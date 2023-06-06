package com.emids.springbootdemo.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.emids.springbootdemo.entity.CompanyShare;

@Repository
public interface ShareRepository extends CrudRepository<CompanyShare, UUID>{

	Optional<CompanyShare> findByShareName(String shareName);
	
}
