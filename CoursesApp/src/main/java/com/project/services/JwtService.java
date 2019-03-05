package com.project.services;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import static com.project.security.SecurityConstants.EXPIRATION_TIME;
import static com.project.security.SecurityConstants.SECRET;

import java.util.Date;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;

import com.auth0.jwt.JWT;

public class JwtService {
	
	public String generateJwtToken(Authentication authentication) {
		 String token = JWT.create()
	                .withSubject(((User) authentication.getPrincipal()).getUsername())
	                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
	                .sign(HMAC512(SECRET.getBytes()));
	     return token;
	}

}
