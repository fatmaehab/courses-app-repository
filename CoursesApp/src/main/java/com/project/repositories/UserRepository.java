package com.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.entities.ApplicationUser;

public interface UserRepository extends JpaRepository<ApplicationUser, Long> {

	ApplicationUser findByUsername(String username);
	Boolean existsByUsername(String username);
}
