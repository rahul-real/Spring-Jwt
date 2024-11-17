/**
 * 
 */
package com.rahul.spring.security.repository;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.rahul.spring.security.dto.User;

/**
 * @author rahul
   @since  27-Jan-2024 2024 1:36:29 pm
 */
@Component
public interface SpringSecurityRepository {

	/**
	 * @param email
	 * @return
	 */
	String findByEmail(String email);

}
