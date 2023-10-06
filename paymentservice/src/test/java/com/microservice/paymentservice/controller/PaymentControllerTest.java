package com.microservice.paymentservice.controller;

import org.junit.jupiter.api.Test;
import com.microservice.paymentservice.payload.PaymentRequest;
import com.microservice.paymentservice.payload.PaymentResponse;
import com.microservice.paymentservice.service.PaymentService;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.*;

class PaymentControllerTest {


		@Mock
	    private PaymentService paymentService;

	    @InjectMocks
	    private PaymentController paymentController;

	    @BeforeEach
	    void setUp() {
	        MockitoAnnotations.openMocks(this);
	    }

	    @Test
	    void testDoPayment() {
	        // Prepare a sample PaymentRequest
	        PaymentRequest paymentRequest = new PaymentRequest();
	        paymentRequest.setAmount(100); // Set any necessary fields

	        // Mock the behavior of the PaymentService
	        when(paymentService.doPayment(paymentRequest)).thenReturn(12345L); // Simulate a payment with an ID of 12345

	        // Call the controller method
	        ResponseEntity<Long> responseEntity = paymentController.doPayment(paymentRequest);

	        // Verify that the controller called the service method and returned the expected response
	        verify(paymentService, times(1)).doPayment(paymentRequest);
	        assert responseEntity.getStatusCode() == HttpStatus.OK;
	        assert responseEntity.getBody() == 12345L; // The expected payment ID
	    }

	    @Test
	    void testGetPaymentDetailsByOrderId() {
	        // Prepare a sample orderId
	        long orderId = 123;

	        // Prepare a sample PaymentResponse
	        PaymentResponse paymentResponse = new PaymentResponse();
	        paymentResponse.setPaymentId(456); // Set any necessary fields

	        // Mock the behavior of the PaymentService
	        when(paymentService.getPaymentDetailsByOrderId(orderId)).thenReturn(paymentResponse);

	        // Call the controller method
	        ResponseEntity<PaymentResponse> responseEntity = paymentController.getPaymentDetailsByOrderId(orderId);

	        // Verify that the controller called the service method and returned the expected response
	        verify(paymentService, times(1)).getPaymentDetailsByOrderId(orderId);
	        assert responseEntity.getStatusCode() == HttpStatus.OK;
	        assert responseEntity.getBody() == paymentResponse; // The expected PaymentResponse
	    }



}







