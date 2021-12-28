package com.rsd_movieshop.service;

import com.rsd_movieshop.model.Cart;
import com.rsd_movieshop.model.CartItem;
import com.rsd_movieshop.model.CartItemRequest;
import com.rsd_movieshop.model.Movie;
import com.rsd_movieshop.model.OrderStatus;
import com.rsd_movieshop.model.Orders;
import com.rsd_movieshop.repository.CartRepo;
import com.rsd_movieshop.repository.OrderRepo;
import com.rsd_movieshop.repository.UserRepo;
import com.rsd_movieshop.responseModels.OrderResponse;

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

	public ResponseEntity<OrderResponse> findOrderById(long id) {
		if (orderRepo.findByOrderId(id) == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The order with id: " + id + " doesn't exist!");
		} else {
			OrderResponse orderResponse = getOrderResponse(orderRepo.findByOrderId(id));
			return new ResponseEntity<>(orderResponse, HttpStatus.OK);
		}
	}

	public ResponseEntity<List<OrderResponse>> findAllOrders() {
		try {
			List<OrderResponse> orders = new ArrayList<>();
			for (Orders order1 : orderRepo.findAll()) {
				OrderResponse orderResponse = getOrderResponse(order1);
				orders.add(orderResponse);
			}
			return new ResponseEntity<>(orders, HttpStatus.OK);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e.getCause());
		}
	}

	public ResponseEntity<OrderResponse> createOrderFromCart(long id) {

		try {
			double sum = 0;
			int newStockQuantity;
			Cart cart = cartRepo.findByCartId(id);
			List<CartItem> items = cart.getCartItems();
			Orders order = new Orders(items, userRepo.findByCart_CartId(id));
			List<CartItem> newCartList = new ArrayList<>();
			for (CartItem cartItem : items) {
				Movie movie = cartItem.getMovie();
				if (movie.getMovieStock() < cartItem.getQuantity()) {
					cartItem.setQuantity(movie.getMovieStock());
					movie.setMovieStock(0);
					newStockQuantity = 0;
				} else {
					newStockQuantity = movie.getMovieStock() - cartItem.getQuantity();
				}
				sum += cartItem.getMovie().getPrice() * cartItem.getQuantity();
				movie.setMovieStock(newStockQuantity);
				cartItem.setOrder(order);
				cartItem.setMovie(movie);
				cartItem.setCart(null);
			}
			order.setTotalPrice(sum);
			cart.setCartItems(newCartList);
			orderRepo.save(order);
			cartRepo.save(cart);
			// FIXME error handling!
			OrderResponse orderResponse = getOrderResponse(order);
			return new ResponseEntity<>(orderResponse, HttpStatus.OK);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e.getCause());
		}
	}

	public ResponseEntity<OrderResponse> updateOrder(long id, OrderStatus orderStatus) {
		if (orderRepo.findByOrderId(id) == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "the order with the id: " + id + " doesn't exist!",
					null);
		} else {
			Orders order = orderRepo.findByOrderId(id);
			order.setStatus(orderStatus);
			orderRepo.save(order);
			OrderResponse orderResponse = getOrderResponse(order);
			return new ResponseEntity<>(orderResponse, HttpStatus.OK);
		}
	}

	public ResponseEntity<String> deleteOrder(long id) {
		if (orderRepo.findByOrderId(id) == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "the order with the id: " + id + " doesn't exist!",
					null);
		} else {
			orderRepo.deleteById(id);
			return new ResponseEntity<>("The order with the id: " + id + " is deleted!", HttpStatus.OK);
		}
	}

	public OrderResponse getOrderResponse(Orders order) {
		OrderResponse orderResponse = new OrderResponse();
		orderResponse.setOrderId(order.getOrderId());
		orderResponse.setUserId(order.getUserid().getUserId());
		orderResponse.setOrderStatus(order.getStatus());
		orderResponse.setTotalPrice(order.getTotalPrice());
		List<CartItemRequest> items = new ArrayList<>();
		for (CartItem cartItem : order.getCartItems()) {
			CartItemRequest cartItemRequest = new CartItemRequest();
			cartItemRequest.setMovieName(cartItem.getMovie().getTitle());
			cartItemRequest.setQuantity(cartItem.getQuantity());
			items.add(cartItemRequest);
		}
		orderResponse.setItems(items);
		return orderResponse;
	}
}