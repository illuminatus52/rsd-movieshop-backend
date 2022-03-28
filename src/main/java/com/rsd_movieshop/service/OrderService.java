package com.rsd_movieshop.service;

import com.rsd_movieshop.model.Cart;
import com.rsd_movieshop.model.CartItem;
import com.rsd_movieshop.model.CartItemRequest;
import com.rsd_movieshop.model.Movie;
import com.rsd_movieshop.model.OrderStatus;
import com.rsd_movieshop.model.Orders;
import com.rsd_movieshop.model.User;
import com.rsd_movieshop.repository.CartRepo;
import com.rsd_movieshop.repository.MovieRepo;
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
	private final MovieRepo movieRepo;

	public OrderService(OrderRepo orderRepo, CartRepo cartRepo, UserRepo userRepo, MovieRepo movieRepo) {
		super();
		this.orderRepo = orderRepo;
		this.cartRepo = cartRepo;
		this.userRepo = userRepo;
		this.movieRepo = movieRepo;
	}

	public ResponseEntity<OrderResponse> findOrderById(long id, String username) {
		if (orderRepo.findByOrderId(id) == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The order with id: " + id + " doesn't exist!");
		} else {
			Orders order = checkOrder(username, id);
			OrderResponse orderResponse = getOrderResponse(order);
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

	public ResponseEntity<OrderResponse> createOrderFromCart(long id, String username) {
		try {
			double sum = 0;
			int newStockQuantity;
			Cart cart = userCheck(username, id);
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
			try {
				Orders order = orderRepo.getById(id);
				if (order.getStatus() == OrderStatus.In_process || order.getStatus() == OrderStatus.Cancelled) {
					List<CartItem> cartItems = order.getCartItems();
					for (CartItem cartItem : cartItems) {
						Movie movie = cartItem.getMovie();
						int stock = movie.getMovieStock();
						stock += cartItem.getQuantity();
						movie.setMovieStock(stock);
						movieRepo.save(movie);
					}
				}
				orderRepo.deleteById(id);
			} catch (Exception e) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
			}
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
			cartItemRequest.setMovieID(cartItem.getMovie().getMovieID());
			cartItemRequest.setQuantity(cartItem.getQuantity());
			items.add(cartItemRequest);
		}
		orderResponse.setItems(items);
		return orderResponse;
	}

	public Cart userCheck(String username, long cartId) {
		User user = userRepo.findByUsername(username);
		if (user.getCart().getCartId() != cartId) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		} else {
			Cart cart = cartRepo.findByCartId(cartId);
			return cart;
		}
	}

	public Orders checkOrder(String username, long OrderId) {
		Orders order = orderRepo.findByOrderId(OrderId);
		if (!order.getUserid().getUsername().equalsIgnoreCase(username)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		} else {
			return order;
		}
	}
}