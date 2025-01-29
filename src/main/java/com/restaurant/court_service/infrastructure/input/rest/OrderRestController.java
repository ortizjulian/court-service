package com.restaurant.court_service.infrastructure.input.rest;

import com.restaurant.court_service.application.dto.OrderDtoResponse;
import com.restaurant.court_service.application.dto.PlaceOrderDtoRequest;
import com.restaurant.court_service.application.handler.IOrderHandler;
import com.restaurant.court_service.domain.model.PageCustom;
import com.restaurant.court_service.infrastructure.output.security.AuthenticationService;
import com.restaurant.court_service.utils.Constants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("order")
@RequiredArgsConstructor
@Validated
public class OrderRestController {
    private final IOrderHandler orderHandler;
    private final AuthenticationService authenticationService;
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
        Long clientId = authenticationService.getAuthenticatedUserId();
        orderHandler.placeOrder(placeOrderDtoRequest, clientId);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Assign an order to a chef",
            description = "Allows an authenticated employee to assign an order to themselves."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Order assigned successfully"),
            @ApiResponse(responseCode = "404", description = "Order not found"),
            @ApiResponse(responseCode = "409", description = "Order cannot be assigned because it is already in another state")
    })
    @PatchMapping("/assign/{orderId}")
    public ResponseEntity<Void> assignOrder(@PathVariable Long orderId) {
        Long employeeId = authenticationService.getAuthenticatedUserId();
        orderHandler.assignOrder(employeeId, orderId);
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

        Long clientId = authenticationService.getAuthenticatedUserId();
        return ResponseEntity.ok(orderHandler.getAllOrders(page,size,orderStatus,clientId));
    }

}
