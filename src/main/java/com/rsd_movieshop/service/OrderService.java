package com.rsd_movieshop.service;

import com.rsd_movieshop.repository.OrderRepo;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepo orderRepo;


    public OrderService(OrderRepo orderRepo) {
        this.orderRepo = orderRepo;
    }
}
