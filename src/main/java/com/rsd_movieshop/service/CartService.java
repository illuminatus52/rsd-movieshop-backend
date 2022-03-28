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
				CartResponse cartResponse = getCartResponse(cart.getCartId());
				return new ResponseEntity<>(cartResponse, HttpStatus.OK);
			} catch (Exception e) {
				throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e.getCause());
			}
		}
	}

	public ResponseEntity<CartResponse> addItemToCart(long id, String username, long movieId) {
		Movie movie = movieRepo.findByMovieId(movieId);
		try {
			Cart cart = userCheck(username, id);
			List<CartItem> items = new ArrayList<>();
			List<CartItem> cartItems = cart.getCartItems();
			if (cartItems.size() > 0) {
				for (CartItem cartItem : cartItems) {
					if (cartItem.getMovie() == movie) {
						if (movie.getMovieStock() > cartItem.getQuantity() + 1) {
							cartItem.setQuantity(cartItem.getQuantity() + 1);
						} else {
							cartItem.setQuantity(cartItem.getQuantity());
						}
						cartItemRepo.save(cartItem);
					} else {
						CartItem newCartItem = new CartItem(movie, cart, 1);
						cartItemRepo.save(newCartItem);
						items.add(newCartItem);
						cart.setCartItems(cartItems);
					}
				}
			} else {
				CartItem newCartItem = new CartItem(movie, cart, 1);
				cartItemRepo.save(newCartItem);
				cartItems.add(newCartItem);
				cart.setCartItems(cartItems);

			}
			
			cartItems.addAll(items);
			cartRepo.save(cart);
			CartResponse cartResponse = getCartResponse(id);
			return new ResponseEntity<>(cartResponse, HttpStatus.OK);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e.getCause());
		}
	}

	public ResponseEntity<CartResponse> updateCart(long id, String username, CartItemRequest cartItemRequest) {
		Movie movie = movieRepo.findByMovieId(cartItemRequest.getMovieID());

		if (movie.getMovieStock() > 0) {
			try {
				Cart cart = userCheck(username, id);
				int quantity = cartItemRequest.getQuantity();
				if (quantity > movie.getMovieStock()) {
					quantity = movie.getMovieStock();
				}
				List<CartItem> itemsAlreadyExist = cart.getCartItems();
				List<Movie> allTheMoviesInThisCart = getAllMovies(itemsAlreadyExist);
				
				List<CartItem> newItems = new ArrayList<>();
				if (itemsAlreadyExist.size() == 0) {
					CartItem item = new CartItem(movie, cart, quantity);
					itemsAlreadyExist.add(item);
					cartItemRepo.save(item);
				} else {
					CartItem item = new CartItem(movie, cart, quantity);
					
					if (!allTheMoviesInThisCart.contains(movie)) {
						cartItemRepo.save(item);
						newItems.add(item);
					} else {
						for (CartItem cartItem : itemsAlreadyExist) {
							if (cartItem.getMovie() == movie) {
									cartItem.setQuantity(quantity);
							}
						}
					}
					if (newItems.size() > 0) {
						itemsAlreadyExist.addAll(newItems);
					}
				}
				cart.setCartItems(itemsAlreadyExist);
				cartRepo.save(cart);
				CartResponse cartResponse = getCartResponse(id);

				return new ResponseEntity<>(cartResponse, HttpStatus.OK);
			} catch (Exception e) {
				throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e.getCause());
			}
		} else {
			throw new ResponseStatusException(HttpStatus.OK, "This item is sold out");
		}
	}

	public ResponseEntity<CartResponse> deleteItem(long cartId, String username, long itemId) {
		try {
			Cart cart = userCheck(username, cartId);
			CartItem item = cartItemRepo.findByItemId(itemId);
			List<CartItem> items = cart.getCartItems();
			items.remove(item);
			cart.setCartItems(items);
			cartRepo.save(cart);
			cartItemRepo.delete(item);
			CartResponse cartResponse = getCartResponse(cartId);

			return new ResponseEntity<>(cartResponse, HttpStatus.OK);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e.getCause());
		}
	}

	public CartResponse getCartResponse(long cartId) {
		CartResponse cartResponse = new CartResponse();
		cartResponse.setCartId(cartId);
		List<CartItemResponse> items = new ArrayList<>();
		Cart cart = cartRepo.findByCartId(cartId);
		for (CartItem item : cart.getCartItems()) {
			CartItemResponse itemResponse = new CartItemResponse(item.getItemID(), item.getMovie().getTitle(), item.getQuantity());
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
	
	public List<Movie> getAllMovies(List<CartItem> itemsAlreadyExist) {
		List<Movie> movies = new ArrayList<>();
		for (CartItem cartItem : itemsAlreadyExist) {
			Movie movie = cartItem.getMovie();
			movies.add(movie);
		}
		return movies;
	}
 }