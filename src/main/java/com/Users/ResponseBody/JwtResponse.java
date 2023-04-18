package com.Users.ResponseBody;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtResponse implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2332302621752635684L;
	
	private final String jwttoken;
	
	

}
