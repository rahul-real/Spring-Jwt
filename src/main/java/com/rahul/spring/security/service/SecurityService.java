/**
 * 
 */
package com.rahul.spring.security.service;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.rahul.spring.security.dto.RegisterUserDetails;
import com.rahul.spring.security.dto.User;
import com.rahul.spring.security.repository.SpringSecurityRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * @author rahul
   @since  26-Jan-2024 2024 6:44:10 pm
 */
@Component
@Slf4j
public class SecurityService {
	
	@Autowired
	SpringSecurityRepository repository;
	
	private PasswordEncoder passwordEncoder;

	/**
	 * @param txnNum
	 * @param userDetails
	 * @throws Exception 
	 */
	public void signUp(String txnNum, RegisterUserDetails userDetails) throws Exception {
		String email = userDetails.getEmail();
	    String existingUser = repository.findByEmail(email);
	    if (StringUtils.isNotBlank(existingUser)) {
	        throw new Exception(String.format("User with the email address {} already exists.", email));
	      }
	    
	    String hashedPassword = passwordEncoder.encode(userDetails.getPassword());
	    User user = new User();
	    user.setEmail(email);
	    user.setName(userDetails.getName());
	    user.setPassword(hashedPassword);
	    
		
	}
	
	

}
