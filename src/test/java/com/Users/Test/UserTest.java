package com.Users.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.Users.Additional.Users;
import com.Users.Dao.UserRepository;
import com.Users.Model.User;
import com.Users.ResponseBody.ResponseUser;
import com.Users.Service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest
public class UserTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@MockBean
	private UserService userService;
	
	@MockBean
	private UserRepository userRepository;
	
	@Test
	public void createUser_ResponseUser() throws Exception{
		
		given(userService.addUser(any(User.class))).willReturn(ResponseEntity.ok(new ResponseUser(Long.valueOf(1),"Kevin",Long.valueOf("9994876497"),"kevin@gmail.com")));
		
		ResultActions response=mockMvc.perform(post("/user/adduser")
				.contentType(MediaType.APPLICATION_JSON)
	            .content(objectMapper.writeValueAsString(new User(Long.valueOf(1),"Kevin","9994876497","kevin@gmail.com","Kevin@123",Users.CUSTOMER))));
			
		response.andDo(print())
		.andExpect(status().is2xxSuccessful())
		.andExpect(jsonPath("$.userName",is("Kevin")))
		.andExpect(jsonPath("$.userEmail",is("kevin@gmail.com")));
		
	}

}
