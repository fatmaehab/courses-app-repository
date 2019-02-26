package com.project.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.entities.ApplicationUser;
import com.project.repositories.UserRepository;

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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ApplicationUser applicationUser = repository.findByUsername(username);
        if (applicationUser == null) {
            throw new UsernameNotFoundException(username);
        }
        return new User(applicationUser.getUsername(), applicationUser.getPassword(), emptyList());
    }

	
	
	public ApplicationUser findByUserName(String username) {
		return repository.findByUsername(username);
	}

	public void saveUser(ApplicationUser user) {
		this.repository.save(user);
	}

	public void deleteUser(long userId) {
		ApplicationUser user = this.repository.getOne(userId);
		this.repository.delete(user);
	}
	
	public ApplicationUser findUser(long userId) {
		return this.repository.getOne(userId);
	}
	
	public List<ApplicationUser> getAll() {
		return this.repository.findAll();
	}
}
