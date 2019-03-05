package com.project.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.entities.ApplicationUser;
import com.project.response.ResponseMessage;
import com.project.response.JwtResponse;
import com.project.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService service;
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public UserController(BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.bCryptPasswordEncoder =  bCryptPasswordEncoder;
	}
	
	@GetMapping
	public List<ApplicationUser> getAll() {
		return service.getAll(); 
	}
	
	@GetMapping(path="/{username}")
	public ApplicationUser findUser(@PathVariable String username) {
		return service.findByUserName(username); 
	}


	@PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@Valid @RequestBody ApplicationUser user) {
        try {
			user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
	        service.saveUser(user);
	        return  ResponseEntity.ok(new ResponseMessage("User registered successfully"));
        }catch(Exception ex) {
    			return new ResponseEntity<>(new ResponseMessage("Fail -> " + ex.getMessage()),
    					HttpStatus.BAD_REQUEST);
        }
    }
	
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody ApplicationUser loginRequest) {
 
		JwtResponse jwtResponse;
		jwtResponse = service.authenticateUser(loginRequest);
		return ResponseEntity.ok(jwtResponse);
	}
	
	@PutMapping
	public ResponseEntity<?> editUser(@Valid @RequestBody ApplicationUser user) {
		try{
			service.saveUser(user);
		    return  ResponseEntity.ok(new ResponseMessage("User updated successfully"));
		    
		}catch(Exception ex) {
			return new ResponseEntity<>(new ResponseMessage("Fail -> " + ex.getMessage()),
				HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping(path="/{id}")
	public void deleteUser(@PathVariable long id) {
		this.service.deleteUser(id);
	}
	
}
