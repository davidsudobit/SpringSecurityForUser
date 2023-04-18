package com.Users.Dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.Users.Model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	@Query(value="SELECT * FROM Users u WHERE u.user_mobile_no=?1",nativeQuery = true)
	public Optional<User> findByUserMobileNo(String userMobileNo);
	
	@Query(value="SELECT * FROM USERS u WHERE u.user_email=:userEmail",nativeQuery=true)
	public Optional<User> findByUserEmail(String userEmail);

}
