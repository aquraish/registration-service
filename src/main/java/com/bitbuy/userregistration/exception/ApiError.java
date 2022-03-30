package com.bitbuy.userregistration.exception;

import java.io.Serializable;
import java.util.UUID;

import lombok.Data;

@Data
public class ApiError implements Serializable {
	
	private static final long serialVersionUID = 8085992398431888139L;
	private String code;
	private String message;
	
	public ApiError(String message) {
		super();
		this.code = UUID.randomUUID().toString();
		this.message = message;
	}
	
	
}
