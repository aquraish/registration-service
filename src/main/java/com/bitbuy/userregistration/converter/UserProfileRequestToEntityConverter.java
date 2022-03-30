package com.bitbuy.userregistration.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.bitbuy.userregistration.entity.UserProfileEntity;
import com.bitbuy.userregistration.request.UserProfileRequest;

@Component
public class UserProfileRequestToEntityConverter implements Converter<UserProfileRequest, UserProfileEntity>{

	@Override
	public UserProfileEntity convert(UserProfileRequest source) {
		UserProfileEntity entity = new UserProfileEntity();
		entity.setName(source.getName());
		entity.setEmail(source.getEmail());
		entity.setPhone(source.getPhone());
		return entity;
	}

}
