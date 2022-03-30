package com.bitbuy.userregistration.request;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class AuthenticationRequest implements Serializable {
	
	private static final long serialVersionUID = -8425712801257414162L;
	
	@NotNull
	@NotBlank
	private String username;
	
	@NotNull
	@NotBlank
	private String password;
}
