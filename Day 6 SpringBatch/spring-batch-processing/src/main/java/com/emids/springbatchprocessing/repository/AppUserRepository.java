package com.emids.springbatchprocessing.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.emids.springbatchprocessing.entity.AppUser;

@Repository
public interface AppUserRepository extends CrudRepository<AppUser, Integer>{

}
