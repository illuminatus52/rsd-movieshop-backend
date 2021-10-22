package com.rsd_movieshop.service;

import com.rsd_movieshop.model.Orders;
import com.rsd_movieshop.repository.OrderRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepo orderRepo;


    public OrderService(OrderRepo orderRepo) {
        this.orderRepo = orderRepo;
    }

    public Optional<Orders> findOrderById(long id){
        return orderRepo.findById(id);
    }

    public List<Orders> findAllOrders(){
        return orderRepo.findAll();
    }

    public void saveOrder(Orders order){
        orderRepo.save(order);
    }

    public void deleteOrder(long id){
        orderRepo.deleteById(id);
    }
}
