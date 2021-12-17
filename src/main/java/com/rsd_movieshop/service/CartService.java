package com.rsd_movieshop.service;

import com.rsd_movieshop.model.Cart;
import com.rsd_movieshop.model.CartItem;
import com.rsd_movieshop.repository.CartRepo;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CartService {

	private final CartRepo cartRepo;

	public CartService(CartRepo cartRepo) {
		this.cartRepo = cartRepo;
	}

	public ResponseEntity<Cart> findCartById(long id) {
		if (cartRepo.findByCartId(id) == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no cart with this ID!");
		} else {
			try {
				Cart cart = cartRepo.findByCartId(id);
				return new ResponseEntity<Cart>(cart, HttpStatus.OK);
			} catch (Exception e) {
				throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e.getCause());
			}
		}
	}

	public ResponseEntity<Cart> addCartItem(long id, CartItem item) {
		try {
			Cart cart = cartRepo.findByCartId(id);
			List<CartItem> items = cart.getCartItems();
			items.add(item);
			cart.setCartItems(items);
			cartRepo.save(cart);
			return new ResponseEntity<Cart>(cart, HttpStatus.OK);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e.getCause());
		}
	}
	
	public ResponseEntity<Cart> deleteItem(long id, CartItem item) {
		try {
			Cart cart = cartRepo.findByCartId(id);
			List<CartItem> items = cart.getCartItems();
			items.remove(item);
			cart.setCartItems(items);
			cartRepo.save(cart);
			return new ResponseEntity<Cart>(cart, HttpStatus.OK);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e.getCause());
		}
	}
}
