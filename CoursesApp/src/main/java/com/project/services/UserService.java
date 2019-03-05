package com.project.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.entities.ApplicationUser;
import com.project.repositories.UserRepository;
import com.project.response.JwtResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static java.util.Collections.emptyList;
@Service
@Transactional
public class UserService implements UserDetailsService {

	@Autowired
    private UserRepository repository;
	
	@Autowired
	AuthenticationManager authenticationManager;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ApplicationUser applicationUser = repository.findByUsername(username);
        if (applicationUser == null) {
            throw new UsernameNotFoundException(username);
        }
        return new User(applicationUser.getUsername(), applicationUser.getPassword(), emptyList());
    }

	
	/**
	 * retrieve for user using username
	 * @param username
	 * @return
	 */
	public ApplicationUser findByUserName(String username) {
		return repository.findByUsername(username);
	}

	/**
	 * Save the user either by add/edit
	 * validate that no other user exists with the same username
	 * @param user
	 * @throws Exception
	 */
	public void saveUser(ApplicationUser user) throws Exception {
		if(existsByUsername((user.getUsername()))) {
			throw new Exception("Username already exists");
		}
		this.repository.save(user);
	}

	/**
	 * Delete user using user id
	 * @param userId
	 */
	public void deleteUser(long userId) {
		ApplicationUser user = this.repository.getOne(userId);
		this.repository.delete(user);
	}
	
	/**
	 * retrieve user using user id
	 * @param userId
	 * @return
	 */
	public ApplicationUser findUser(long userId) {
		return this.repository.getOne(userId);
	}
	
	/**
	 * Retrieve all existing users
	 * @return list of application users
	 */
	public List<ApplicationUser> getAll() {
		return this.repository.findAll();
	}
	
	/**
	 * Check if another user exists with the same username
	 * @param username
	 * @return
	 */
	public Boolean existsByUsername(String username) {
		return this.repository.existsByUsername(username);
	}
	
	/**
	 * Authenticate the user using JWT 
	 * @param user
	 * @return
	 */
	public JwtResponse authenticateUser(ApplicationUser user) {
		
		JwtService jwtService = new JwtService();
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
 
		SecurityContextHolder.getContext().setAuthentication(authentication);
 
		String jwt = jwtService.generateJwtToken(authentication);
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		return new JwtResponse(jwt, userDetails.getUsername(), userDetails.getAuthorities());
	}
	
	
}
