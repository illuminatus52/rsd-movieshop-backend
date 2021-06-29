package com.rsd_movieshop.controller;


import com.rsd_movieshop.model.Cart;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping(path = "/cart")
public class CartController {

    @GetMapping("/{cartid}")
    public Cart getCart(@PathVariable int cartid) {
        //Get Cart
        return null;
    }

    @PutMapping("/{cartid")
    public void updateCart(@PathVariable int cartid) {
        //update cart
    }
}
