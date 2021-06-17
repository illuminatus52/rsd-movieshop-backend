package com.rsd_movieshop.controller;

import com.rsd_movieshop.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MovieController {
    
    @GetMapping ("/")
    public String getIndex() {
        return "";
    }
    @GetMapping   ("/register")
    public String getRegister() {
        return "";
    }
    
    @GetMapping("/user/{userID}")
    public User getUser(@PathVariable int userID) {
        // TODO DB-Abfrage auf userID
        
    }
    
    @GetMapping ("/cart")
    public Cart getCart(userID) {
        // get user cart
    }
}
