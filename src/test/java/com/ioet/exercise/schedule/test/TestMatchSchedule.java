package com.ioet.exercise.schedule.test;

import java.time.LocalTime;

import com.ioet.exercise.schedule.entity.Company;
import com.ioet.exercise.schedule.entity.Employee;
import com.ioet.exercise.schedule.entity.Schedule;
import com.ioet.exercise.schedule.services.AdminCompany;

import junit.framework.TestCase;

public class TestMatchSchedule extends TestCase{
	
	public void testBuiltCompany(){
		
		String route = "D:\\Documentos\\Programacion\\Utils\\Files\\employessAndSchedules.txt";
		/**
		 * The file contain
		 * RENE=MO 10:00-12:00,TU 10:00-12:00,TH 01:00-03:00,SA 14:00-18:00,SU 20:00- 21:00
		 * ASTRID=MO 10:00-12:00,TH 12:00-14:00,SU 20:00-21:00
		 * ANDRES=MO 10:00-12:00,TH 12:00-14:00,SU 20:00-21:00
		 */
		Company company = AdminCompany.builtCompany(route);
		
		//Compare names
		assertEquals("RENE", company.getEmployees().get(0).getName());
		assertEquals("ASTRID", company.getEmployees().get(1).getName());
		assertEquals("ANDRES", company.getEmployees().get(2).getName());
		
		//Compare days of shedules 
		assertEquals("MO", company.getEmployees().get(0).getSchedule()[0].getDay());
		assertEquals("TH", company.getEmployees().get(1).getSchedule()[1].getDay());
		assertEquals("SU", company.getEmployees().get(2).getSchedule()[2].getDay());
		
		//Compare hours of some schedules MO 10:00-12:00
		assertEquals(10, company.getEmployees().get(0).getSchedule()[0].getStartTime().getHour());
		assertEquals(12, company.getEmployees().get(0).getSchedule()[0].getEndTime().getHour());
		
		//Compare hours of some schedules TH 12:00-14:00
		assertEquals(12, company.getEmployees().get(1).getSchedule()[1].getStartTime().getHour());
		assertEquals(14, company.getEmployees().get(1).getSchedule()[1].getEndTime().getHour());
		
		//Compare hours of some schedules SU 20:00-21:00
		assertEquals(20, company.getEmployees().get(2).getSchedule()[2].getStartTime().getHour());
		assertEquals(21, company.getEmployees().get(2).getSchedule()[2].getEndTime().getHour());
		
		//Compare minutes of some schedules SA 14:00-18:00
		assertEquals(0, company.getEmployees().get(0).getSchedule()[3].getStartTime().getMinute());
		assertEquals(0, company.getEmployees().get(0).getSchedule()[3].getEndTime().getMinute());
		
	}
	
	public void testHasCoincidence(){
		//Test method Has Coincidence
		Schedule schedule1 = new Schedule("MO",LocalTime.parse("15:00"),LocalTime.parse("17:00"));
		Schedule schedule2 = new Schedule("MO",LocalTime.parse("12:00"),LocalTime.parse("16:00"));
		Schedule schedule3 = new Schedule("MO",LocalTime.parse("07:00"),LocalTime.parse("12:00"));
		
		Schedule schedule4 = new Schedule("TU",LocalTime.parse("07:00"),LocalTime.parse("12:30"));
		Schedule schedule5 = new Schedule("TU",LocalTime.parse("11:30"),LocalTime.parse("12:30"));
		Schedule schedule6 = new Schedule("TU",LocalTime.parse("17:00"),LocalTime.parse("19:00"));
		
		Schedule schedule7 = new Schedule("TH",LocalTime.parse("09:00"),LocalTime.parse("11:50"));
		Schedule schedule8 = new Schedule("TH",LocalTime.parse("11:40"),LocalTime.parse("12:00"));
		Schedule schedule9 = new Schedule("TH",LocalTime.parse("13:00"),LocalTime.parse("17:00"));
		
		//Compare scheduler in the same day in diferents hours
		assertEquals(true,AdminCompany.hasCoincidence(schedule1,schedule2));
		assertEquals(false,AdminCompany.hasCoincidence(schedule1,schedule3));
		
		assertEquals(true,AdminCompany.hasCoincidence(schedule4,schedule5));
		assertEquals(false,AdminCompany.hasCoincidence(schedule4,schedule6));
		
		assertEquals(true,AdminCompany.hasCoincidence(schedule7,schedule8));
		assertEquals(false,AdminCompany.hasCoincidence(schedule7,schedule9));
		
		//Compare scheduler in diferents days
		assertEquals(false,AdminCompany.hasCoincidence(schedule1,schedule4));
		assertEquals(false,AdminCompany.hasCoincidence(schedule1,schedule7));
		
		assertEquals(false,AdminCompany.hasCoincidence(schedule4,schedule7));
		assertEquals(false,AdminCompany.hasCoincidence(schedule4,schedule1));
	}
	
	public void testFindScheduleFrequency(){
		
		Schedule [] schedule1 = new Schedule[5];
		Schedule [] schedule2 = new Schedule[3];
		Schedule [] schedule3 = new Schedule[3];
		
		Schedule [] schedule4 = new Schedule[5];
		Schedule [] schedule5 = new Schedule[3];
		
		schedule1[0] = new Schedule("MO",LocalTime.parse("10:00"),LocalTime.parse("12:00"));
		schedule1[1] = new Schedule("TU",LocalTime.parse("10:00"),LocalTime.parse("12:00"));
		schedule1[2] = new Schedule("TH",LocalTime.parse("01:00"),LocalTime.parse("03:00"));
		schedule1[3] = new Schedule("SA",LocalTime.parse("14:00"),LocalTime.parse("18:00"));
		schedule1[4] = new Schedule("SU",LocalTime.parse("20:00"),LocalTime.parse("21:00"));
		
		schedule2[0] = new Schedule("MO",LocalTime.parse("10:00"),LocalTime.parse("12:00"));
		schedule2[1] = new Schedule("TH",LocalTime.parse("12:00"),LocalTime.parse("14:00"));
		schedule2[2] = new Schedule("SU",LocalTime.parse("20:00"),LocalTime.parse("21:00"));
		
		schedule3[0] = new Schedule("MO",LocalTime.parse("10:00"),LocalTime.parse("12:00"));
		schedule3[1] = new Schedule("TH",LocalTime.parse("12:00"),LocalTime.parse("14:00"));
		schedule3[2] = new Schedule("SU",LocalTime.parse("20:00"),LocalTime.parse("21:00"));
		
		schedule4[0] = new Schedule("MO",LocalTime.parse("10:15"),LocalTime.parse("12:00"));
		schedule4[1] = new Schedule("TU",LocalTime.parse("10:00"),LocalTime.parse("12:00"));
		schedule4[2] = new Schedule("TH",LocalTime.parse("13:00"),LocalTime.parse("13:15"));
		schedule4[3] = new Schedule("SA",LocalTime.parse("14:00"),LocalTime.parse("18:00"));
		schedule4[4] = new Schedule("SU",LocalTime.parse("20:00"),LocalTime.parse("21:00"));
		
		schedule5[0] = new Schedule("MO",LocalTime.parse("10:00"),LocalTime.parse("12:00"));
		schedule5[1] = new Schedule("TH",LocalTime.parse("12:00"),LocalTime.parse("14:00"));
		schedule5[2] = new Schedule("SU",LocalTime.parse("20:00"),LocalTime.parse("21:00"));
		
		
		assertEquals(2, AdminCompany.findScheduleFrequency(schedule1,schedule2));
		assertEquals(2, AdminCompany.findScheduleFrequency(schedule1,schedule3));
		assertEquals(3, AdminCompany.findScheduleFrequency(schedule2,schedule3));
		
		assertEquals(3, AdminCompany.findScheduleFrequency(schedule4,schedule5));
		assertEquals(2, AdminCompany.findScheduleFrequency(schedule1,schedule5));
		
	}
	
	public void testFindMatchEmployess(){
		
		Schedule [] schedule1 = new Schedule[5];
		Schedule [] schedule2 = new Schedule[3];
		Schedule [] schedule3 = new Schedule[3];
		
		schedule1[0] = new Schedule("MO",LocalTime.parse("10:00"),LocalTime.parse("12:00"));
		schedule1[1] = new Schedule("TU",LocalTime.parse("10:00"),LocalTime.parse("12:00"));
		schedule1[2] = new Schedule("TH",LocalTime.parse("01:00"),LocalTime.parse("03:00"));
		schedule1[3] = new Schedule("SA",LocalTime.parse("14:00"),LocalTime.parse("18:00"));
		schedule1[4] = new Schedule("SU",LocalTime.parse("20:00"),LocalTime.parse("21:00"));
		
		schedule2[0] = new Schedule("MO",LocalTime.parse("10:00"),LocalTime.parse("12:00"));
		schedule2[1] = new Schedule("TH",LocalTime.parse("12:00"),LocalTime.parse("14:00"));
		schedule2[2] = new Schedule("SU",LocalTime.parse("20:00"),LocalTime.parse("21:00"));
		
		schedule3[0] = new Schedule("MO",LocalTime.parse("10:00"),LocalTime.parse("12:00"));
		schedule3[1] = new Schedule("TH",LocalTime.parse("12:00"),LocalTime.parse("14:00"));
		schedule3[2] = new Schedule("SU",LocalTime.parse("20:00"),LocalTime.parse("21:00"));
		
		Employee employee1 = new Employee("RENE",schedule1);
		Employee employee2 = new Employee("ASTRID",schedule2);
		Employee employee3 = new Employee("ANDRES",schedule3);
		
		Company company = new Company();
		company.addEmployee(employee1);
		company.addEmployee(employee2);
		company.addEmployee(employee3);
		
		String [] resultMatch = AdminCompany.findMatchEmployess(company);
		
		assertEquals("RENE-ASTRID:  2", resultMatch[0]);
		assertEquals("RENE-ANDRES:  2", resultMatch[1]);
		assertEquals("ASTRID-ANDRES:  3", resultMatch[2]);
		
	}
	
	
	
}
