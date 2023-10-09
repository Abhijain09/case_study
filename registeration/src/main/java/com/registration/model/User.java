package com.registration.model;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name="UserDetails")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
//	@NotEmpty(message = "Name must not be Empty")
//    @Size(min = 4 , message = "Name must be min of 4 characters !!")
	private String name;
	

	private int age;
	
//	@NotEmpty(message = "Invalid Phone number: Empty number")
//	@NotNull(message = "Invalid Phone number: Number is NULL")
//	@Pattern(regexp = "\\d{10}", message = "Invalid phone number")
	private String phoneNumber;
	 
	
//	@NotEmpty
//	@Size(min = 3 , max = 50 , message = "Address must be min of 20 chars and max of 50 characters")
	private String address;
	
//	@NotNull
//	@NotEmpty(message = "Enter the gender cannot be empty")
	private String gender;
	
//	@NotNull(message = "Username can not be null")
//	@NotEmpty(message = "Username must be filled")
//	@Column(unique = true)
//    @Size(min = 4 , message = "Usename is not valid / User is already exist")
	private String username;
	
//	@NotEmpty
//	@Size(min = 3 , max = 10 , message = "Password must be min of 3 chars and max of 10 characters")
	private String password;
	
//	@NotNull(message ="E-mail cannot be NULL")
//	@NotEmpty(message ="E-mail cannot be Empty")
//	@Email(message = "Email address is not valid !!")
	private String email;
		
	
}