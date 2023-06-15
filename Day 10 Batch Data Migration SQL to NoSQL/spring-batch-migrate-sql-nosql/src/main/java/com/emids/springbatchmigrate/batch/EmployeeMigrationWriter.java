package com.emids.springbatchmigrate.batch;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.emids.springbatchmigrate.entity.EmployeeDocument;
import com.emids.springbatchmigrate.repository.EmployeeDocumentRepository;

@Component
public class EmployeeMigrationWriter implements ItemWriter<EmployeeDocument>{

	@Autowired
	private EmployeeDocumentRepository empDocumentRepository;
	@Override
	public void write(List<? extends EmployeeDocument> items) throws Exception {
		System.out.println("Writing all employees to MongoDB Repository...");
		empDocumentRepository.saveAll(items);
		
	}

}
