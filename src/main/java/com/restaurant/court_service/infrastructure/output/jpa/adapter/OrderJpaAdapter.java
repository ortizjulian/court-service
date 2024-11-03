package com.restaurant.court_service.infrastructure.output.jpa.adapter;

import com.restaurant.court_service.domain.model.OrderDish;
import com.restaurant.court_service.domain.model.PlaceOrder;
import com.restaurant.court_service.domain.spi.IOrderPersistencePort;
import com.restaurant.court_service.infrastructure.output.jpa.entity.OrderDishesEntity;
import com.restaurant.court_service.infrastructure.output.jpa.entity.OrderEntity;
import com.restaurant.court_service.infrastructure.output.jpa.entity.RestaurantEntity;
import com.restaurant.court_service.infrastructure.output.jpa.repository.IDishRepository;
import com.restaurant.court_service.infrastructure.output.jpa.repository.IOrderDishesRepository;
import com.restaurant.court_service.infrastructure.output.jpa.repository.IOrderRepository;
import com.restaurant.court_service.infrastructure.output.jpa.repository.IRestaurantRepository;
import com.restaurant.court_service.utils.Constants;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
public class OrderJpaAdapter implements IOrderPersistencePort {

    private final IRestaurantRepository restaurantRepository;
    private final IOrderRepository orderRepository;
    private final IDishRepository dishRepository;
    private final IOrderDishesRepository orderDishesRepository;

    @Override
    public void createOrder(PlaceOrder placeOrder) {

        OrderEntity orderEntity = new OrderEntity();
        RestaurantEntity restaurantEntity = restaurantRepository.getReferenceById(placeOrder.getRestaurantId());
        orderEntity.setClientId(placeOrder.getClientId());
        orderEntity.setRestaurant(restaurantEntity);
        orderEntity.setStatus(placeOrder.getStatus());

        OrderEntity savedOrder = orderRepository.save(orderEntity);

        List<OrderDishesEntity> orderDishes = new ArrayList<>();
        for (OrderDish dishOrder : placeOrder.getDishes()) {
            OrderDishesEntity orderDishesEntity = new OrderDishesEntity();
            orderDishesEntity.setDish(dishRepository.getReferenceById(dishOrder.getId()));
            orderDishesEntity.setOrder(savedOrder);
            orderDishesEntity.setQuantity(dishOrder.getQuantity());

            orderDishes.add(orderDishesEntity);
        }

        orderDishesRepository.saveAll(orderDishes);
    }

    @Override
    public boolean clientHasAlreadyAnOrder(Long clientId) {
        List<String> statuses = Arrays.asList(Constants.PENDING, Constants.IN_PREPARATION, Constants.READY);

        List<OrderEntity> orders = orderRepository.findByClientIdAndStatusIn(clientId, statuses);

        return !orders.isEmpty();
    }
}
