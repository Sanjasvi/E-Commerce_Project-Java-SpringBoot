package com.sanjasvi.controller;

import java.util.List;
import com.sanjasvi.security.JwtUtil;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//import java.util.List;

import com.sanjasvi.dto.LoginRequest;
import com.sanjasvi.entity.User;
import com.sanjasvi.repository.UserRepository;

@RestController
@RequestMapping("/users")
@CrossOrigin("*")
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	@PostMapping("/register")
	public User registerUser(@RequestBody User user) {

	    if (user.getRole() == null || user.getRole().isEmpty()) {
	        user.setRole("USER");
	    }

	    return userRepository.save(user);
	}
	
	@GetMapping
	public List<User> getAllUsers(){
		return userRepository.findAll();
	}
	
	//Login
	
	@PostMapping("/login")
	public Map<String, Object> loginUser(@RequestBody LoginRequest loginRequest) {

	    User user = userRepository.findByEmail(loginRequest.getEmail());

	    Map<String, Object> response = new HashMap<>();

	    if (user != null && user.getPassword().equals(loginRequest.getPassword())) {

	        response.put(
	                "token",
	                JwtUtil.generateToken(user.getEmail(), user.getRole())
	        );

	        response.put("userId", user.getId());
	        response.put("name", user.getName());
	        response.put("role", user.getRole());

	    } else {
	        response.put("message", "Invalid Email or Password");
	    }

	    return response;
	}
}







