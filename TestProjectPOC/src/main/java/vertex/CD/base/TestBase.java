package vertex.CD.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;

import vertex.CD.util.TestUtil;

public class TestBase {
	
public static WebDriver driver;

public static Properties prop;


public TestBase()
{
	try {
		
		prop = new Properties();
		FileInputStream ip = new FileInputStream ("C:\\Users\\karthik.k\\New Eclipse-Workspace\\TestProjectPOC\\src\\main\\java\\vertex\\CD\\config\\config.properties");
		prop.load(ip);
	    }
	catch (FileNotFoundException e) 
	      {
	    	e.printStackTrace();
	      }
	
	catch (IOException e)
	{
	e.printStackTrace();	
	}
}

public static void initialization()
{
	String browserName = prop.getProperty("browser");
	String chromepath = prop.getProperty("chromepath");
	String ffpath = prop.getProperty("firefoxpath");
	

	if (browserName.equals("chrome"))
	{
		System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
		//System.setProperty("webdriver.chrome.driver", "C:\\Automation\\Working Project\\DataValidationPOC\\Drivers\\chromedriver.exe");
		//System.setProperty("webdriver.chrome.driver", chromepath);
		//System.setProperty("webdriver.chrome.driver", "./Drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		
	}
	
	else if (browserName.equals("firefox"))
	{
		//System.setProperty("webdriver.chrome.driver", "D:\\Selenium\\99\\chromedriver.exe");
		System.setProperty("webdriver.gecko.driver", ffpath);
		driver = new ChromeDriver();
	}
	
	
	
	driver.manage().window().maximize();
	driver.manage().deleteAllCookies();
	driver.manage().timeouts().pageLoadTimeout(TestUtil.Page_load_timeout, TimeUnit.SECONDS);
	driver.manage().timeouts().implicitlyWait(TestUtil.Implicit_wait, TimeUnit.SECONDS);;
	
	//String URL = "http://prashant.s@vertexcs.com:Qa@123456@"+prop.getProperty("baseURL");
	
	
	String userName ="Karthik.K";
	  String password ="Vertex@1";
	 String domain ="intranet.vertexcs.com/SitePages/downloads.aspx?ctype=Downloads"; 
	 // String url= "http://"+userName+":"+password+"@"+prop.getProperty("baseURL");
	  String url1 ="https://www.google.com/";
	 // System.out.println("Application is "+url);
	//driver.get(prop.getProperty("baseURL"));
	  String url ="https://www.yahoo.com/";
	  
	driver.get(url);
	 System.out.println("First URL Title is "+driver.getTitle());
	
	//openNewTab(driver,""1);
	
	//openNewTab(driver, url, 1);
    openNewTab(driver, url1, 1);
    
    System.out.println("Second URL Title is "+driver.getTitle());

}

public static Connection  getDBConnection() {
	Connection connection = null;
	 try {
		 
         String dbURL = "jdbc:sqlserver://192.168.0.20\\SQLEXPRESS;database=VertexEmp_Prod";
         String user = "VertexProdUser";
         String pass = "abc123@@";
         connection = DriverManager.getConnection(dbURL, user, pass);
         if (connection != null) {
             DatabaseMetaData dm = (DatabaseMetaData) connection.getMetaData();
   
         }

     } catch (SQLException ex) {
         ex.printStackTrace();
     } 
     
	 return connection;
}

//************ writen by Karthk************************

public static void openNewTab(WebDriver webDriver, String url, int position) {
    ((JavascriptExecutor) webDriver).executeScript("window.open()");

    ArrayList<String>  tabs = new ArrayList<>(webDriver.getWindowHandles());
    System.out.println("tabs : " + tabs.size() + " >position: " + position + " >\t" + url);
    webDriver.switchTo().window(tabs.get(position));

    webDriver.get(url);
 }
}
