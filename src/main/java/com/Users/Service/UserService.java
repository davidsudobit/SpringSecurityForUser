package com.Users.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.Users.Additional.Users;
import com.Users.CustomException.AlreadyExistsException;
import com.Users.CustomException.InsufficientException;
import com.Users.CustomException.NotAuthorizedException;
import com.Users.CustomException.NotFoundException;
import com.Users.Dao.UserRepository;
import com.Users.Model.User;
import com.Users.ResponseBody.ResponseUser;

@Service
public class UserService implements IUserService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public ResponseEntity<ResponseUser> addUser(User user) throws NotFoundException,NotAuthorizedException,InsufficientException,AlreadyExistsException,Exception{
		
		if(user!=null) {
			
			if(!(isMobileNoExists(Long.valueOf(user.getUserMobileNo()))||isEmailExists(user.getUserEmail()))) {
				
				user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
				user.setUser(Users.CUSTOMER);
				user=userRepository.save(user);
				
				return ResponseEntity.ok(new ResponseUser(user.getUserId(),user.getUserName(),Long.valueOf(user.getUserMobileNo()),user.getUserEmail()));
				
			}
			
			throw new AlreadyExistsException("User with the same mobile number or email already exists");
			
		}
		
		throw new InsufficientException("User can't be null");
		
	}
	
	@Override
	public ResponseEntity<ResponseUser> updateUser(User user) throws NotFoundException,NotAuthorizedException,InsufficientException,AlreadyExistsException,Exception{
		
		if(userRepository.findAll().stream().filter(u->(u.getUserMobileNo().equals(user.getUserMobileNo())||u.getUserEmail().equals(user.getUserEmail()))&&!u.getUserId().equals(user.getUserId())).count()==0) {
			
			User forSave=getUserById(user.getUserId());
			
			forSave.setUserMobileNo(user.getUserMobileNo());
			forSave.setUserName(user.getUserName());
			forSave.setUserEmail(user.getUserEmail());
			
			forSave=userRepository.save(forSave);
			
			return ResponseEntity.ok(new ResponseUser(forSave.getUserId(),forSave.getUserName(),Long.valueOf(forSave.getUserMobileNo()),forSave.getUserEmail()));
			
		}
		
		throw new AlreadyExistsException("User with the same mobile number or email already exists");
		
	}
	
	@Override
	public ResponseEntity<ResponseUser> updateUserName(Long userId,String userName) throws NotFoundException,NotAuthorizedException,InsufficientException,AlreadyExistsException,Exception{
		
			User forSave=getUserById(userId);
			forSave.setUserName(userName);
			forSave=userRepository.save(forSave);
			
			return ResponseEntity.ok(new ResponseUser(forSave.getUserId(),forSave.getUserName(),Long.valueOf(forSave.getUserMobileNo()),forSave.getUserEmail()));
		
	}
	
	@Override
	public ResponseEntity<ResponseUser> updateUserMobileNo(Long userId,String mobileNo) throws NotFoundException,NotAuthorizedException,InsufficientException,AlreadyExistsException,Exception{
		
		if(!isMobileNoExists(Long.valueOf(mobileNo))) {
			
			User forSave=getUserById(userId);
			forSave.setUserMobileNo(String.valueOf(mobileNo));
			
			forSave=userRepository.save(forSave);
			
			return ResponseEntity.ok(new ResponseUser(forSave.getUserId(),forSave.getUserName(),Long.valueOf(forSave.getUserMobileNo()),forSave.getUserEmail()));
		}
		
		throw new AlreadyExistsException("Mobile no already exists");
		
	}
	
	@Override
	public ResponseEntity<ResponseUser> updateUserEmail(Long userId,String email) throws NotFoundException,NotAuthorizedException,InsufficientException,AlreadyExistsException,Exception{
		
		if(!isEmailExists(email)) {
			
			User forSave=getUserById(userId);
			forSave.setUserEmail(email);
			
			forSave=userRepository.save(forSave);
			
			return ResponseEntity.ok(new ResponseUser(forSave.getUserId(),forSave.getUserName(),Long.valueOf(forSave.getUserMobileNo()),forSave.getUserEmail()));
		}
		
		throw new AlreadyExistsException("Email already exists");
		
	}
	
	@Override
	public ResponseEntity<ResponseUser> deleteUser(Long userId) throws NotFoundException,NotAuthorizedException,InsufficientException,AlreadyExistsException,Exception{
		
		User user=getUserById(userId);
		userRepository.delete(user);
		
		return ResponseEntity.ok(new ResponseUser(user.getUserId(),user.getUserName(),Long.valueOf(user.getUserMobileNo()),user.getUserEmail()));
	
	}	
	
	@Override
	public ResponseEntity<ResponseUser> getUser(Long userId) throws NotFoundException,NotAuthorizedException,InsufficientException,AlreadyExistsException,Exception{
		
			User user=getUserById(userId);
			
			return ResponseEntity.ok(new ResponseUser(user.getUserId(),user.getUserName(),Long.valueOf(user.getUserMobileNo()),user.getUserEmail()));
		
	}
	
	@Override
	public ResponseEntity<User> getUserWithRoleById(Long userId)
			throws NotFoundException, NotAuthorizedException, InsufficientException, AlreadyExistsException, Exception {
		
		return ResponseEntity.ok(getUserById(userId));
		
	}
	
	@Override
	public ResponseEntity<ResponseUser> getUserByMobileNo(Long mobileNo)
			throws NotFoundException, NotAuthorizedException, InsufficientException, AlreadyExistsException, Exception {
		
		if(isMobileNoExists(mobileNo)) {
			User forRes=userRepository.findByUserMobileNo(String.valueOf(mobileNo)).get();
			return ResponseEntity.ok(new ResponseUser(forRes.getUserId(),forRes.getUserName(),Long.valueOf(forRes.getUserMobileNo()),forRes.getUserEmail()));
		}
		
		throw new NotFoundException("User not found");
		
	}
	
	@Override
	public ResponseEntity<List<ResponseUser>> getAllUsers() throws NotFoundException,NotAuthorizedException,InsufficientException,AlreadyExistsException,Exception{
		
		return ResponseEntity.ok(userRepository.findAll().stream().map(user->{
			
			return new ResponseUser(user.getUserId(),user.getUserName(),Long.valueOf(user.getUserMobileNo()),user.getUserEmail());
			
		}).collect(Collectors.toList()));
		
	}
	
	private boolean isMobileNoExists(Long mobileNo) {
		
		if(userRepository.findByUserMobileNo(String.valueOf(mobileNo)).isEmpty()) {
			return false;
		}
		
		return true;
		
	}
	
	private boolean isEmailExists(String email) {
		
		if(userRepository.findByUserEmail(email).isEmpty()) {
			return false;
		}
		
		return true;
		
	}
	
	private	User getUserById(Long userId)throws NotFoundException,NotAuthorizedException,InsufficientException,AlreadyExistsException,Exception {
		
		Optional<User> op1=userRepository.findById(userId);
		
		if(op1.isPresent()) {
			return op1.get();
		}
		
		throw new NotFoundException("User not found");
		
	}
	
}
