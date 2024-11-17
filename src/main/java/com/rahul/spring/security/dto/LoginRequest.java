/**
 * 
 */
package com.rahul.spring.security.dto;

import lombok.Data;

/**
 * @author rahul
   @since  27-Jan-2024 2024 2:37:13 pm
 */
@Data
public class LoginRequest {
	
	private String email;
	
	private String password;

}
