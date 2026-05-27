package com.sanjasvi.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sanjasvi.entity.Order;
import com.sanjasvi.repository.OrderRepository;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    // Place Order
    public Order placeOrder(Order order) {

        order.setStatus("PLACED");

        order.setOrderDate(LocalDateTime.now());

        return orderRepository.save(order);
    }

    // Get User Orders
    public List<Order> getUserOrders(Long userId) {

        return orderRepository.findByUserId(userId);
    }
    
 // All Orders

    public List<Order>
    getAllOrders(){

    return orderRepository
    .findAll();

    }
    
 // Update Status

    public Order
    updateStatus(

    Long id,

    String status

    ){

    Order order = orderRepository.findById(id).orElseThrow();
    
    order.setStatus(status);

    		return orderRepository.save(order);

    		}
    
}