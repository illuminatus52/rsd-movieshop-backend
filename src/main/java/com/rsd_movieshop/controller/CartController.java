package com.rsd_movieshop.controller;


import com.rsd_movieshop.model.CartItemRequest;
import com.rsd_movieshop.model.CartResponse;
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
    public ResponseEntity<CartResponse> getCart(@PathVariable long cartid) {
        return cartService.findCartById(cartid);
    }

    @PutMapping(path = "user/cart/{cartid}")
    public ResponseEntity<CartResponse> addCartItem(@PathVariable long cartid, @RequestBody CartItemRequest cartItemRequest) {
        return cartService.addCartItem(cartid, cartItemRequest);
    }

    @DeleteMapping(path = "admin/cart/{cartid}")
    public ResponseEntity<CartResponse> deleteCartItem(@PathVariable long cartid, long cartItemId) {
    	return cartService.deleteItem(cartid, cartItemId);
    }
}