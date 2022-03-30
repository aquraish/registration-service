package com.bitbuy.userregistration.request;

import java.io.Serializable;

import lombok.Data;

@Data
public class UserProfileRequest implements Serializable {
	
	private static final long serialVersionUID = 6920662410661499954L;
	private String name;
	private String email;
	private String phone;

}
