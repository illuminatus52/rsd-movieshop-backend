package com.rsd_movieshop.service;

import com.rsd_movieshop.model.Cart;
import com.rsd_movieshop.model.CartItem;
import com.rsd_movieshop.model.CartItemRequest;
import com.rsd_movieshop.model.CartResponse;
import com.rsd_movieshop.model.Movie;
import com.rsd_movieshop.repository.CartItemRepo;
import com.rsd_movieshop.repository.CartRepo;
import com.rsd_movieshop.repository.MovieRepo;

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

	public CartService(CartRepo cartRepo, CartItemRepo cartItemRepo, MovieRepo movieRepo) {
		super();
		this.cartRepo = cartRepo;
		this.cartItemRepo = cartItemRepo;
		this.movieRepo = movieRepo;
	}

	public ResponseEntity<CartResponse> findCartById(long id) {
		if (cartRepo.findByCartId(id) == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no cart with this ID!");
		} else {
			try {
				Cart cart = cartRepo.findByCartId(id);
				CartResponse cartResponse = new CartResponse();
				cartResponse.setCartId(id);
				List<String> items = new ArrayList<>();
				for (CartItem item : cart.getCartItems()) {
					items.add(item.getMovie().getTitle());
				}
				cartResponse.setItems(items);
				return new ResponseEntity<CartResponse>(cartResponse, HttpStatus.OK);
			} catch (Exception e) {
				throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e.getCause());
			}
		}
	}

	public ResponseEntity<CartResponse> addCartItem(long id, CartItemRequest cartItemRequest) {
		Movie movie = movieRepo.findMovieByTitle(cartItemRequest.getMovieName());
		if (movie.getMovieStock() > 0) {
			try {
				Cart cart = cartRepo.findByCartId(id);
				int quantity = cartItemRequest.getQuantity();
				if (quantity > movie.getMovieStock()) {
					quantity = movie.getMovieStock();
				}
				CartItem item = new CartItem(movie, cart, quantity);
				List<CartItem> items = cart.getCartItems();
				items.add(item);
				cart.setCartItems(items);
				cartItemRepo.save(item);
				cartRepo.save(cart);
				CartResponse cartResponse = new CartResponse();
				cartResponse.setCartId(id);
				List<String> itemsList = new ArrayList<>();
				for (CartItem cartItem : cart.getCartItems()) {
					itemsList.add(cartItem.getMovie().getTitle());
				}
				cartResponse.setItems(itemsList);
				return new ResponseEntity<CartResponse>(cartResponse, HttpStatus.OK);
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
			CartResponse cartResponse = new CartResponse();
			cartResponse.setCartId(cartId);
			List<String> itemsList = new ArrayList<>();
			for (CartItem cartItem : cart.getCartItems()) {
				itemsList.add(cartItem.getMovie().getTitle());
			}
			cartResponse.setItems(itemsList);
			return new ResponseEntity<CartResponse>(cartResponse, HttpStatus.OK);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e.getCause());
		}
	}
}
