package vertex.CD.testcases;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import vertex.CD.base.TestBase;

import vertex.CD.pages.EmployeeRnD;
import vertex.CD.util.CustomListener;
import vertex.CD.util.XLUtility;


@Listeners(CustomListener.class)
public class EmployeeTestP1 extends TestBase{
	
	EmployeeRnD loginPage;
	Connection con = null;

		public EmployeeTestP1()
	{
		super();
	}
	
	@DataProvider(name="WorkOrderData")
	String [][] getData( ) throws IOException {
		
		String path="C:\\Users\\karthik.k\\New Eclipse-Workspace\\TestProjectPOC\\src\\main\\java\\vertex\\CD\\testdata\\WorkOrderData.xlsx";
		
		int rownum = XLUtility.getRowCount(path, "EmployeeTestF1");
		int colcount = XLUtility.getCellCount(path, "EmployeeTestF1", 1);
		
		String logindata[][] = new String [rownum][colcount];
		
		for(int i=1;i<=rownum;i++)
		{
			for (int j=0;j<colcount;j++)
			{
				logindata[i-1][j]=XLUtility.getCellData(path, "EmployeeTestF1", i, j);
			}
		}
		
		return logindata;
		
	}
	

@BeforeClass
public void setup() throws InterruptedException 
{
	initialization();
	loginPage = new EmployeeRnD();
}

@BeforeClass
public void setDB() {	
    con = getDBConnection();
}

@BeforeClass
public void clearData() {
	 String path="C:\\Users\\karthik.k\\New Eclipse-Workspace\\TestProjectPOC\\src\\main\\java\\vertex\\CD\\testdata\\WorkOrderData.xlsx";
	  	//Delete here
		try {
			XLUtility.deleteData(path,"EmployeeTestResult");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}

@Test(priority=1, dataProvider="WorkOrderData", enabled=true)
public void EmployeeRnDTestDDT(String firstname, String lastname , String email , String phone , String city , String pincode) throws IOException, InterruptedException
{
	loginPage = loginPage.login(firstname,lastname,email,phone,city,pincode);
	
  Thread.sleep(5000);
    
    String firstName = "";
    String lastName = "";
    String emailId = "";
    String Phone = "";
    String City = "";
    String Pincode = "";
    try {
        String sql = "select top 1 s.Id, FirstName,LastName,Email,Phone,City,PinCode from Tb_LM_Emp_SRD S\r\n"
        		+ "inner join [Tb_LM_Emp_SRD_City_Info] C on S.Id=C.SRDId order by s.Id desc";
        
        PreparedStatement p = con.prepareStatement(sql);
        ResultSet rs = p.executeQuery();


        // Condition check
        while (rs.next()) {

            int id = rs.getInt("id");
            firstName = rs.getString("FirstName");
            lastName = rs.getString("LastName");
            emailId = rs.getString("Email");
            Phone = rs.getString("Phone");
            City = rs.getString("City");
            Pincode = rs.getString("Pincode");
            
            System.out.println(id + "\t\t" + firstName + "\t\t" + lastName+ "\t\t" + emailId+ "\t\t" + Phone + "\t\t" + City + "\t\t" + Pincode);

            }
    }
    
    catch (SQLException e) {
        System.out.println(e);
    }
    
    boolean result = firstname.equals(firstName) && lastname.equals(lastName) &&
    		email.equals(emailId) && phone.equals(Phone) && city.equals(City) && pincode.equals(Pincode); 
    String path="C:\\Users\\karthik.k\\New Eclipse-Workspace\\TestProjectPOC\\src\\main\\java\\vertex\\CD\\testdata\\WorkOrderData.xlsx";
	
    String formattedResult = "Fail";
		if(result == true) {
		formattedResult = "Pass";
	}
		
	    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
	    Date date = new Date();
	    String date1 = formatter.format(date);
	    
	    System.out.println(formattedResult);
		
	XLUtility.setResultData(path,"EmployeeTestResult",firstname,lastname,email,phone,city,pincode,formattedResult,date1);

	 // System.out.println(formattedResult);
	 // System.out.println(result);
	
    Assert.assertEquals(firstname, firstName);
    Assert.assertEquals(lastname, lastName);
    Assert.assertEquals(email, emailId);
    Assert.assertEquals(phone, Phone);
	Assert.assertEquals(city, City);
	Assert.assertEquals(pincode, Pincode);

    }


@AfterClass
public void tearDown()
{
	driver.quit();
	
}


}
