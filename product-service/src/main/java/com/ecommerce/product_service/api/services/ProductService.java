package com.ecommerce.product_service.api.services;

import com.ecommerce.product_service.api.database.entities.Product;
import com.ecommerce.product_service.api.hateaoas.PageApiResponse;



import java.util.List;

 public interface ProductService {
     List<Product> getAllProducts();

     PageApiResponse<List<Product>> getAllProductsWithPagination(int offset, int size);

     Product addProduct(Product product);
     Product updateProduct(Long id, Product productDetails);

     void deleteProduct(Long id);
 }
