package com.emids.springbatchprocessing.batch;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.emids.springbatchprocessing.entity.AppUser;

@Component
public class UserItemProcessor implements ItemProcessor<AppUser, AppUser>{

	@Override
	public AppUser process(AppUser item) throws Exception {
		// some processing, according to business requirement
		
		item.setDepartment(item.getDepartment().toUpperCase());
		item.setSalary((int) (item.getSalary()*1.1));
		return item;
	}	
}
