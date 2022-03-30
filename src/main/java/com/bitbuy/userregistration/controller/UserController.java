package com.bitbuy.userregistration.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bitbuy.userregistration.entity.UserProfileEntity;
import com.bitbuy.userregistration.repository.UserProfileRepository;
import com.bitbuy.userregistration.repository.UserRepository;
import com.bitbuy.userregistration.request.UserProfileRequest;
import com.bitbuy.userregistration.response.UserProfileResponse;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping(path = "users")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserProfileRepository customerRepository;

	@Autowired
	private ConversionService conversionService;

	@Operation(summary = "Create/Update user details")
	@PostMapping(path = "/user/{userID}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserProfileResponse> updateUserProfile(@RequestBody UserProfileRequest user,
			@PathVariable String userID) {
		UserProfileEntity dbCustomer = conversionService.convert(user, UserProfileEntity.class);
		dbCustomer.setUserAccount(userRepository.findById(userID).get());
		return ResponseEntity
				.ok(conversionService.convert(customerRepository.save(dbCustomer), UserProfileResponse.class));
	}

	@Operation(summary = "Get user details")
	@GetMapping(path = "/user/{userID}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserProfileResponse> getUserProfile(@PathVariable String userID) {
		Optional<UserProfileEntity> customer = customerRepository.findByUserAccountId(userID);
		return ResponseEntity
				.ok(conversionService.convert(customer.get(), UserProfileResponse.class));
	}

}
