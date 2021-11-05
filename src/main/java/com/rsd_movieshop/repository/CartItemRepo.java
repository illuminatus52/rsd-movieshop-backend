package com.rsd_movieshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rsd_movieshop.model.CartItem;

@Repository
public interface CartItemRepo extends JpaRepository<CartItem, Long>{

}
