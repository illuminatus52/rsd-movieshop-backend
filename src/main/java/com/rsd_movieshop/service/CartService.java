package com.rsd_movieshop.service;

import com.rsd_movieshop.model.Cart;
import com.rsd_movieshop.model.CartItem;
import com.rsd_movieshop.model.CartItemRequest;
import com.rsd_movieshop.model.Movie;
import com.rsd_movieshop.model.User;
import com.rsd_movieshop.repository.CartItemRepo;
import com.rsd_movieshop.repository.CartRepo;
import com.rsd_movieshop.repository.MovieRepo;
import com.rsd_movieshop.repository.UserRepo;
import com.rsd_movieshop.responseModels.CartItemResponse;
import com.rsd_movieshop.responseModels.CartResponse;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CartService {

	private final CartRepo cartRepo;
	private final CartItemRepo cartItemRepo;
	private final MovieRepo movieRepo;
	private final UserRepo userRepo;

	public CartService(CartRepo cartRepo, CartItemRepo cartItemRepo, MovieRepo movieRepo, UserRepo userRepo) {
		super();
		this.cartRepo = cartRepo;
		this.cartItemRepo = cartItemRepo;
		this.movieRepo = movieRepo;
		this.userRepo = userRepo;
	}

	public ResponseEntity<CartResponse> findCartById(long id, String username) {
		if (cartRepo.findByCartId(id) == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no cart with this ID!");
		} else {
			try {
				Cart cart = userCheck(username, id);
				CartResponse cartResponse = getCartResponse(cart);
				return new ResponseEntity<>(cartResponse, HttpStatus.OK);
			} catch (Exception e) {
				throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e.getCause());
			}
		}
	}

	public ResponseEntity<CartResponse> addCartItem(long id, String username, CartItemRequest cartItemRequest) {
		Movie movie = movieRepo.findMovieByTitle(cartItemRequest.getMovieName());
		if (movie.getMovieStock() > 0) {
			try {
				Cart cart = userCheck(username, id);
				int quantity = cartItemRequest.getQuantity();
				if (quantity > movie.getMovieStock()) {
					quantity = movie.getMovieStock();
				}
				CartItem item = new CartItem(movie, cart, quantity);
				List<CartItem> items = cart.getCartItems();
				if (items.size() > 0) {
					for (CartItem cartItem : items) {
						if (cartItem.getMovie().getTitle().equalsIgnoreCase(cartItemRequest.getMovieName())) {
							cartItem.setQuantity(cartItem.getQuantity() + quantity);
							item = cartItem;
						} else {
							items.add(item);
						}
					}
				} else {
					items.add(item);
				}
				cart.setCartItems(items);
				cartItemRepo.save(item);
				cartRepo.save(cart);
				CartResponse cartResponse = getCartResponse(cart);
				return new ResponseEntity<>(cartResponse, HttpStatus.OK);
			} catch (Exception e) {
				throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e.getCause());
			}
		} else {
			throw new ResponseStatusException(HttpStatus.OK, "This item is sold out");
		}
	}

	public ResponseEntity<CartResponse> deleteItem(long cartId, long itemId) {
		try {
			Cart cart = cartRepo.findByCartId(cartId);
			CartItem item = cartItemRepo.findByItemId(itemId);
			List<CartItem> items = cart.getCartItems();
			items.remove(item);
			cart.setCartItems(items);
			cartRepo.save(cart);
			cartItemRepo.delete(item);
			CartResponse cartResponse = getCartResponse(cart);
			return new ResponseEntity<>(cartResponse, HttpStatus.OK);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e.getCause());
		}
	}

	public CartResponse getCartResponse(Cart cart) {
		CartResponse cartResponse = new CartResponse();
		cartResponse.setCartId(cart.getCartId());
		List<CartItemResponse> items = new ArrayList<>();
		for (CartItem item : cart.getCartItems()) {
			CartItemResponse itemResponse = new CartItemResponse(item.getMovie().getTitle(), item.getQuantity());
			items.add(itemResponse);
		}
		cartResponse.setItems(items);
		return cartResponse;
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
}