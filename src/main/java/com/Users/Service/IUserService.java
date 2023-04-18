package com.Users.Service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.Users.CustomException.AlreadyExistsException;
import com.Users.CustomException.InsufficientException;
import com.Users.CustomException.NotAuthorizedException;
import com.Users.CustomException.NotFoundException;
import com.Users.Model.User;
import com.Users.ResponseBody.ResponseUser;

public interface IUserService {
	
	public ResponseEntity<ResponseUser> addUser(User user) throws NotFoundException,NotAuthorizedException,InsufficientException,AlreadyExistsException,Exception;
	
	public ResponseEntity<ResponseUser> updateUser(User user) throws NotFoundException,NotAuthorizedException,InsufficientException,AlreadyExistsException,Exception;
	
	public ResponseEntity<ResponseUser> updateUserName(Long userId,String userName) throws NotFoundException,NotAuthorizedException,InsufficientException,AlreadyExistsException,Exception;
	
	public ResponseEntity<ResponseUser> updateUserMobileNo(Long userId,String mobileNo) throws NotFoundException,NotAuthorizedException,InsufficientException,AlreadyExistsException,Exception;
	
	public ResponseEntity<ResponseUser> updateUserEmail(Long userId,String email) throws NotFoundException,NotAuthorizedException,InsufficientException,AlreadyExistsException,Exception;
	
	public ResponseEntity<ResponseUser> deleteUser(Long userId) throws NotFoundException,NotAuthorizedException,InsufficientException,AlreadyExistsException,Exception;
	
	public ResponseEntity<ResponseUser> getUser(Long userId) throws NotFoundException,NotAuthorizedException,InsufficientException,AlreadyExistsException,Exception;
	
	public ResponseEntity<User> getUserWithRoleById(Long userId) throws NotFoundException,NotAuthorizedException,InsufficientException,AlreadyExistsException,Exception;
	
	public ResponseEntity<ResponseUser> getUserByMobileNo(Long mobileNo) throws NotFoundException,NotAuthorizedException,InsufficientException,AlreadyExistsException,Exception;
	
	public ResponseEntity<List<ResponseUser>> getAllUsers() throws NotFoundException,NotAuthorizedException,InsufficientException,AlreadyExistsException,Exception;

}
