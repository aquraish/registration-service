package com.bitbuy.userregistration.response;

import java.io.Serializable;

import lombok.Data;

@Data
public class UserAccountResponse implements Serializable {
	
	private static final long serialVersionUID = 5927473401029323352L;
	private String id;
	private String username;

}
