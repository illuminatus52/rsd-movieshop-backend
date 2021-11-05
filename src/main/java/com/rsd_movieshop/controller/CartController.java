package com.rsd_movieshop.controller;


import com.rsd_movieshop.model.Cart;
import com.rsd_movieshop.model.CartItem;
import com.rsd_movieshop.service.CartService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping(path = "/api/cart")
public class CartController {

    private final CartService cartService;


    public CartController(CartService cartService) {
        this.cartService = cartService;
    }


    @GetMapping("/{cartid}")
    public Optional<Cart> getCart(@PathVariable int cartid) {
        return cartService.findCartById(cartid);
    }

    @PutMapping("/{cartid")
    public void updateCart(@RequestBody CartItem cartItem) {
        //update cart
    }

    @PostMapping
    public void createCart(@RequestBody Cart cart) {
        cartService.saveCart(cart);
    }
}