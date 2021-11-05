package com.rsd_movieshop.controller;

import com.rsd_movieshop.model.Orders;
import com.rsd_movieshop.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/orders")
public class OrderController {

	private final OrderService orderService;

	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}


	@GetMapping("/{orderID}")
	public Optional<Orders> getOrder(@PathVariable int orderID) {
		return orderService.findOrderById(orderID);
	}
	
	@GetMapping
	public List<Orders> getOrders() {
		System.out.println("OrderController: getOrders");
		return orderService.findAllOrders();
	}
	
	@PostMapping
	public void addNewOrder(@RequestBody Orders order) {
		orderService.saveOrder(order);
	}
	
	@PutMapping(path = "/{orderID}")
	public void updateOrder(@PathVariable int orderID) {
		// update specific order (admin only)
		System.out.println("OrderController: updateOrder");
	}
	
	@DeleteMapping("/{orderID}")
	public void deleteOrder(@PathVariable int orderID) {

		System.out.println("OrderController: deleteOrder");
	}
}
