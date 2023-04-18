package com.Users.Controller;

import java.util.List;

import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.Users.CustomException.AlreadyExistsException;
import com.Users.CustomException.InsufficientException;
import com.Users.CustomException.NotAuthorizedException;
import com.Users.CustomException.NotFoundException;
import com.Users.Model.User;
import com.Users.ResponseBody.ResponseUser;
import com.Users.Service.UserService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/adduser")
	public ResponseEntity<ResponseUser> addUser(@Valid @RequestBody User user) throws NotFoundException,NotAuthorizedException,InsufficientException,AlreadyExistsException,Exception{
		
		return userService.addUser(user);
		
	}
	
	@PutMapping("/updateuser")
	public ResponseEntity<ResponseUser> updateUser(@Valid @RequestBody User user) throws NotFoundException,NotAuthorizedException,InsufficientException,AlreadyExistsException,Exception{
		
		return userService.updateUser(user);
		
	}
	
	@PatchMapping("/updateusername/{userId}/{userName}")
	public ResponseEntity<ResponseUser> updateUserName(@PathVariable(name="userId",required=true) Long userId,@PathVariable(name="userName",required=true) @NotBlank(message="UserName can't be blank") String userName) throws NotFoundException,NotAuthorizedException,InsufficientException,AlreadyExistsException,Exception{
		
		return userService.updateUserName(userId, userName);
		
	}
	
	@PatchMapping("/updateusermobileno/{userId}/{mobileNo}")
	public ResponseEntity<ResponseUser> updateUserMobileNo(@PathVariable(name="userId",required=true) Long userId,@PathVariable(name="mobileNo",required=true) @NotBlank(message="User Mobile.No can't be blank") @Length(min = 10,max = 10,message ="User Mobile.No must be 10 digits") String mobileNo) throws NotFoundException,NotAuthorizedException,InsufficientException,AlreadyExistsException,Exception{
		
		return userService.updateUserMobileNo(userId, mobileNo);
		
	}
	
	@PatchMapping("/updateuseremail/{userId}/{email}")
	public ResponseEntity<ResponseUser> updateUserEmail(@PathVariable(name="userId",required=true) Long userId,@PathVariable(name="email",required=true) @NotBlank(message="User Email can't be blank") String email) throws NotFoundException,NotAuthorizedException,InsufficientException,AlreadyExistsException,Exception{
		
		return userService.updateUserEmail(userId, email);
		
	}
	
	@DeleteMapping("/deleteuser/{userId}")
	public ResponseEntity<ResponseUser> deleteUser(@PathVariable(name="userId",required=true) Long userId) throws NotFoundException,NotAuthorizedException,InsufficientException,AlreadyExistsException,Exception{
		
		return userService.deleteUser(userId);
		
	}
	
	
	@GetMapping("/getuserbyid/{userId}")
	public ResponseEntity<ResponseUser> getUserById(@PathVariable(name="userId",required=true) Long userId) throws NotFoundException,NotAuthorizedException,InsufficientException,AlreadyExistsException,Exception{
		
		return userService.getUser(userId);
		
	}
	
	@GetMapping("/getuserwithrolebyid/{userId}")
	public ResponseEntity<User> getUserWithRoleById(@PathVariable(name="userId",required=true) Long userId) throws NotFoundException,NotAuthorizedException,InsufficientException,AlreadyExistsException,Exception{
		
		return userService.getUserWithRoleById(userId);
		
	}
	
	@GetMapping("/getuserbymobileno/{mobileNo}")
	public ResponseEntity<ResponseUser> getUserByMobileNo(@PathVariable(name="mobileNo",required=true) Long mobileNo) throws NotFoundException,NotAuthorizedException,InsufficientException,AlreadyExistsException,Exception{
		
		return userService.getUserByMobileNo(mobileNo);
		
	}
	
	@GetMapping("/getalluser")
	public ResponseEntity<List<ResponseUser>> getAllUsers() throws NotFoundException,NotAuthorizedException,InsufficientException,AlreadyExistsException,Exception{
		
		return userService.getAllUsers();
		
	}

}
