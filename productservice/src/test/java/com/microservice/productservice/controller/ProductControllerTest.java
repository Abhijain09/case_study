package com.microservice.productservice.controller;

import com.microservice.productservice.entity.Product;
import com.microservice.productservice.exception.ProductServiceCustomException;
import com.microservice.productservice.payload.request.ProductRequest;
import com.microservice.productservice.payload.response.ProductResponse;
import com.microservice.productservice.repository.ProductRepository;
import com.microservice.productservice.service.impl.ProductServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Optional;


class ProductControllerTest {
	
	 @Mock
	    private ProductRepository productRepository;

	    @InjectMocks
	    private ProductServiceImpl productService;

	    @BeforeEach
	    public void setUp() {
	        MockitoAnnotations.initMocks(this);
	    }

	    @Test
	    public void testAddProduct() {
	        // Arrange
	    	ProductRequest productRequest = ProductRequest.builder()
	    		    .name("Test Product")
	    		    .price(50L)
	    		    .quantity(10L)
	    		    .build();

	        Product savedProduct = new Product();
	        savedProduct.setProductId(1L);
	        savedProduct.setProductName(productRequest.getName());
	        savedProduct.setPrice(productRequest.getPrice() * productRequest.getQuantity());
	        savedProduct.setQuantity(productRequest.getQuantity());

	        when(productRepository.save(any(Product.class))).thenReturn(savedProduct);

	        // Act
	        long productId = productService.addProduct(productRequest);

	        // Assert
	        assertEquals(1L, productId);
	    }

	    @Test
	    public void testGetProductById() {
	        // Arrange
	        long productId = 1L;

	        Product product = new Product();
	        product.setProductId(productId);
	        product.setProductName("Test Product");
	        product.setPrice(50L);
	        product.setQuantity(10L);

	        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

	        // Act
	        ProductResponse productResponse = productService.getProductById(productId);

	        // Assert
	        assertNotNull(productResponse);
	        assertEquals(productId, productResponse.getProductId());
	    }

	    @Test
	    public void testReduceQuantity_Successful() {
	        // Arrange
	        long productId = 1L;
	        long initialQuantity = 10L;
	        long quantityToReduce = 5L;

	        Product product = new Product();
	        product.setProductId(productId);
	        product.setProductName("Test Product");
	        product.setPrice(50L);
	        product.setQuantity(initialQuantity);

	        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

	        // Act
	        productService.reduceQuantity(productId, quantityToReduce);

	        // Assert
	        assertEquals(initialQuantity - quantityToReduce, product.getQuantity());
	        verify(productRepository, times(1)).save(product);
	    }

	    @Test
	    public void testReduceQuantity_InsufficientQuantity() {
	        // Arrange
	        long productId = 1L;
	        long initialQuantity = 5L;
	        long quantityToReduce = 10L;

	        Product product = new Product();
	        product.setProductId(productId);
	        product.setProductName("Test Product");
	        product.setPrice(50L);
	        product.setQuantity(initialQuantity);

	        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

	        // Act and Assert
	        assertThrows(ProductServiceCustomException.class, () -> {
	            productService.reduceQuantity(productId, quantityToReduce);
	        });

	        // Verify that the quantity was not updated and save was not called.
	        assertEquals(initialQuantity, product.getQuantity());
	        verify(productRepository, never()).save(product);
	    }

	    @Test
	    public void testDeleteProductById_ProductExists() {
	        // Arrange
	        long productId = 1L;

	        when(productRepository.existsById(productId)).thenReturn(true);

	        // Act
	        productService.deleteProductById(productId);

	        // Verify that deleteById was called.
	        verify(productRepository, times(1)).deleteById(productId);
	    }

	    @Test
	    public void testDeleteProductById_ProductNotFound() {
	        // Arrange
	        long productId = 1L;

	        when(productRepository.existsById(productId)).thenReturn(false);

	        // Act and Assert
	        assertThrows(ProductServiceCustomException.class, () -> {
	            productService.deleteProductById(productId);
	        });

	        // Verify that deleteById was not called.
	        verify(productRepository, never()).deleteById(productId);
	    }


}