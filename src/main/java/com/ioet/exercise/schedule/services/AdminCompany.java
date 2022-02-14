package com.ioet.exercise.schedule.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;

import com.ioet.exercise.schedule.entity.Company;
import com.ioet.exercise.schedule.entity.Employee;
import com.ioet.exercise.schedule.entity.Schedule;

public class AdminCompany {

	/**
	 * Built the Company from a text file
	 * @param  File path with employee information
	 * @return Company object with a list of employess and her schedules
	 */
	public static Company builtCompany(String routeFile) {
		Company company = new Company();

		File file = new File(routeFile);
		FileReader fileReader = null;
		BufferedReader br = null;

		String line;
		try {
			fileReader = new FileReader(file);
			br = new BufferedReader(fileReader);

			while ((line = br.readLine()) != null) {
				if (line.length() == 0) {
					continue;
				}
				company.addEmployee(builtEmployee(line));
			}
		} catch (FileNotFoundException e) {
			System.out.println("File not found error " + e);
		} catch (IOException e) {
			System.out.println("Error opening file "+e);
		} finally {
			try {
				if (br != null) {
					br.close();
				}
			} catch (IOException e) {
				System.out.println("Error closing BufferedReader "+e);
			}
			try {
				if (fileReader != null) {
					fileReader.close();
				}
			} catch (IOException e) {
				System.out.println("Error closing FileReader "+e);
			}
		}
		System.out.println("Company with a list of employess created successfully from a txt file");
		return company;
	}
	

	/**
	 * Built the Employee object with a line of characters
	 * @param Characters with employee information
	 * @return An a Employee Object
	 */
	public static Employee builtEmployee(String line) {
		Employee employee = new Employee();
		Schedule[] schedule = null;

		try {
			String[] separatedLine = line.split("=");

			String name = separatedLine[0];
			String sheduleCombinated = separatedLine[1];

			sheduleCombinated = sheduleCombinated.replace(" ", "");

			String[] schedulesString = sheduleCombinated.split(",");

			schedule = new Schedule[schedulesString.length];

			for (int i = 0; i < schedulesString.length; i++) {
				String day = schedulesString[i].substring(0, 2);
				String scheduleTime = schedulesString[i].substring(2);

				String[] hours = scheduleTime.split("-");

				schedule[i] = new Schedule(day, LocalTime.parse(hours[0]), LocalTime.parse(hours[1]));
			}

			employee.setName(name);
			employee.setSchedule(schedule);

		} catch (Exception e) {
			System.out.println("Error building employees "+e);
		}
		return employee;
	}
	

	/**
	 * Finds matches between schedules and returns the keys pair
	 * of the person's name and the match number
	 * @param The Company object that contains a list of employees
	 * @return An array of type String with the matches of employee schedules
	 */
	public static String[] findMatchEmployess(Company company) {
		ArrayList<Employee> employeeList = null;
		String [] resultArray = null;
		String results = "";
		System.out.println("Looking for coincidences...");
		try {
			employeeList = company.getEmployees();

			for (int i = 0; i < employeeList.size() - 1; i++) {
				for (int j = i + 1; j < employeeList.size(); j++) {
					
					int frecuency = findScheduleFrequency(employeeList.get(i).getSchedule(),employeeList.get(j).getSchedule());
					
					if (frecuency > 0) {
						results = results + employeeList.get(i).getName() + "-" + employeeList.get(j).getName() + ":  "	+ frecuency + ",";
						System.out.println(employeeList.get(i).getName() + "-" + employeeList.get(j).getName() + ":  "	+ frecuency);
					}
					frecuency = 0;
				}
			}
			if (results.length()>1) {
				resultArray = results.split(",");
				System.out.println("No coincidences...");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error finding matches in employee schedules "+e);
		}

		return resultArray;

	}

	/**
	 * Find the frequency of coincidences of the timetables hours
	 * Compare two arrays of schedules 
	 * @param Array Objects type Schedule
	 * @param Array Objects type Schedule
	 * @return The matching number found in the schedules
	 */
	public static int findScheduleFrequency(Schedule[] employeeASchedules, Schedule[] employeeBSchedules) {

		int frecuency = 0;
		try {

			// Iterate Schedules to compare time and find match between employee
			// A and B
			for (int i = 0; i < employeeASchedules.length; i++) {
				for (int j = 0; j < employeeBSchedules.length; j++) {

					// Call method to known if has a coincidence in a hours
					if (hasCoincidence(employeeASchedules[i], employeeBSchedules[j])) {
						// Count the coincidences
						frecuency++;
					}
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error finding matches between two array of schedules "+e);
		}

		return frecuency;
	}
	

	/**
	 * Find a coincidence in hour and return true if find a coincidence
	 * @param Object type Schedule
	 * @param Object type Schedule
	 * @return True if exist a coincidence
	 */
	public static boolean hasCoincidence(Schedule scheduleA, Schedule scheduleB) {

		try {
			if (scheduleA.getDay().equals(scheduleB.getDay())) {

				int startHourA = scheduleA.getStartTime().getHour();
				int startMinuteA = scheduleA.getStartTime().getMinute();
				int endHourA = scheduleA.getEndTime().getHour();
				int endMinuteA = scheduleA.getEndTime().getMinute();
				
				int startHourB = scheduleB.getStartTime().getHour();
				int startMinuteB = scheduleB.getStartTime().getMinute();
				int endHourB = scheduleB.getEndTime().getHour();
				int endMinuteB = scheduleB.getEndTime().getMinute();

				if (endHourA > startHourB || (endHourA == startHourB && endMinuteA > startMinuteB)) {

					if (startHourA < endHourB || (startHourA == endHourB && startMinuteA < endMinuteB)) {
						return true;
					}
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error finding matches at scheduled times "+e);
		}
		return false;
	}
	
	
	/**
	 * Create a new text file
	 * @param Path where the file is created
	 * @param Array of characters to be entered in the file
	 */
	public static void createFile(String routeFile, String [] content) {

		FileWriter file = null;
		PrintWriter pw = null;
		
		try {
			file = new FileWriter(routeFile, true);
			pw = new PrintWriter(file);
			
			for (String line : content) {
				pw.println(line);
			}
			System.out.println("File created at the route : " + routeFile);

		} catch (IOException e) {
			System.out.println("Error creating file "+e);
		} finally {

			try {
				if (null != file) {
					file.close();
				}
			} catch (Exception e) {
				System.out.println("Error closing the file "+e);
			}

		}
	}
}
