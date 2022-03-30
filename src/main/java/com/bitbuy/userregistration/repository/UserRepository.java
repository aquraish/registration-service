package com.bitbuy.userregistration.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bitbuy.userregistration.entity.UserAccountEntity;

@Repository
public interface  UserRepository extends CrudRepository<UserAccountEntity, String> {
	
}
