package com.project.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
 
@Component
public class JwtAuthEntryPoint implements AuthenticationEntryPoint {
 
    private static final Logger logger = LoggerFactory.getLogger(JwtAuthEntryPoint.class);
    
    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException e) 
                        		 throws IOException, ServletException {
    	String errorMessage= "";
        logger.error("Unauthorized error. Message - {}", e.getMessage());
        logger.info("URL: " +request.getRequestURL().toString());
        if (request.getRequestURL().toString().contains(SecurityConstants.SIGN_IN_URL)){
        	errorMessage = "Error -> Invalid username or password";
        }else {
        	errorMessage = "Error -> User is unauthorized";
        }
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, errorMessage);
    }
}