package com.ioet.exercise.schedule.entity;

import java.util.Arrays;

public class Employee {
	
	private String name;
	private Schedule [] schedule;
	
	public Employee() {
		super(); 
	}
	
	public Employee(String name, Schedule[] schedule) {
		super();
		this.name = name;
		this.schedule = schedule;
	}
	public String getName() {
		return name;
	}
	public Schedule[] getSchedule() {
		return schedule;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setSchedule(Schedule[] schedule) {
		this.schedule = schedule;
	}

	@Override
	public String toString() {
		return name + "=" + Arrays.toString(schedule);
	}
	
	

}
