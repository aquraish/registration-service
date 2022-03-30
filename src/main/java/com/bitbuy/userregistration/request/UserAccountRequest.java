package com.bitbuy.userregistration.request;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class UserAccountRequest implements Serializable {
	
	private static final long serialVersionUID = 7365906583526609776L;
	@NotNull
	@NotBlank
	private String username;
	@NotNull
	@NotBlank
	private String password;
}
