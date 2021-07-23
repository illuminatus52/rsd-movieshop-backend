package com.rsd_movieshop.controller;

import com.rsd_movieshop.model.Order;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(path = "/orders")
public class OrderController {
	
	@GetMapping("/{orderID}")
	public Order getOrder(@PathVariable int orderID) {
		// get specific order
		return null;
	}
	
	@GetMapping
	public ArrayList<Order> getOrders() {
		// return all orders (admin only)
		System.out.println("OrderController: getOrders");
		return null;
	}
	
	@PostMapping
	public void addNewOrder(@RequestBody Order order) {
		// add Order from Cart
	}
	
	@PutMapping(path = "/{orderID}")
	public void updateOrder(@PathVariable int orderID) {
		// update specific order (admin only)
		System.out.println("OrderController: updateOrder");
	}
	
	@DeleteMapping("/{orderID}")
	public void deleteOrder(@PathVariable int orderID) {
		// delete specific order (admin only)
		System.out.println("OrderController: deleteOrder");
	}
}
