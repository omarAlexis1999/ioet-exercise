package com.ioet.exercise.schedule.test;

import com.ioet.exercise.schedule.entity.Company;
import com.ioet.exercise.schedule.services.AdminCompany;
import com.ioet.exercise.schedule.services.AdminFile;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		String route = AdminFile.getRouteByConsole("Enter the path of the employee file to read : ");
		//D:\Documentos\Programacion\Utils\Files\employessAndSchedules.txt
		
		Company company = AdminCompany.builtCompany(route);
		String [] coincidences = AdminCompany.findMatchEmployess(company);
		
		String routeNewFile = AdminFile.getRouteByConsole("Enter the route of the new file to create : ");
		//D:\Documentos\Programacion\Utils\Files
		AdminCompany.createFile(routeNewFile+"\\Coincidences.txt", coincidences);
	}

}
