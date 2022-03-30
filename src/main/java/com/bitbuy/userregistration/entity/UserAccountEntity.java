package com.bitbuy.userregistration.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "USER_ACCOUNT")
public class UserAccountEntity {
	
	@Id
	private String id;
	@NotNull
	private String username;
	@NotNull
	private String password;
	
	private boolean enabled;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "userAccount")
    private UserProfileEntity userProfile;
	
}
