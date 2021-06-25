package com.rsd_movieshop.controller;

import com.rsd_movieshop.model.Order;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/orders")
public class OrderController {
	
	@GetMapping("{orderID}")
	public Order


}
