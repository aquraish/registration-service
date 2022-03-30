package com.bitbuy.userregistration.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.bitbuy.userregistration.entity.UserProfileEntity;
import com.bitbuy.userregistration.response.UserProfileResponse;

@Component
public class UserProfileEntityToResponseConverter implements Converter<UserProfileEntity, UserProfileResponse>{

	@Override
	public UserProfileResponse convert(UserProfileEntity source) {
		UserProfileResponse profileResponse = new UserProfileResponse();
		profileResponse.setEmail(source.getEmail());
		profileResponse.setName(source.getName());
		profileResponse.setPhone(source.getPhone());
		return profileResponse;
	}

}
