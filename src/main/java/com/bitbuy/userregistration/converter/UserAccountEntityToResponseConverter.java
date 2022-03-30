package com.bitbuy.userregistration.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.bitbuy.userregistration.entity.UserAccountEntity;
import com.bitbuy.userregistration.response.UserAccountResponse;

@Component
public class UserAccountEntityToResponseConverter implements Converter<UserAccountEntity, UserAccountResponse>{

	@Override
	public UserAccountResponse convert(UserAccountEntity source) {
		UserAccountResponse response = new UserAccountResponse();
		response.setId(source.getId());
		response.setUsername(source.getUsername());
		return response;
	}

}
