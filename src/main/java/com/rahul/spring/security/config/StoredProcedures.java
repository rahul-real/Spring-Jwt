/**
 * 
 */
package com.rahul.spring.security.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * @author rahul
   @since  27-Jan-2024 2024 1:51:03 pm
 */
@Data
@Component
@ConfigurationProperties(prefix = "rahul.stored-proc-name")
public class StoredProcedures {
	
	private String getUserData;

}
