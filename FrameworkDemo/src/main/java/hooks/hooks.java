package hooks;

import org.testng.annotations.AfterMethod;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.json.simple.JSONObject;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestLogSpecification;
import io.restassured.specification.RequestSpecification;
import reports.ExtentReport;
import reports.HTMLDetailReportGenerator;
import reports.HTMLSummaryReportGenerator;
import utils.FileUtil;
import utils.JSONProcesser;

public class hooks 
{
	public static RequestSpecification httpRequest;
	public static Response response = null;
	public String empID = "2";
	public String baseURIGlobal = null;
	public Properties log4jProperties = null;
	public Properties testProjectProperties = null;
	//public ExtentReport extentReport = new ExtentReport();
	
	//Commenting Log4j related code
	public static Logger logger;
	
	@BeforeSuite()
	@Parameters("env")
	public void setup(String env) {
		log4jProperties = new Properties();
		testProjectProperties = new Properties();
		
		try {
			log4jProperties.load(new FileReader(System.getProperty("user.dir") + "/config/log4j.properties"));
			testProjectProperties.load(new FileReader(System.getProperty("user.dir") + "./config/testproject.properties"));
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		logger = Logger.getLogger("EmployeesRestAPI");
		PropertyConfigurator.configure(log4jProperties);
		logger.setLevel(Level.DEBUG);
		
		//String mEnv = System.getProperty("env");
		//String mEnv = "QA";
		//RestAssured.baseURI = getURL(mEnv);
		RestAssured.baseURI = getURL(env);
		System.out.println("Environment variable is " + env);
		System.out.println("Base URL is " + getURL(env));
	}
	
	@AfterSuite
	public void reportGeneration() throws IOException {
		logger.info("Executing report Generation");
		HTMLSummaryReportGenerator htmlReportGenerator =  new HTMLSummaryReportGenerator();
		htmlReportGenerator.createSummaryReport();
		
		HTMLDetailReportGenerator  htmlDetailReportGenerator = new HTMLDetailReportGenerator();
		htmlDetailReportGenerator.createDetailReport();
		
	}
	
	private String getURL(String env) {
	
		switch(env) {		
		case "QA":
			return testProjectProperties.getProperty("testProject.QA");
		case "Prod":
			return testProjectProperties.getProperty("testProject.Prod");
		case "UAT":
			return testProjectProperties.getProperty("testProject.UAT");
		default:
			System.out.println("Please provide proper environment value.. It should be one among QA/Prod/UAT");
			return null;
		}
		
	}
	
	
}
