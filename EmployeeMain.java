package com.java8.realquery;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EmployeeMain {

	static List<Employee> employeeList = new ArrayList<>();

	public static void getEmployee() {
		employeeList.add(new Employee(111, "Jiya Brein", 32, "Female", "HR", 2011, 25000.0));
		employeeList.add(new Employee(122, "Paul Niksui", 25, "Male", "Sales And Marketing", 2015, 13500.0));
		employeeList.add(new Employee(133, "Martin Theron", 29, "Male", "Infrastructure", 2012, 18000.0));
		employeeList.add(new Employee(144, "Murali Gowda", 28, "Male", "Product Development", 2014, 32500.0));
		employeeList.add(new Employee(155, "Nima Roy", 27, "Female", "HR", 2013, 22700.0));
		employeeList.add(new Employee(166, "Iqbal Hussain", 43, "Male", "Security And Transport", 2016, 10500.0));
		employeeList.add(new Employee(177, "Manu Sharma", 35, "Male", "Account And Finance", 2010, 27000.0));
		employeeList.add(new Employee(188, "Wang Liu", 31, "Male", "Product Development", 2015, 34500.0));
		employeeList.add(new Employee(199, "Amelia Zoe", 24, "Female", "Sales And Marketing", 2016, 11500.0));
		employeeList.add(new Employee(200, "Jaden Dough", 38, "Male", "Security And Transport", 2015, 11000.5));
		employeeList.add(new Employee(211, "Jasna Kaur", 27, "Female", "Infrastructure", 2014, 15700.0));
		employeeList.add(new Employee(222, "Nitin Joshi", 25, "Male", "Product Development", 2016, 28200.0));
		employeeList.add(new Employee(233, "Jyothi Reddy", 27, "Female", "Account And Finance", 2013, 21300.0));
		employeeList.add(new Employee(244, "Nicolus Den", 24, "Male", "Sales And Marketing", 2017, 10700.5));
		employeeList.add(new Employee(255, "Ali Baig", 23, "Male", "Infrastructure", 2018, 12700.0));
		employeeList.add(new Employee(266, "Sanvi Pandey", 26, "Female", "Product Development", 2015, 28900.0));
		employeeList.add(new Employee(277, "Anuj Chettiar", 31, "Male", "Product Development", 2012, 35700.0));

	}

	public static void main(String[] args) {

		getEmployee();

		/// How many male and female employees are there in the organization?

		Map<String, Long> maleFemaleCount = employeeList.stream()
				.collect(Collectors.groupingBy(Employee::getGender, Collectors.counting()));

		System.out.println("Male female count : " + maleFemaleCount);

		// Print the name of all departments in the organization?

		Set<String> allDepartment = employeeList.stream().map(e -> e.getDepartment())
				.collect(Collectors.toCollection(TreeSet::new));

		System.out.println("All Department : " + allDepartment);

		List<String> distinctDepartment = employeeList.stream().map(Employee::getDepartment).distinct().sorted()
				.collect(Collectors.toList());

		System.out.println("distinctDepartment Department : " + distinctDepartment);

		// What is the average age of male and female employees?

		Map<String, Double> averageAge = employeeList.stream()
				.collect(Collectors.groupingBy(Employee::getGender, Collectors.averagingDouble(Employee::getAge)));

		System.out.println("Average age of male and female : " + averageAge);

		// Get the details of highest paid employee in the organization?

		Optional<Employee> maxSalaryEmployee = employeeList.stream()
				.collect(Collectors.maxBy(Comparator.comparing(Employee::getSalary)));

		System.out.println(" AMx salary employee : " + maxSalaryEmployee.get());

		// Get the names of all employees who have joined after 2015?

		List<String> empNameJoinAfter2015 = employeeList.stream().filter(e -> e.getYearOfJoining() > 2015)
				.map(Employee::getName).collect(Collectors.toList());

		System.out.println("Employee name join after 2015 : " + empNameJoinAfter2015);

		// Count the number of employees in each department?

		Map<String, Long> noOfEmpInDept = employeeList.stream()
				.collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()));

		System.out.println("No of employee in each dept : " + noOfEmpInDept);

		// What is the average salary of each department?

		Map<String, Double> averageDeptSalary = employeeList.stream().collect(
				Collectors.groupingBy(Employee::getDepartment, Collectors.averagingDouble(Employee::getSalary)));

		System.out.println("Average department salary " + averageDeptSalary);

		// Get the details of youngest male employee in the product development
		// department?

		Optional<Employee> youngEmpInDept = employeeList.stream()
				.filter(e -> e.getDepartment().equals("Product Development"))
				.collect(Collectors.minBy(Comparator.comparing(Employee::getAge)));

		System.out.println("Young Employee in Deparment : " + youngEmpInDept);

		// Who has the most working experience in the organization?

		Optional<Employee> findFirst = employeeList.stream().sorted(Comparator.comparingInt(Employee::getYearOfJoining))
				.findFirst();

		System.out.println("MOst Experience employee : " + findFirst);

		Optional<Employee> mostExpeEmp = employeeList.stream()
				.collect(Collectors.minBy(Comparator.comparingInt(Employee::getYearOfJoining)));

		System.out.println("MOst mostExpeEmp  employee : " + mostExpeEmp);

		// statistics on employee salary min,max,sum,average and count

		DoubleSummaryStatistics statistics = employeeList.stream()
				.collect(Collectors.summarizingDouble(Employee::getSalary));

		System.out.println("Average salary : " + statistics.getAverage());
		System.out.println("Total salary " + statistics.getSum());

		// partition will returen the map of true and false and accept the predicate

		Map<Boolean, List<Employee>> partitionMap = employeeList.stream()
				.collect(Collectors.partitioningBy(e -> e.age > 25));

		Set<Entry<Boolean, List<Employee>>> entrySet = partitionMap.entrySet();

		for (Entry<Boolean, List<Employee>> entry : entrySet) {

			if (entry.getKey()) {
				System.out.println("elders employees ");
			} else {
				System.out.println("Younger employee ");
			}

			entry.getValue();
		}
		
		
		// Who is the oldest employee in the organization? What is his age and which department he belongs to?


		Optional<Employee> employeeOptional = employeeList.stream().collect(Collectors.maxBy(Comparator.comparingInt(Employee::getYearOfJoining)));
		
		employeeOptional.get().getAge();
		
		employeeOptional.get().getDepartment();
		
		
		Optional<String> map = employeeList.stream().filter(e->e.getAge()>25)
		//.map(Employee::getName)
		.collect(Collectors.maxBy(Comparator.comparing(Employee::getSalary))).map(Employee::getName);
	
	
	
		
		
	}
	
	

}
