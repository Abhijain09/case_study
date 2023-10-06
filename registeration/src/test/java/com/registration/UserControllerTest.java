//package com.registration;
//
//import java.util.ArrayList;
//import java.util.List;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.eq;
//import static org.mockito.BDDMockito.given;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import com.registration.controller.UserController;
//import com.registration.model.User;
//import com.registration.services.UserServices;
//
//@WebMvcTest(UserController.class)
//@AutoConfigureMockMvc
//public class UserControllerTest {
//
//	 @Autowired
//	    private MockMvc mockMvc;
//
//	    @MockBean
//	    private UserServices userService;
//	    
//	    //Test creating a new user via POST request.
//	    @Test
//	    public void testCreateUser() throws Exception {
//	        User user = new User();
//	        user.setName("testuser");
//	        user.setPassword("testpassw");
//	        user.setUsername("testing");
//	        user.setAge(12);
//	        user.setGender("male");;
//	        user.setPhoneNumber("8784799498");
//	        given(userService.addUser(any(User.class))).willReturn(user);
//
//	     // Perform POST request and validate the response
//	        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/users/")
//	                .contentType(MediaType.APPLICATION_JSON)
//	                .content("{\"name\":\"testuser\",\"password\":\"testpassw\",\"username\":\"testing\",\"phoneNumber\":\"8784799498\"}"))
//	        .andExpect(MockMvcResultMatchers.status().isCreated())
//	        .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("testuser"))
//	        .andExpect(MockMvcResultMatchers.jsonPath("$.password").value("testpassw"))
//	        .andExpect(MockMvcResultMatchers.jsonPath("$.username").value("testing"))
//	        .andExpect(MockMvcResultMatchers.jsonPath("$.phoneNumber").value("8784799498"));
//	}
//	    
//	    //Test retrieving a list of all users via GET request.
//	    @Test
//	    public void testGetAllUser() throws Exception {
//	    	
//	    	 // Test listing all users and response validation
//	        List<User> userList = new ArrayList<>();
//	        userList.add(new User(1, "user1", 12, "9775446788","agra","male", "tester", "password1"));
//	        userList.add(new User(2,"user2", 20, "987654321","mathura","male", "frzitester", "password23"));
//
//	        given(userService.getUsers()).willReturn(userList);
//
//	        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/users/"))
//	                .andExpect(MockMvcResultMatchers.status().isOk())
//	                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("user1"))
//	                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("user2"));
//	    }
//
//	    // Test updating an existing user via PUT request.
//	    @Test
//	    public void testUpdateUser() throws Exception {
//	    	
//	    	// Test updating user information and response validation
//	    	 User user = new User();
//	         user.setName("tesuser");
//	         user.setPassword("testpassw");
//	         user.setAge(18);
//	         user.setUsername("testuser");
//	         user.setPhoneNumber("8784799498");
//	         given(userService.updateUser(eq("testuser"),any(User.class))).willReturn(user);
//
//	         mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/users/testuser")
//	                 .contentType(MediaType.APPLICATION_JSON)
//	                 .content("{\"name\":\"tesuser\",\"password\":\"testpassw\",\"username\":\"testuser\",\"phoneNumber\":\"8784799498\"}"))
//	         .andExpect(MockMvcResultMatchers.status().isOk())
//	         .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("tesuser"))
//	         .andExpect(MockMvcResultMatchers.jsonPath("$.password").value("testpassw"))
//	         .andExpect(MockMvcResultMatchers.jsonPath("$.username").value("testuser"))
//	         .andExpect(MockMvcResultMatchers.jsonPath("$.phoneNumber").value("8784799498"));
//	    }
//
//	    //Test deleting a user via DELETE request.
////	    @Test
////	    public void testDeleteUser() throws Exception {
////	    	
////	    	// Test deleting a user and response validation
////	    	User user = new User(1, "user1", 12, "9775446788","agra","male", "tester", "password1");
////	        Mockito.doNothing().when(userService).deleteUserByUsername(eq("tester"));
//////	    	given(userService.deleteUserByUsername("tester"));
////
////	        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/users/tester"))
////	                .andExpect(MockMvcResultMatchers.status().isOk())
////	                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("User deleted Successfully"))
////	                .andExpect(MockMvcResultMatchers.jsonPath("$.success").value(true));
////	    }
//
//	    //Test retrieving a single user by ID via GET request.
////	    @Test
////	    public void testGetUser() throws Exception {
////	    	
////	    	// Test getting a single user by ID and response validation
//////	        User user = new User(1, "user1", 12, "9775446788","agra","male", "tester", "password1");
//////
//////	        given(userService.getUserByUsername(eq("tester")).get()).willReturn(user);
//////
//////	        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/users/tester"))
//////	                .andExpect(MockMvcResultMatchers.status().isOk())
//////	                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("user1"));
////	        
////	        
////	       
////	       
////
//////	        given(userService.getUsers()).willReturn(userList);
//////	        given(userService.getUserByUsername("tester").get()).willReturn(user);
//////
//////	        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/users/tester"))
//////	                .andExpect(MockMvcResultMatchers.status().isOk())
//////	                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("user1"));
////	    	User user = new User();
////	        user.setName("testuser");
////	        user.setPassword("testpassw");
////	        user.setUsername("testing");
////	        user.setAge(12);
////	        user.setGender("male");;
////	        user.setPhoneNumber("8784799498");
////	        given(userService.getUserByUsername(user.getUsername()).get()).willReturn(user);
////	        
////	        
//////	        given(userService.getUserByUsername(eq("testuser")).get()).willReturn(user);
////
////	         mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/users/testing")
////	                 .contentType(MediaType.APPLICATION_JSON))
////	         .andExpect(MockMvcResultMatchers.status().isOk())
////	         .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("tesuser"));
////	    }
//	
//}
