package com.emids.springbatchmigrate.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.emids.springbatchmigrate.entity.EmployeeEntity;

@Repository
public interface EmployeeEntityRepopsitory extends CrudRepository<EmployeeEntity, Integer> {

}
