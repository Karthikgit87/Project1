package vertex.CD.util;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import vertex.CD.base.TestBase;

public class TestUtil extends TestBase {
	
	public static long Page_load_timeout = 40;
	public static long Implicit_wait = 40;
	
	
//public static void TakeScreenshotAtEndOfTest() throws IOException
//{
//   File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//   String currentDir = System.getProperty("user.dir");
//   FileUtils.copyFile(scrFile, new File(currentDir + "/screenshots/" + System.currentTimeMillis() + ".png"));
//}
	
	public static void captureScreen(WebDriver driver, String tname) throws IOException
	{
		TakesScreenshot ts = ((TakesScreenshot) driver);
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir") + "/Screenshots/" + tname + ".png");
		FileUtils.copyFile(source, target);
		System.out.println("Screenshot captured");
	}
}
