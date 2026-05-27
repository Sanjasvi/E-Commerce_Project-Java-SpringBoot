package com.sanjasvi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sanjasvi.entity.Cart;
import com.sanjasvi.repository.CartRepository;

@Service
public class CartService {
	 @Autowired
	    private CartRepository cartRepository;

	    // Add To Cart
	    public Cart addToCart(Cart cart) {

	        return cartRepository.save(cart);
	    }

	    // Get User Cart
	    public List<Cart> getCartByUser(Long userId) {

	        return cartRepository.findByUserId(userId);
	    }
	    
	    public String removeCartItem(Long id) {

	    	Cart cart = cartRepository.findById(id).orElse(null);

	        if(cart != null) {

	            cartRepository.delete(cart);

	            return "Item Removed From Cart";
	        }

	        return "Cart Item Not Found";
		}
	    
	    public Cart updateQuantity(
	            Long id,
	            int quantity
	    ) {

	        Cart cart =
	            cartRepository
	                .findById(id)
	                .orElse(null);

	        if(cart != null){

	            cart.setQuantity(
	                    quantity
	            );

	            return cartRepository
	                    .save(cart);
	        }

	        return null;
	    }
	    
	    @Transactional
	    public String clearCart(Long userId) {

	        cartRepository
	            .deleteByUserId(
	                userId
	            );

	        return "Cart Cleared";
	    }
}
