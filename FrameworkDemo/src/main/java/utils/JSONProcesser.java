package utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONProcesser {
	
	//Json file name
	public String fileName;
	
	public JSONProcesser(String fileName) {
		this.fileName = fileName;
	}

	 public JSONArray getJsonArrayFromResult(String jsonField) {
		 
		 JSONArray JArrayResult = null;
			
		 JSONParser parser = new JSONParser();
		 try {
			Object object = parser.parse(new FileReader(fileName));
			JSONObject jsonObject = (JSONObject) object;
		    JArrayResult = (JSONArray) jsonObject.get(jsonField);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		 return JArrayResult;
	 }
	 
 public JSONObject getJsonObject() {
		 
	 	JSONObject jsonObject = null;
			
		 JSONParser parser = new JSONParser();
		 try {
			Object object = parser.parse(new FileReader(fileName));
			jsonObject = (JSONObject) object;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		 return jsonObject;
	 }
 
 
 	public String getJsonField(String JsonKey) {
	 
 		JSONObject jsonObject = null;
 		Object fieldObj = null;
		
		 JSONParser parser = new JSONParser();
		 try {
			Object object = parser.parse(new FileReader(fileName));
			jsonObject = (JSONObject) object;
			fieldObj = jsonObject.get("Test suite name");
			System.out.println("Suite Name = " + (String)fieldObj);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		 return (String)fieldObj;
 		
 	
 		
 	}
 
}
