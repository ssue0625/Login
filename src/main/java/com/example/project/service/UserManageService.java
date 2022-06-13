package com.example.project.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.project.domain.User;
import com.example.project.repository.UserManageRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserManageService implements UserDetailsService {

	private final UserManageRepository userManageRepository;

	@Transactional
	public User findByUsernameAndPassword(User user) {
		User findUser = userManageRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
		return findUser;
	}

	@Transactional
	public Long save(User user) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userManageRepository.save(
				user.builder().username(user.getUsername()).password(user.getPassword()).auth(user.getAuth()).build())
				.getId();
	}

	@Override
	public User loadUserByUsername(String username) throws UsernameNotFoundException {
		return userManageRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException((username)));
	}

}