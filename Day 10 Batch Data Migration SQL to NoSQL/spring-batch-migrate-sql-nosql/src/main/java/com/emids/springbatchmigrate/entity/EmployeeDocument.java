package com.emids.springbatchmigrate.entity;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class EmployeeDocument {
	 @Id
	    private Integer employeeId;
	    private String firstName;
	    private String lastName;
	    private String email;
	    private String gender;
	    private String department;
	    private String jobTitle;
	    private Integer yearsOfExperience;
	    private Double salary;
	    
		public EmployeeDocument(Integer employeeId, String firstName, String lastName, String email, String gender,
				String department, String jobTitle, Integer yearsOfExperience, Double salary) {
			this.employeeId = employeeId;
			this.firstName = firstName;
			this.lastName = lastName;
			this.email = email;
			this.gender = gender;
			this.department = department;
			this.jobTitle = jobTitle;
			this.yearsOfExperience = yearsOfExperience;
			this.salary = salary;
		}

		public EmployeeDocument() {
		}

		public Integer getEmployeeId() {
			return employeeId;
		}

		public void setEmployeeId(Integer employeeId) {
			this.employeeId = employeeId;
		}

		public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getGender() {
			return gender;
		}

		public void setGender(String gender) {
			this.gender = gender;
		}

		public String getDepartment() {
			return department;
		}

		public void setDepartment(String department) {
			this.department = department;
		}

		public String getJobTitle() {
			return jobTitle;
		}

		public void setJobTitle(String jobTitle) {
			this.jobTitle = jobTitle;
		}

		public Integer getYearsOfExperience() {
			return yearsOfExperience;
		}

		public void setYearsOfExperience(Integer yearsOfExperience) {
			this.yearsOfExperience = yearsOfExperience;
		}

		public Double getSalary() {
			return salary;
		}

		public void setSalary(Double salary) {
			this.salary = salary;
		}

		@Override
		public String toString() {
			return "EmployeeEntity [employeeId=" + employeeId + ", firstName=" + firstName + ", lastName=" + lastName
					+ ", email=" + email + ", gender=" + gender + ", department=" + department + ", jobTitle=" + jobTitle
					+ ", yearsOfExperience=" + yearsOfExperience + ", salary=" + salary + "]";
		}
	    
		
}
