package com.rsd_movieshop.service;

import com.rsd_movieshop.model.Cart;
import com.rsd_movieshop.repository.CartRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartService {

	
    private final CartRepo cartRepo;


    public CartService(CartRepo cartRepo) {
        this.cartRepo = cartRepo;
    }

    
    public Optional<Cart> findCartById(long id){
        return cartRepo.findById(id);
    }

    public void saveCart(Cart cart){
        cartRepo.save(cart);
    }
}
