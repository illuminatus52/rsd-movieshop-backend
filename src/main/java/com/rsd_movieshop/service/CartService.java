package com.rsd_movieshop.service;

import com.rsd_movieshop.repository.CartRepo;
import org.springframework.stereotype.Service;

@Service
public class CartService {


    private final CartRepo cartRepo;


    public CartService(CartRepo cartRepo) {
        this.cartRepo = cartRepo;
    }
}
