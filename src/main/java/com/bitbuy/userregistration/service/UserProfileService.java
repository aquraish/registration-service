package com.bitbuy.userregistration.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import com.bitbuy.userregistration.entity.UserProfileEntity;
import com.bitbuy.userregistration.repository.UserAccountRepository;
import com.bitbuy.userregistration.repository.UserProfileRepository;
import com.bitbuy.userregistration.request.UserProfileRequest;
import com.bitbuy.userregistration.response.UserProfileResponse;

@Service
public class UserProfileService {

	@Autowired
	private UserAccountRepository userRepository;

	@Autowired
	private UserProfileRepository customerRepository;

	@Autowired
	private ConversionService conversionService;

	public UserProfileResponse updateUserProfile(UserProfileRequest user, String userID) {
		UserProfileEntity dbCustomer = conversionService.convert(user, UserProfileEntity.class);
		dbCustomer.setUserAccount(userRepository.findById(userID).get());
		return conversionService.convert(customerRepository.save(dbCustomer), UserProfileResponse.class);
	}

	public UserProfileResponse getUserProfile(String userID) {
		Optional<UserProfileEntity> customer = customerRepository.findByUserAccountId(userID);
		return conversionService.convert(customer.get(), UserProfileResponse.class);
	}

}
