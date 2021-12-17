package com.rsd_movieshop.controller;

import com.rsd_movieshop.model.OrderStatus;
import com.rsd_movieshop.model.Orders;
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

	@GetMapping(path = "user/orders/{orderID}")
	public ResponseEntity<Orders> getOrder(@PathVariable long orderID) {
		return orderService.findOrderById(orderID);
	}

	@GetMapping(path = "admin/orders/all")
	public ResponseEntity<List<Orders>> getOrders() {
		return orderService.findAllOrders();
	}

	@PostMapping(path = "user/orders/{cartID}")
	public ResponseEntity<Orders> createNewOrder(@PathVariable long cartID) {
		return orderService.createOrderFromCart(cartID);
	}

	@PutMapping(path = "admin/orders/{orderID}")
	public ResponseEntity<Orders> updateOrder(@PathVariable long orderID,
			@RequestParam OrderStatus orderStatus) {
		return orderService.updateOrder(orderID, orderStatus);
	}

	@DeleteMapping(path = "admin/orders/{orderID}")
	public ResponseEntity<String> deleteOrder(@PathVariable long orderID) {
		return orderService.deleteOrder(orderID);
	}
}
