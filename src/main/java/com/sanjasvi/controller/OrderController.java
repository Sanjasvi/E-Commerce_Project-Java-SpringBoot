package com.sanjasvi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sanjasvi.entity.Order;
import com.sanjasvi.service.OrderService;

@RestController
@RequestMapping("/orders")
@CrossOrigin("*")
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@PostMapping("/place")
	public Order placeOrder(@RequestBody Order order) {
		return orderService.placeOrder(order);
	}
	
	@GetMapping("/{userId}")
	public List<Order> getOrders(@PathVariable Long userId){
		return orderService.getUserOrders(userId);
	}
	
	@GetMapping("/all")

	public List<Order> getAllOrders(){
          return orderService.getAllOrders();
	}
	
	@PutMapping("/status/{id}")
			public Order updateStatus(@PathVariable Long id, @RequestParam String status){
			return orderService.updateStatus(id,status);
   }
}
