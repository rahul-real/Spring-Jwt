/**
 * 
 */
package com.rahul.spring.security.service;

import java.awt.RenderingHints.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

/**
 * @author rahul
   @since  27-Jan-2024 2024 2:45:50 pm
 */
@Component
public class JwtHelper {
	
	private static final SecretKey SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);
	private static final int MINUTES = 60;
	/**
	 * @param email
	 * @return
	 */
	public String generateToken(String email) {
		var now = Instant.now();
	    return Jwts.builder()
	            .setSubject(email)
	            .setIssuedAt(Date.from(now))
	            .setExpiration(Date.from(now.plus(MINUTES, ChronoUnit.MINUTES)))
	            .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
	            .compact();
	}

}
