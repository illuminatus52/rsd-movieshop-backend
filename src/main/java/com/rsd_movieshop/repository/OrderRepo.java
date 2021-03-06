package com.rsd_movieshop.repository;

import com.rsd_movieshop.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepo extends JpaRepository<Orders,Long> {
    Orders findByOrderId (long id);
}