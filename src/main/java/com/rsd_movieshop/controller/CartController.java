package com.rsd_movieshop.controller;


import com.rsd_movieshop.model.Cart;
import com.rsd_movieshop.model.CartItem;
import com.rsd_movieshop.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/cart")
public class CartController {

    private final CartService cartService;


    public CartController(CartService cartService) {
        this.cartService = cartService;
    }



    @GetMapping("/{cartid}")
    public Cart getCart(@PathVariable int cartid) {
        //Get Cart
        return null;
    }

    @PutMapping("/{cartid")
    public void updateCart(@RequestBody CartItem cartItem) {
        //update cart
    }
    @PostMapping
    public void createCart(@RequestBody Cart cart) {
        //Create Cart
    }
}
