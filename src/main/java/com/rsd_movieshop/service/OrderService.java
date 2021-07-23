package com.rsd_movieshop.service;

import com.rsd_movieshop.repositorie.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepo orderRepo;


    public OrderService(OrderRepo orderRepo) {
        this.orderRepo = orderRepo;
    }
}
