package com.bitbuy.userregistration.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bitbuy.userregistration.request.UserAccountRequest;
import com.bitbuy.userregistration.response.UserAccountResponse;
import com.bitbuy.userregistration.service.UserAccountService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("registration")
public class UserAccountController {

	@Autowired
	private UserAccountService userAccountService;
	
	@Operation(summary = "Register user")
	@PutMapping(path = "/users/user", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserAccountResponse> registerUser(@Valid @RequestBody UserAccountRequest user) {
		return ResponseEntity.status(HttpStatus.CREATED).body(userAccountService.registerUser(user));
	}

}
