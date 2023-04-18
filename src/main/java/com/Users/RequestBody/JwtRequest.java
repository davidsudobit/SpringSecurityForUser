package com.Users.RequestBody;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtRequest implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5621169592961945503L;
	
	private String username;
	private String password;

}
