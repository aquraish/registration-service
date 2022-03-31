package com.bitbuy.userregistration.converter;

import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.bitbuy.userregistration.entity.UserAccountEntity;
import com.bitbuy.userregistration.request.UserAccountRequest;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class UserAccountRequestToEntityConverter implements Converter<UserAccountRequest, UserAccountEntity> {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserAccountEntity convert(UserAccountRequest source) {
		if (StringUtils.isBlank(source.getUsername()) || StringUtils.isBlank(source.getPassword())) {
			throw new IllegalArgumentException("Username is empty");
		}
		UserAccountEntity userAccountEntity = new UserAccountEntity();
		userAccountEntity.setId(UUID.randomUUID().toString());
		userAccountEntity.setUsername(source.getUsername());
		userAccountEntity.setPassword(passwordEncoder.encode(source.getPassword()));
		userAccountEntity.setEnabled(true);
		return userAccountEntity;
	}

}
