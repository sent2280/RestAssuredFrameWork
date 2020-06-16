package utils;

import java.io.FileWriter;
import java.io.IOException;

public class FileUtil {
	
	//public FileWriter file;
	
	public FileWriter createFile(FileWriter file,String filePath) {
		
		try {
				file = new FileWriter(System.getProperty("user.dir") + filePath);
				System.out.println("User dir is " + System.getProperty("user.dir"));
			} 
		catch (IOException e) {
			e.printStackTrace();
		}
		return file;
	}
	
	public void flushToFile(FileWriter file,String content) {
		
		try {
			file.write(content);
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
public void closeFile(FileWriter file) {
		
		try {
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}
