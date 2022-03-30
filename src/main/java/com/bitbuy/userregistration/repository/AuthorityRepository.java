package com.bitbuy.userregistration.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bitbuy.userregistration.entity.AuthorityEntity;

@Repository
public interface  AuthorityRepository extends CrudRepository<AuthorityEntity, Long> {
	
}
