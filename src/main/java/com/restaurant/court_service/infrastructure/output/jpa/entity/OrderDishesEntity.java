package com.restaurant.court_service.infrastructure.output.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "order_dishes")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderDishesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private DishEntity dish;

    @ManyToOne
    @JoinColumn(nullable = false)
    private OrderEntity order;

    @Column(nullable = false)
    private Integer quantity;

}
