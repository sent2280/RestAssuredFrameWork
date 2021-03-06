package utils;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomValueGenerator {
	
	public static String getEmpName() {		
		String generatedString = RandomStringUtils.randomAlphabetic(1);
		return "John" + generatedString;
	}
	
	public static String getSalary() {		
		String salary = RandomStringUtils.randomNumeric(4);
		return salary;
	}
	
	public static String getAge() {		
		String age = RandomStringUtils.randomNumeric(2);
		return age;
	}
	
	public static String getFirstName() {
		String generatedString = RandomStringUtils.randomAlphabetic(1);
		return ("John"+generatedString);
	}

	public static String getLastName() {
		String generatedString = RandomStringUtils.randomAlphabetic(1);
		return ("Kenedy"+generatedString);
	}
	
	public static String getUserName() {
		String generatedString = RandomStringUtils.randomAlphabetic(3);
		return ("John"+generatedString);
	}
	
	public static String getPassword() {
		String generatedString = RandomStringUtils.randomAlphabetic(3);
		return ("John"+generatedString);
	}
	
	public static String getEmail() {
		String generatedString = RandomStringUtils.randomAlphabetic(3);
		return (generatedString+"gmail.com");
	}
	

}
