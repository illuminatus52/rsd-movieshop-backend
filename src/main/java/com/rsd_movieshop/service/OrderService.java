package com.rsd_movieshop.service;

import com.rsd_movieshop.model.Cart;
import com.rsd_movieshop.model.CartItem;
import com.rsd_movieshop.model.OrderStatus;
import com.rsd_movieshop.model.Orders;
import com.rsd_movieshop.repository.CartRepo;
import com.rsd_movieshop.repository.OrderRepo;
import com.rsd_movieshop.repository.UserRepo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

	private final OrderRepo orderRepo;
	private final CartRepo cartRepo;
	private final UserRepo userRepo;

	public OrderService(OrderRepo orderRepo, CartRepo cartRepo, UserRepo userRepo) {
		this.orderRepo = orderRepo;
		this.cartRepo = cartRepo;
		this.userRepo = userRepo;
	}

	public ResponseEntity<Orders> findOrderById(long id) {
		if (orderRepo.getById(id) == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The order with id: " + id + " doesn't existed!");
		} else {
			Orders order = orderRepo.getById(id);
			return new ResponseEntity<Orders>(order, HttpStatus.OK);
		}
	}

	public ResponseEntity<List<Orders>> findAllOrders() {
		try {
			List<Orders> orders = orderRepo.findAll();
			return new ResponseEntity<List<Orders>>(orders, HttpStatus.OK);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e.getCause());
		}
	}

	public ResponseEntity<Orders> createOrderfromCart(long id) {
		try {
			Cart cart = cartRepo.getById(id);
			List<CartItem> items = cart.getCartItems();
			Orders order = new Orders(items, userRepo.findByCart_CartId(id));
			List<CartItem> newCartList = new ArrayList<CartItem>();
			cart.setCartItems(newCartList);
			cartRepo.save(cart);
			orderRepo.save(order);
			//FIXME: when creating new order, the items should be subtracted from the stock quantity!
			return new ResponseEntity<Orders>(order, HttpStatus.OK);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e.getCause());
		}
	}

	public ResponseEntity<Orders> updateOrder(long id, OrderStatus orderStatus) {
		if (orderRepo.getById(id) == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "the order with the id: " + id + " doesn't exist!",
					null);
		} else {
			Orders order = orderRepo.getById(id);
			order.setOrderStatus(orderStatus);
			return new ResponseEntity<Orders>(order, HttpStatus.OK);
		}
	}

	public ResponseEntity<String> deleteOrder(long id) {
		if (orderRepo.getById(id) == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "the order with the id: " + id + " doesn't exist!",
					null);
		} else {
			orderRepo.deleteById(id);
			return new ResponseEntity<String>("The order with the id: " + id + " is deleted!", HttpStatus.OK);
		}
	}
}
