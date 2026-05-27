package com.sanjasvi.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import com.sanjasvi.entity.Product;
import com.sanjasvi.service.ProductService;

@RestController
@CrossOrigin("*")
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@PostMapping
	public Product addProduct(@RequestBody Product product) {
		return productService.addProduct(product);
	}
	
	@GetMapping
	public List<Product> getAllProducts(){
		return productService.getAllProducts();
	}
	
	@GetMapping("/{id}")
	public Product getProductById(@PathVariable Long id) {

	    return productService.getProductById(id);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteProduct(
	        @PathVariable Long id
	) {

	    String response =
	            productService.deleteProduct(id);

	    if(response.equals("Product Deleted Successfully")) {

	        return ResponseEntity.ok(response);
	    }

	    return ResponseEntity
	            .badRequest()
	            .body(response);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateProduct(
	        @PathVariable Long id,
	        @RequestBody Product updatedProduct
	) {

	    Product product =
	            productService.updateProduct(id, updatedProduct);

	    if(product != null) {

	        return ResponseEntity.ok(product);
	    }

	    return ResponseEntity
	            .badRequest()
	            .body("Product Not Found");
	}
	
	
}
