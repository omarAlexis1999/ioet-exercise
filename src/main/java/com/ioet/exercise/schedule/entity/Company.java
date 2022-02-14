package com.ioet.exercise.schedule.entity;

import java.util.ArrayList;

public class Company {
	private ArrayList<Employee> employee;
	
	public Company() {
		super();
		this.employee = new ArrayList<Employee>();
	}
	
	public Company(ArrayList<Employee> employee) {
		super();
		this.employee = employee;
	}

	public ArrayList<Employee> getEmployees() {
		return employee;
	}

	public void setEmployee(ArrayList<Employee> employee) {
		this.employee = employee;
	}
	
	public void addEmployee(Employee employee){
		this.employee.add(employee);
	}

	@Override
	public String toString() {
		return "Company = " + employee ;
	}

	
	
	
}
