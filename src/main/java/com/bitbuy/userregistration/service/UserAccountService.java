package com.bitbuy.userregistration.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import com.bitbuy.userregistration.entity.AuthorityEntity;
import com.bitbuy.userregistration.entity.UserAccountEntity;
import com.bitbuy.userregistration.repository.AuthorityRepository;
import com.bitbuy.userregistration.repository.UserAccountRepository;
import com.bitbuy.userregistration.request.UserAccountRequest;
import com.bitbuy.userregistration.response.UserAccountResponse;

@Service
public class UserAccountService {
	
	@Autowired
	private UserAccountRepository userRepository;
	
	@Autowired
	private AuthorityRepository authorityRepository;
	
	@Autowired
	private ConversionService conversionService;
	
	public UserAccountResponse registerUser(UserAccountRequest user) {
		UserAccountEntity dbUser = conversionService.convert(user, UserAccountEntity.class);
		UserAccountEntity savedUser = userRepository.save(dbUser);
		AuthorityEntity authorityEntity = new AuthorityEntity();
		authorityEntity.setUsername(user.getUsername());
		authorityEntity.setAuthority("ROLE_USER");
		authorityRepository.save(authorityEntity);
		return conversionService.convert(savedUser, UserAccountResponse.class);
	}

}
