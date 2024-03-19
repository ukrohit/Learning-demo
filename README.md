### Ref : https://javaconceptoftheday.com/solving-real-time-queries-using-java-8-features-employee-management-system/

### How many male and female employees are there in the organization?
	For queries such as above where you need to group the input elements, use the Collectors.groupingBy() method. 
 we use Collectors.groupingBy() method which takes two arguments. We pass Employee::getGender as first argument which groups the input elements based on gender and
 Collectors.counting() as second argument which counts the number of entries in each group.\
	
  	Map<String, Long> noOfMaleAndFemaleEmployees=
  		employeeList.stream().collect(Collectors.groupingBy(Employee::getGender, Collectors.counting()));
           
  		System.out.println(noOfMaleAndFemaleEmployees);
		
### Print the name of all departments in the organization?
	Use distinct() method after calling map(Employee::getDepartment) on the stream. It will return unique departments.

	employeeList.stream()
            .map(Employee::getDepartment)
            .distinct()
            .forEach(System.out::println);
      
   	Set<String> allDepartment = employeeList.stream()
   		.map(e -> e.getDepartment())
   		.collect(Collectors.toCollection(TreeSet::new));
		
		System.out.println("All Department : " + allDepartment);

	List<String> distinctDepartment = 
		employeeList.stream()
			.map(Employee::getDepartment)
			.distinct()
			.sorted()
			.collect(Collectors.toList());

		System.out.println("distinctDepartment Department : " + distinctDepartment);
		
### What is the average age of male and female employees?

	Use same method as query 3.1 but pass Collectors.averagingInt(Employee::getAge) as the second argument to Collectors.groupingBy().

	Map<String, Double> averageAge = 
		employeeList.stream().collect(Collectors.groupingBy(Employee::getGender,Collectors.averagingDouble(Employee::getAge)));
			
			System.out.println("Average age of male and female : "+averageAge);
			
### Get the details of highest paid employee in the organization?

	Optional<Employee> maxSalaryEmployee = employeeList.stream().collect(Collectors.maxBy(Comparator.comparing(Employee::getSalary)));
			
			System.out.println(" AMx salary employee : "+maxSalaryEmployee.get());
			
### Get the names of all employees who have joined after 2015?
	
	List<String> empNameJoinAfter2015 = employeeList.stream().filter(e -> e.getYearOfJoining() > 2015)
				.map(Employee::getName).collect(Collectors.toList());

		System.out.println("Employee name join after 2015 : " + empNameJoinAfter2015);
		
### Count the number of employees in each department?
	
				Map<String, Long> noOfEmpInDept = 
				employeeList.stream()
				.collect(Collectors.groupingBy(Employee::getDepartment,Collectors.counting()));
				
### What is the average salary of each department?

	Map<String, Double> averageDeptSalary = employeeList.stream().collect(
				Collectors.groupingBy(Employee::getDepartment, Collectors.averagingDouble(Employee::getSalary)));

		System.out.println("Average department salary " + averageDeptSalary);
		
### Get the details of youngest male employee in the product development department?
	For this query, use Stream.filter() method to filter male employees in product development department and to find youngest among them, 
 use Stream.min() method.
	
	Optional<Employee> youngEmpInDept = employeeList.stream()
				.filter(e -> e.getGender()=="Male" && e.getDepartment().equals("Product Development"))
				.collect(Collectors.minBy(Comparator.comparing(Employee::getAge)));

		System.out.println("Young Employee in Deparment : " + youngEmpInDept);
		
### Who has the most working experience in the organization?

	For this query, sort employeeList by yearOfJoining in natural order and first employee will have most working experience in the organization. 
 To solve this query, we will be using sorted() and findFirst() methods of Stream.
	
		Optional<Employee> findFirst = employeeList.stream()
		.sorted(Comparator.comparingInt(Employee::getYearOfJoining))
		.findFirst();
		
		System.out.println("MOst Experience employee : "+findFirst);
		
		Optional<Employee> mostExpeEmp = employeeList.stream().collect(Collectors.minBy(Comparator.comparingInt(Employee::getYearOfJoining)));
		
		System.out.println("MOst mostExpeEmp  employee : "+mostExpeEmp);
	
	
### What is the average salary and total salary of the whole organization?

	For this query, we use Collectors.summarizingDouble() on Employee::getSalary which will return statistics of the employee salary
 like max, min, average and total.

	DoubleSummaryStatistics statistics = employeeList.stream().collect(Collectors.summarizingDouble(Employee::getSalary));
		
		System.out.println("Average salary : "+statistics.getAverage());
		System.out.println("Total salary "+statistics.getSum());

###  Separate the employees who are younger or equal to 25 years from those employees who are older than 25 years.

	we will be using Collectors.partitioningBy() method which separates input elements based on supplied Predicate.
	
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
		
		
###  Who is the oldest employee in the organization? What is his age and which department he belongs to?

	Optional<Employee> employeeOptional = employeeList.stream().collect(Collectors.maxBy(Comparator.comparingInt(Employee::getYearOfJoining)));
		
		employeeOptional.get().getAge();
		
		employeeOptional.get().getDepartment();
	
	
### How To Sort HashMap By Values Using Java 8 comparingByValue()?
	
		 Map<Integer, String> sortedIdNameMap 
        = idNameMap.entrySet()
                   .stream()
                   .sorted(Entry.comparingByValue())
                   .collect(Collectors.toMap(Entry::getKey, Entry::getValue, (e1,  e2) -> e1, LinkedHashMap::new));
                   

 
