package com.rsd_movieshop.controller;

import com.rsd_movieshop.model.OrderStatus;
import com.rsd_movieshop.responseModels.OrderResponse;
import com.rsd_movieshop.service.OrderService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/")
public class OrderController {
	
	private final OrderService orderService;
	
	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}
	
	@GetMapping(path = "user/{username}/orders/{orderID}")
	public ResponseEntity<OrderResponse> getOrder(
			@PathVariable String username,
			@PathVariable long orderID) {
		return orderService.findOrderById(orderID, username);
	}
	
	@GetMapping(path = "admin/orders/all")
	public ResponseEntity<List<OrderResponse>> getOrders() {
		return orderService.findAllOrders();
	}
	
	@PostMapping(path = "user/{username}/orders/{cartID}")
	public ResponseEntity<OrderResponse> createNewOrder(
			@PathVariable String username,
			@PathVariable long cartID) {
		return orderService.createOrderFromCart(cartID, username);
	}
	
	@PutMapping(path = "admin/orders/{orderID}")
	public ResponseEntity<OrderResponse> updateOrder(
			@PathVariable long orderID,
			@RequestParam OrderStatus orderStatus) {
		return orderService.updateOrder(orderID, orderStatus);
	}
	
	@DeleteMapping(path = "admin/orders/{orderID}")
	public ResponseEntity<String> deleteOrder(@PathVariable long orderID) {
		return orderService.deleteOrder(orderID);
	}
}