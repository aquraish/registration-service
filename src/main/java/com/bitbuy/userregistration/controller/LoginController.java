package com.bitbuy.userregistration.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bitbuy.userregistration.request.AuthenticationRequest;
import com.bitbuy.userregistration.response.JwtResponse;
import com.bitbuy.userregistration.service.LoginService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
public class LoginController {
	
    @Autowired
    private LoginService loginService;
	
	@Operation(summary = "Login user")
	@PostMapping(path="/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JwtResponse> login(@Valid @RequestBody AuthenticationRequest authenticationRequest) throws Exception {
		return ResponseEntity.ok(loginService.login(authenticationRequest));
	}
	
}
