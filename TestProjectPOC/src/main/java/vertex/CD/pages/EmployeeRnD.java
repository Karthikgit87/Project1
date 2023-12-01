package vertex.CD.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import vertex.CD.base.TestBase;

public class EmployeeRnD extends TestBase{
	
	//Page factory - OR
	@FindBy(id="FirstName")
	WebElement employeefirstname;
	
	@FindBy(id="LastName")
	WebElement employeelastname;
	
	@FindBy(id="Email")
	WebElement employeeemail;
	
	@FindBy(id="Phone")
	WebElement employeephone;
	
	@FindBy(id="btnSave")
	WebElement savebtn;
	
	@FindBy(id="City")
	WebElement employeecity;
		
	@FindBy(id="PinCode")
	WebElement employeepincode;
	
	public EmployeeRnD()
	{
		PageFactory.initElements(driver, this);
	}
	
	public String validateLoginPageTitle()
	{
		return driver.getTitle();
	}
	
	
	
	public EmployeeRnD login (String firstname, String lastname , String email, String phone, String city , String pincode ) throws InterruptedException
	{
		employeefirstname.sendKeys(firstname);
		employeelastname.sendKeys(lastname);
		employeeemail.sendKeys(email);
		employeephone.sendKeys(phone);
		employeecity.sendKeys(city);
		employeepincode.sendKeys(pincode);
		savebtn.click();
		
	    
	    
	    return new EmployeeRnD();
	    
	}

}
