package com.emids.springbatchmigrate.batch;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.emids.springbatchmigrate.entity.EmployeeDocument;
import com.emids.springbatchmigrate.entity.EmployeeEntity;

@Component
public class EmployeeMigrationProcessor implements ItemProcessor<EmployeeEntity, EmployeeDocument>{

	@Override
	public EmployeeDocument process(EmployeeEntity item) throws Exception {
		
		return new EmployeeDocument(item.getEmployeeId(), item.getFirstName(), 
				item.getLastName(), item.getEmail(), item.getGender(),
				item.getDepartment(), item.getJobTitle(),
				item.getYearsOfExperience(), item.getSalary());
	}

}
