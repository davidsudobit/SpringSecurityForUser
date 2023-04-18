package com.Users.Service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.Users.Dao.UserRepository;

@Service
public class JwtUserService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		Optional<com.Users.Model.User> op1=userRepository.findByUserEmail(email);
		
		if(op1.isPresent()) {
			com.Users.Model.User user=op1.get();
			return new User(user.getUserEmail(), user.getUserPassword(), new ArrayList<>());
		}
		
		throw new UsernameNotFoundException("User with the email not found");
		
	}

}
