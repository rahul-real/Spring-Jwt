/**
 * 
 */
package com.rahul.spring.security.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @author rahul
   @since  26-Jan-2024 2024 6:34:19 pm
 */
@Data
public class RegisterUserDetails {
	
	@NotBlank(message = "name cannot be blank")
	private String name;
	
	@NotBlank(message = "password cannot be blank")
	private String password;
	
	@NotBlank(message = "email cannot be blank")
	private String email;

}
