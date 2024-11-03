package com.restaurant.court_service.infrastructure.input.rest;


import com.restaurant.court_service.application.dto.PlaceOrderDtoRequest;
import com.restaurant.court_service.application.handler.IOrderHandler;
import com.restaurant.court_service.infrastructure.output.security.entity.SecurityUser;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("order")
@RequiredArgsConstructor
@Validated
public class OrderRestController {
    private final IOrderHandler orderHandler;

    @PostMapping("/place")
    public ResponseEntity<Void> placeOrder(@Valid @RequestBody PlaceOrderDtoRequest placeOrderDtoRequest){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SecurityUser userDetails = (SecurityUser) authentication.getPrincipal();
        Long clientId = userDetails.getId();
        orderHandler.placeOrder(placeOrderDtoRequest,clientId);
        return ResponseEntity.noContent().build();
    }

}
