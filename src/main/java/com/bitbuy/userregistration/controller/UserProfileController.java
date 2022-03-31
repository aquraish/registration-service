package com.bitbuy.userregistration.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bitbuy.userregistration.request.UserProfileRequest;
import com.bitbuy.userregistration.response.UserProfileResponse;
import com.bitbuy.userregistration.service.UserProfileService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping(path = "users")
public class UserProfileController {

	@Autowired
	private UserProfileService userProfileService;

	@Operation(summary = "Create/Update user details")
	@PostMapping(path = "/user/{userID}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserProfileResponse> updateUserProfile(@RequestBody UserProfileRequest user,
			@PathVariable String userID) {
		return ResponseEntity.ok(userProfileService.updateUserProfile(user, userID));
	}

	@Operation(summary = "Get user details")
	@GetMapping(path = "/user/{userID}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserProfileResponse> getUserProfile(@PathVariable String userID) {
		return ResponseEntity.ok(userProfileService.getUserProfile(userID));
	}

}
