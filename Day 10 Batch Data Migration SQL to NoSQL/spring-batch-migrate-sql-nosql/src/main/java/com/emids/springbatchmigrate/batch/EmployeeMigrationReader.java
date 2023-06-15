package com.emids.springbatchmigrate.batch;

import javax.sql.DataSource;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.emids.springbatchmigrate.entity.EmployeeEntity;

@Component
public class EmployeeMigrationReader 
extends JdbcCursorItemReader<EmployeeEntity> 
implements ItemReader<EmployeeEntity>{
	
	public EmployeeMigrationReader(@Autowired DataSource dataSource) {
		setDataSource(dataSource);
		setSql("select employee_id,first_name,last_name,email,gender,"
				+ "department,job_title,years_of_experience,salary from employee_entity");
		setFetchSize(100);
		setRowMapper(new EmployeeRowMapper());	
	}

}

