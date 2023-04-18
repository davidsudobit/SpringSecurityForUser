package com.Users.ResponseBody;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseUser {
	
	private Long userId;
	private String userName;
	private Long userMobileNo;
	private String userEmail;

}
