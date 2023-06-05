package com.emids.springbootdemo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.emids.springbootdemo.entity.Share;

@Repository
public interface ShareRepository extends CrudRepository<Share, String>{

}
