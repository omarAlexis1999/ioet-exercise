package com.ioet.exercise.schedule.test;



import com.ioet.exercise.schedule.entity.Employee;
import com.ioet.exercise.schedule.services.AdminCompany;

import junit.framework.TestCase;

public class TestEmployee extends TestCase {
	
	
	public void testBuiltEmployees(){
		
		String line1 = "RENE=MO10:00-12:00,TU10:00-12:00,TH01:00-03:00,SA14:00-18:00,SU20:00-21:00";
		String line2 = "ASTRID=MO10:00-12:00,TH12:00-14:00,SU20:00-21:00";
		String line3 = "ANDRES=MO10:00-12:00,TH12:00-14:00,SU20:00-21:00";
		
		Employee employee1 = AdminCompany.builtEmployee(line1);
		Employee employee2 = AdminCompany.builtEmployee(line2);
		Employee employee3 = AdminCompany.builtEmployee(line3);
		
		//Compare names
		assertEquals("RENE",employee1.getName());
		assertEquals("ASTRID",employee2.getName());
		assertEquals("ANDRES",employee3.getName());
		
		//Compare day of week
		assertEquals("MO",employee1.getSchedule()[0].getDay());
		assertEquals("TH",employee2.getSchedule()[1].getDay());
		assertEquals("SU",employee3.getSchedule()[2].getDay());
		
		//Compare start hours
		assertEquals(10,employee1.getSchedule()[0].getStartTime().getHour());
		assertEquals(12,employee2.getSchedule()[1].getStartTime().getHour());
		assertEquals(20,employee3.getSchedule()[2].getStartTime().getHour());
	}
}
