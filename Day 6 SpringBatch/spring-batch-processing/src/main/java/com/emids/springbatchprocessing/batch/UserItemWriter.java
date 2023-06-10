package com.emids.springbatchprocessing.batch;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.emids.springbatchprocessing.entity.AppUser;
import com.emids.springbatchprocessing.repository.AppUserRepository;

@Component
public class UserItemWriter implements ItemWriter<AppUser>{

	@Autowired
	private AppUserRepository repository;
	
	@Override
	public void write(List<? extends AppUser> items) throws Exception {
		System.out.println("Saving all App Users..."+items);
		repository.saveAll(items);	
	}
}
