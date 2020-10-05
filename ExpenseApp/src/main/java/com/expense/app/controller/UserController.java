package com.expense.app.controller;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.expense.app.model.User;
import com.expense.app.repository.UserRepository;

@RestController
public class UserController {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private DaoAuthenticationProvider daoAuthenticationProvider;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody User user) throws NoSuchAlgorithmException {
		LOGGER.info("Login User ===>"+user);
		try {
			daoAuthenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
			Map<String, Object> model = new HashMap<>();
			model.put("loginUser", user);
			return ResponseEntity.ok(model);
		} catch (AuthenticationException e) {
			LOGGER.error("AuthenticationException ===>", e);
			throw new BadCredentialsException("Invalid username or password");
		}
	}

	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody User user) {
		LOGGER.info("Saving User ===> " + user);
		String encodedPass = bCryptPasswordEncoder.encode(user.getPassword());
		user.setPassword(encodedPass);
		userRepository.save(user);
		return ResponseEntity.ok("User Registered Successfully");
	}

}
