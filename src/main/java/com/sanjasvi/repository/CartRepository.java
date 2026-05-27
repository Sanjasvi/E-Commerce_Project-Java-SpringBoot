package com.sanjasvi.repository;

import java.util.List;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sanjasvi.entity.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long>{
	List<Cart> findByUserId(Long userId);
	
	@Transactional
    @Modifying
	void deleteByUserId(Long userId);
}
