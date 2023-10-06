package com.microservice.productservice.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.microservice.productservice.entity.Product;
import com.microservice.productservice.payload.response.ProductResponse;
import com.microservice.productservice.repository.ProductRepository;
import com.microservice.productservice.service.impl.ProductServiceImpl;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;



public class ProductServiceTest {
	
	
	@InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private ProductRepository productRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }
    
    
//    @Test
//    public void testAddProduct() {
//        // Create a ProductRequest object
//        ProductRequest request = new ProductRequest();
//        request.setName("Test Product");
//        request.setQuantity(10);
//        request.setPrice(100);
//
//        // Mock the repository's behavior when saving a product
//        Product savedProduct = new Product();
//        savedProduct.setProductId(1L);
//        when(productRepository.save(any(Product.class))).thenReturn(savedProduct);
//
//        // Call the service method
//        long productId = productService.addProduct(request);
//
//        // Assertions
//        assertEquals(1L, productId);
//    }
   
    
    @Test
    public void testGetProductById() {
        // Mock the repository's behavior when finding a product by ID
        Product product = new Product();
        product.setProductId(1L);
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        // Call the service method
        ProductResponse response = productService.getProductById(1L);
        // Assertions
        assertEquals(1L, response.getProductId());
    }
}
