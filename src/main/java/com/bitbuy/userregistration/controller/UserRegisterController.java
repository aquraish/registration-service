package com.bitbuy.userregistration.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bitbuy.userregistration.entity.AuthorityEntity;
import com.bitbuy.userregistration.entity.UserAccountEntity;
import com.bitbuy.userregistration.repository.AuthorityRepository;
import com.bitbuy.userregistration.repository.UserRepository;
import com.bitbuy.userregistration.request.UserAccountRequest;
import com.bitbuy.userregistration.response.UserAccountResponse;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("registration")
public class UserRegisterController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AuthorityRepository authorityRepository;

	@Autowired
	private ConversionService conversionService;

	@Operation(summary = "Register user")
	@PutMapping(path = "/users/user", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserAccountResponse> registerUser(@Valid @RequestBody UserAccountRequest user) {
		UserAccountEntity dbUser = conversionService.convert(user, UserAccountEntity.class);
		UserAccountEntity savedUser = userRepository.save(dbUser);
		AuthorityEntity authorityEntity = new AuthorityEntity();
		authorityEntity.setUsername(user.getUsername());
		authorityEntity.setAuthority("ROLE_USER");
		authorityRepository.save(authorityEntity);
		return ResponseEntity.status(HttpStatus.CREATED).body(conversionService.convert(savedUser, UserAccountResponse.class));
	}

}
