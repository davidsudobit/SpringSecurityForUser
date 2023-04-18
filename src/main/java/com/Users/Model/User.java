package com.Users.Model;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;

import com.Users.Additional.Users;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1668625364821740384L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="user_id")
	private Long userId;
	
	@NotNull(message="UserName can't be null")
	@NotBlank(message="UserName can't be blank")
	@Column(name="user_name")
	private String userName;
	
	@NotNull(message="User Mobile.No can't be null")
	@NotBlank(message="User Mobile.No can't be blank")
	@Length(min = 10,max = 10,message ="User Mobile.No must be 10 digits")
	@Column(name="user_mobile_no")
	private String userMobileNo;
	
	@Email(message="Invalid email, should be like xyz@abc.com")
	@NotNull(message="User Email can't be null")
	@NotBlank(message="User Email can't be blank")
	@Column(name="user_email")
	private String userEmail;
	
	@NotNull(message="User Password can't be null")
	@NotBlank(message="User Password can't be blank")
	@Column(name="user_password")
	private String userPassword;
	
	@Column(name="user_level")
	private Users user;

}
