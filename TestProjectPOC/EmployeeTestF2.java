package vertex.CD.testcases;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.testng.Assert;
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
public class EmployeeTestF2 extends TestBase{
	
	EmployeeRnD loginPage;
	Connection con = null;

		public EmployeeTestF2()
	{
		super();
	}
	
	@DataProvider(name="WorkOrderData")
	String [][] getData( ) throws IOException {
		
		String path="C:\\Automation\\Working Project\\TestProjectPOC\\src\\main\\java\\vertex\\CD\\testdata\\WorkOrderData.xlsx";
		
		int rownum = XLUtility.getRowCount("EmployeeTestF2");
		int colcount = XLUtility.getCellCount("EmployeeTestF2", 1);
		
		String logindata[][] = new String [rownum][colcount];
		
		for(int i=1;i<=rownum;i++)
		{
			for (int j=0;j<colcount;j++)
			{
				logindata[i-1][j]=XLUtility.getCellData(path, "EmployeeTestF2", i, j);
			}
		}
		
		return logindata;
		
	}
	

@BeforeMethod
public void setup() throws InterruptedException 
{
	initialization();
	loginPage = new EmployeeRnD();
}

@BeforeClass
public void setDB() {	
    con = getDBConnection();
}


@Test(priority=1, dataProvider="WorkOrderData", enabled=true)
public void EmployeeRnDTestDDT(String firstname, String lastname , String email , String phone) throws IOException, InterruptedException
{
	loginPage = loginPage.login(firstname,lastname,email,phone);
	
	Thread.sleep(5000);
    
    String firstName = "";
    String lastName = "";
    String emailId = "";
    try {
        String sql = "select * from [dbo].[Tb_LM_Emp_SRD] where id = 1;";
        PreparedStatement p = con.prepareStatement(sql);
        ResultSet rs = p.executeQuery();


        // Condition check
        while (rs.next()) {

            int id = rs.getInt("id");
            firstName = rs.getString("FirstName");
            lastName = rs.getString("LastName");
            emailId = rs.getString("Email");
            System.out.println(id + "\t\t" + firstName + "\t\t" + lastName+ "\t\t" + emailId);
        }
    }
    
    catch (SQLException e) {
        System.out.println(e);
    }
    Assert.assertEquals(firstname, firstName);
    Assert.assertEquals(lastname, lastName);
    Assert.assertEquals(email, emailId);
    }


@AfterMethod
public void tearDown()
{
	driver.quit();
	
}


}
