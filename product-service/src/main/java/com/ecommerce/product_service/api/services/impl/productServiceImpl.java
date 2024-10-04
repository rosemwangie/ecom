package com.ecommerce.product_service.api.services.impl;

import com.ecommerce.product_service.api.database.entities.Product;
import com.ecommerce.product_service.api.database.repository.ProductRepository;
import com.ecommerce.product_service.api.hateaoas.PageApiResponse;
import com.ecommerce.product_service.api.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class productServiceImpl implements ProductService {

   private final ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    @Override
    public PageApiResponse<List<Product>> getAllProductsWithPagination(int offset, int size){
        Pageable pageable = PageRequest.of(offset, size);
        Page<Product> productPage = productRepository.findAll(pageable);

        return new PageApiResponse<>(
                true,
                "Success",
                productPage.getContent(),
                productPage.getTotalPages(),
                productPage.getNumber() + 1,
                productPage.hasNext()

        );
    }

    @Override
    public Product addProduct(Product product){
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long id, Product productDetails){
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product Not found"));
        product.setName(productDetails.getName());
        product.setDescription(productDetails.getDescription());
        product.setPrice(productDetails.getPrice());
        return productRepository.save(product);
    }
    @Override
    public void deleteProduct(Long id){
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found!"));
        productRepository.delete(product);
    }
}
