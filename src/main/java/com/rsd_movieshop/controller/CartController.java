package com.rsd_movieshop.controller;


import com.rsd_movieshop.model.Cart;
import com.rsd_movieshop.model.CartItem;
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


    @GetMapping(path = "user/cart/{cartid}")
    public ResponseEntity<Cart> getCart(@PathVariable long cartid) {
        return cartService.findCartById(cartid);
    }

    @PutMapping(path = "user/cart/{cartid}")
    public ResponseEntity<Cart> addCartItem(@PathVariable long cartid, @RequestBody CartItem cartItem) {
        return cartService.addCartItem(cartid, cartItem);
    }

    @DeleteMapping(path = "admin/cart/{cartid}")
    public ResponseEntity<Cart> deleteCartItem(@PathVariable long cartid, @RequestBody CartItem cartItem) {
    	return cartService.deleteItem(cartid, cartItem);
    }
}