package com.emids.springbatchprocessing.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class AppUser {

	@Id
	private int id;
	private String name;
	private String department;
	private int age;
	private int salary;
	
	public AppUser(int id, String name, String department, int age, int salary) {
		this.id = id;
		this.name = name;
		this.department = department;
		this.age = age;
		this.salary = salary;
	}

	public AppUser() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "AppUser [id=" + id + ", name=" + name + ", department=" + department + ", age=" + age + ", salary="
				+ salary + "]";
	}
	
	
}
