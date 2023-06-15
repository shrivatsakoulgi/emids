package com.emids.springbatchmigrate.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.emids.springbatchmigrate.entity.EmployeeDocument;

@Repository
public interface EmployeeDocumentRepository extends MongoRepository<EmployeeDocument, Integer> {

}
