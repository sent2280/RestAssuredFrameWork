package testcasesBDDStyle;

import java.io.Serializable;

import utils.RandomValueGenerator;

public class EmployeeRecord1 implements Serializable {
	
	 String FirstName;
	 String LastName;
	 String UserName;
	 String Password;
	 String Email;
	
	public void setfirstName() {
		this.FirstName = RandomValueGenerator.getFirstName();
	}
	
	public void setLastName() {
		this.LastName = RandomValueGenerator.getLastName();
	}
	
	public void setUserName() {
		this.UserName = RandomValueGenerator.getUserName();
	}
	
	public void setpassword() {
		this.Password = RandomValueGenerator.getPassword();
	}
	
	public void setemail() {
		this.Email = RandomValueGenerator.getEmail();
	}
	
	public String getfirstName() {
		return FirstName;
	}
	
	public String getLastName() {
		return LastName;
	}
	
	public String getUserName() {
		return UserName;
	}
	
	public String getpassword() {
		return Password;
	}
	
	public String getemail() {
		return Email;
	}

}
