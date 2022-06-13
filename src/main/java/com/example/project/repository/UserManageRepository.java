package com.example.project.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.project.domain.User;

public interface UserManageRepository extends JpaRepository<User, Long> {

	User findByUsernameAndPassword(String username, String password);
	
	Optional<User> findByUsername(String username);

}
