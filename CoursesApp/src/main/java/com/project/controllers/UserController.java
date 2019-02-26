package com.project.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.project.entities.ApplicationUser;
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
    public void signUp(@RequestBody ApplicationUser user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        service.saveUser(user);
    }
	
	@PutMapping
	public void editUser(@RequestBody ApplicationUser user) {
		this.service.saveUser(user);
	}
	
	@DeleteMapping(path="/{id}")
	public void deleteUser(@PathVariable long id) {
		this.service.deleteUser(id);
	}
}
