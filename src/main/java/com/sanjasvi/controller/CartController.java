package com.sanjasvi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;

import com.sanjasvi.entity.Cart;
import com.sanjasvi.service.CartService;

@RestController
@RequestMapping("/cart")
@CrossOrigin("*")
public class CartController {

	@Autowired
	private CartService cartService;
	
	@PostMapping("/add")
	public Cart addToCart(@RequestBody Cart cart) {
		return cartService.addToCart(cart);
	}
	
	@GetMapping("/{userId}")
	public List<Cart> getCartByUser(@PathVariable Long userId){
		return cartService.getCartByUser(userId);
	}
	
	@DeleteMapping("/remove/{id}")
	public String removeCartItem(@PathVariable Long id) {

	    return cartService.removeCartItem(id);

	}
	
	@PutMapping("/update/{id}/{quantity}")
	public Cart updateQuantity(
	        @PathVariable Long id,
	        @PathVariable int quantity
	) {

	    return cartService.updateQuantity(
	            id,
	            quantity
	    );
	}
	
	@DeleteMapping(
	        "/clear/{userId}"
	)
	public String clearCart(
	        @PathVariable
	        Long userId
	){

	    return cartService
	            .clearCart(
	                userId
	            );
	}
}
