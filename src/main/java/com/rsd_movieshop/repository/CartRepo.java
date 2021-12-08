package com.rsd_movieshop.repository;

import com.rsd_movieshop.model.Cart;
import com.rsd_movieshop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepo extends JpaRepository<Cart,Long> {
}
