package com.project.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class ApplicationUser {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
	@NotBlank
	@Size(min = 3, max = 50)
    private String username;
	@NotBlank
    private String password;
    
    
    
	public ApplicationUser() {

	}
	
	
	public ApplicationUser(long id, String username, String password) {
		this.id = id;
		this.username = username;
		this.password = password;
	}


	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
    
    
}
