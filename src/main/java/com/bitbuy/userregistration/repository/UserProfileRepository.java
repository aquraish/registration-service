package com.bitbuy.userregistration.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bitbuy.userregistration.entity.UserProfileEntity;

@Repository
public interface UserProfileRepository extends CrudRepository<UserProfileEntity, String> {
	Optional<UserProfileEntity> findByUserAccountId(String userId);
}
