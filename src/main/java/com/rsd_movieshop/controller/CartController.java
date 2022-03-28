package com.rsd_movieshop.controller;

import com.rsd_movieshop.model.CartItemRequest;
import com.rsd_movieshop.responseModels.CartResponse;
import com.rsd_movieshop.service.CartService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/")
public class CartController {
	
	private final CartService cartService;
	
	public CartController(CartService cartService) {
		this.cartService = cartService;
	}
	
	@GetMapping(path = "user/{username}/cart/{cartid}")
	public ResponseEntity<CartResponse> getCart(
			@PathVariable String username,
			@PathVariable long cartid) {
		return cartService.findCartById(cartid, username);
	}
	@PutMapping(path = "user/{username}/cart/{cartid}/movieId/{id}")
	public ResponseEntity<CartResponse> addItem(@PathVariable String username,
												@PathVariable long cartid,
												@PathVariable long id) {
		return cartService.addItemToCart(cartid, username, id);
	}

	
	@PutMapping(path = "user/{username}/cart/{cartid}")
	public ResponseEntity<CartResponse> addCartItem(
			@PathVariable String username,
			@PathVariable long cartid,
			@RequestBody CartItemRequest cartItemRequest) {
		return cartService.updateCart(cartid, username, cartItemRequest);
	}
	
	@DeleteMapping(path = "user/{username}/cart/{cartid}")
	public ResponseEntity<CartResponse> deleteCartItem(@PathVariable String username,
			@PathVariable long cartid, @RequestParam long cartItemId) {
		return cartService.deleteItem(cartid, username, cartItemId);
	}
}