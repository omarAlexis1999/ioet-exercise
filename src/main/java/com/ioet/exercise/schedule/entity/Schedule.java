package com.ioet.exercise.schedule.entity;

import java.time.LocalTime;

public class Schedule {
	private String day;
	private LocalTime  startTime;
	private LocalTime  endTime;
	
	public Schedule() {
		
	}

	public Schedule(String day, LocalTime startTime, LocalTime endTime) {
		super();
		this.day = day;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public String getDay() {
		return day;
	}

	public LocalTime getStartTime() {
		return startTime;
	}

	public LocalTime getEndTime() {
		return endTime;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}

	@Override
	public String toString() {
		return day + startTime + "-" + endTime;
	}
	
	
		
	
}
