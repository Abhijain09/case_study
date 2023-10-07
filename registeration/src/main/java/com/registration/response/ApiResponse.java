package com.registration.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {
	
	// A message describing the result of the API operation
	private String message;
	
	// A boolean flag indicating the success or failure of the operation
	private boolean success;
	
}