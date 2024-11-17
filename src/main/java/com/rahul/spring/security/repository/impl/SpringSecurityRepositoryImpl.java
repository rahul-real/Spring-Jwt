/**
 * 
 */
package com.rahul.spring.security.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rahul.spring.security.config.StoredProcedures;
import com.rahul.spring.security.repository.SpringSecurityRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;
import lombok.extern.slf4j.Slf4j;

/**
 * @author rahul
   @since  27-Jan-2024 2024 1:46:01 pm
 */
@Slf4j
@Repository
@Transactional
public class SpringSecurityRepositoryImpl implements SpringSecurityRepository{
	
	@Autowired
	EntityManagerFactory entityManagerFactory;
	
	@Autowired
	StoredProcedures procedures;

	@SuppressWarnings("unchecked")
	@Override
	public String findByEmail(String email) {
		EntityManager entityManager =null;
		StoredProcedureQuery storedProcedure =null;
		String existingEmail ="";
		try {
			entityManager = entityManagerFactory.createEntityManager();
			storedProcedure = entityManager.createStoredProcedureQuery(procedures.getGetUserData())
					.registerStoredProcedureParameter("Email", String.class, ParameterMode.IN)
					.registerStoredProcedureParameter("EmailExists", String.class, ParameterMode.OUT)
					.setParameter("Email", email);
			storedProcedure.execute();
			existingEmail = (String) storedProcedure.getOutputParameterValue("EmailExists");
			
		} catch (Exception e) {
			log.info("Couldn't get the email due to {}",e.getMessage());
		}
		return existingEmail;
	}

}
