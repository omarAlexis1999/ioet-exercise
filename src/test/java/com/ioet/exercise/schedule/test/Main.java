package com.ioet.exercise.schedule.test;

import com.ioet.exercise.schedule.entity.Company;
import com.ioet.exercise.schedule.services.AdminCompany;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Company company = AdminCompany.builtCompany("D:\\Documentos\\Programacion\\Utils\\Files\\employessAndSchedules.txt");
		String [] coincidences = AdminCompany.findMatchEmployess(company);
		AdminCompany.createFile("C:/Coincidences.txt", coincidences);
	}

}
