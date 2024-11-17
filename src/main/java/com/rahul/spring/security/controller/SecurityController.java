/**
 * 
 */
package com.rahul.spring.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rahul.spring.security.dto.LoginRequest;
import com.rahul.spring.security.dto.LoginResponse;
import com.rahul.spring.security.dto.RegisterUserDetails;
import com.rahul.spring.security.service.JwtHelper;
import com.rahul.spring.security.service.SecurityService;
import com.rahul.spring.security.utils.Constants;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

/**
 * @author rahul
   @since  26-Jan-2024 2024 5:43:21 pm
 */
@Slf4j
@RestController
@Validated
@RequestMapping("/rahul")
public class SecurityController {
	
	@Autowired
	SecurityService securityService;
	
	@Autowired
	JwtHelper jwtHelper;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@GetMapping("/addNumbers")
	public ResponseEntity<?> addNumbers(@RequestParam("num1") int num1, @RequestParam("num2") int num2,HttpServletRequest request){
		String txnNum = request.getHeader(Constants.TRANSACTION_NUMBER); 
		log.info("TransactionNumber "+txnNum+" Inside SecurityController ");
		return new ResponseEntity<>(num1+num2,HttpStatus.OK);
	}

	@PostMapping("/addUser")
	public ResponseEntity<?> signUp(@RequestBody RegisterUserDetails userDetails,HttpServletRequest request) throws Exception{
		String txnNum = request.getHeader(Constants.TRANSACTION_NUMBER);
		log.info("TransactionNumber "+txnNum+" Inside SecurityController, Adding UserDetails");
		securityService.signUp(txnNum,userDetails);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@PostMapping("/login")
	public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
		String token = jwtHelper.generateToken(request.getEmail());
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
}
