package com.ecommerce.product_service.service;

import com.ecommerce.product_service.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import com.ecommerce.product_service.repository.ProductRepository;


import java.util.List;

@Service
 public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Cacheable(value = "products")
    public List<Product> getAllProducts(){
        System.out.println("Fetching products from the database...");
        return productRepository.findAll();
    }
    public Product addProduct(Product product){
        return productRepository.save(product);
    }
    public Product updateProduct(Long id, Product productDetails) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        product.setName(productDetails.getName());
        product.setDescription(productDetails.getDescription());
        product.setPrice(productDetails.getPrice());
        return productRepository.save(product);
    }
//    @CacheEvict(value = "products", allEntries = true)
    public void deleteProduct(Long id){
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found!"));
        productRepository.delete(product);
    }
}
