package com.sanjasvi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sanjasvi.entity.Product;
import com.sanjasvi.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    // Add Product
    public Product addProduct(Product product) {

        return productRepository.save(product);
    }

    // Get All Products
    public List<Product> getAllProducts() {

        return productRepository.findAll();
    }

    // Update Product
    public Product updateProduct(
            Long id,
            Product updatedProduct
    ) {

        Optional<Product> optionalProduct =
                productRepository.findById(id);

        if(optionalProduct.isPresent()) {

            Product existingProduct =
                    optionalProduct.get();

            existingProduct.setName(updatedProduct.getName());
            existingProduct.setDescription(updatedProduct.getDescription());
            existingProduct.setPrice(updatedProduct.getPrice());
            existingProduct.setCategory(updatedProduct.getCategory());
            existingProduct.setImageUrl(updatedProduct.getImageUrl());

            return productRepository.save(existingProduct);
        }

        return null;
    }

    // Delete Product
    public String deleteProduct(Long id) {

        Optional<Product> optionalProduct =
                productRepository.findById(id);

        if(optionalProduct.isPresent()) {

            productRepository.deleteById(id);

            return "Product Deleted Successfully";
        }

        return "Product Not Found";
    }
    
    public Product getProductById(Long id) {

        return productRepository.findById(id).orElse(null);
    }
}