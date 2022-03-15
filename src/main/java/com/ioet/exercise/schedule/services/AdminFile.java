package com.ioet.exercise.schedule.services;

import java.util.Scanner;

public class AdminFile {
	
	public static String getRouteByConsole(String message){
		Scanner out = new Scanner(System.in);
		System.out.println(message);
		String textConsole = out.nextLine();
		
		String so = System.getProperty("os.name");
		if (so.equals("Windows 10")) {
			return textConsole.replace("\\", "\\\\");
		}
		
		return textConsole;
	}
}
