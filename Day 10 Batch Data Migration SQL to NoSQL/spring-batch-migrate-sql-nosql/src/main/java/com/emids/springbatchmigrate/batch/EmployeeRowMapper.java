package com.emids.springbatchmigrate.batch;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.emids.springbatchmigrate.entity.EmployeeEntity;

public class EmployeeRowMapper implements RowMapper<EmployeeEntity> {

	@Override
	public EmployeeEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new EmployeeEntity(rs.getInt("employee_id"), 
				rs.getString("first_name"), rs.getString("last_name"),
				rs.getString("email"), rs.getString("gender"),
				rs.getString("department"), rs.getString("job_title"),
				rs.getInt("years_of_experience"), rs.getDouble("salary"));
		
	}

}
