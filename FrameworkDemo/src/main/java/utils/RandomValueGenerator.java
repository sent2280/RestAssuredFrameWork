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

}
