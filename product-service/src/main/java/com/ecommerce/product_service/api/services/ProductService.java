package com.ecommerce.product_service.api.services;

import com.ecommerce.product_service.api.database.entities.Product;
import com.ecommerce.product_service.api.hateaoas.PageApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.ecommerce.product_service.api.database.repository.ProductRepository;


import java.util.List;

@Service
 public class ProductService {
    @Autowired
    private ProductRepository productRepository;


    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public PageApiResponse<List<Product>> getAllProductsWithPagination(int offset, int size) {
        PageRequest pageable = PageRequest.of(offset, size);
        Page<Product> productPage = productRepository.findAll(pageable);

        // Create a PageApiResponse with product data and pagination metadata
        var pageResponse = new PageApiResponse<>(
                true,
                "Success",
                productPage.getContent(),
                productPage.getTotalPages(),
                productPage.getNumber() + 1,  // Current page (0-indexed, so add 1 for 1-indexed)
                productPage.hasNext()
        );

        return pageResponse;
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
