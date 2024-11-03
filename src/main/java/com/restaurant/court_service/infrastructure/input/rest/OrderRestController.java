package com.restaurant.court_service.infrastructure.input.rest;


import com.restaurant.court_service.application.dto.DishDtoResponse;
import com.restaurant.court_service.application.dto.OrderDtoResponse;
import com.restaurant.court_service.application.dto.PlaceOrderDtoRequest;
import com.restaurant.court_service.application.handler.IOrderHandler;
import com.restaurant.court_service.domain.model.PageCustom;
import com.restaurant.court_service.infrastructure.output.security.entity.SecurityUser;
import com.restaurant.court_service.utils.Constants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(
            summary = "Place a new order",
            description = "Allows an authenticated client to place a new order in the system."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Order placed successfully, no content returned"),
            @ApiResponse(responseCode = "400", description = "Invalid request body or validation error"),
            @ApiResponse(responseCode = "404", description = "Restaurant or dish not found"),
            @ApiResponse(responseCode = "409", description = "User already has an order")

    })
    @PostMapping("/place")
    public ResponseEntity<Void> placeOrder(@Valid @RequestBody PlaceOrderDtoRequest placeOrderDtoRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SecurityUser userDetails = (SecurityUser) authentication.getPrincipal();
        Long clientId = userDetails.getId();
        orderHandler.placeOrder(placeOrderDtoRequest, clientId);
        return ResponseEntity.noContent().build();
    }


    @Operation(summary = "Retrieve all Orders By Status", description = "Returns a list of all Orders available in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the list of Orders"),
    })
    @GetMapping
    public ResponseEntity<PageCustom<OrderDtoResponse>> getAllOrders(
            @RequestParam(defaultValue = Constants.DEFAULT_PAGE) Integer page,
            @RequestParam(defaultValue = Constants.DEFAULT_SIZE) Integer size,
            @RequestParam() String  orderStatus){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SecurityUser userDetails = (SecurityUser) authentication.getPrincipal();
        Long clientId = userDetails.getId();

        return ResponseEntity.ok(orderHandler.getAllOrders(page,size,orderStatus,clientId));
    }

}
