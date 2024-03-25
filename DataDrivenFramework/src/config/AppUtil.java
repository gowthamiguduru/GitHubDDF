package config;

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class AppUtil {
	//global statitc object and reuse another class
	public static Properties conpro;
	public static WebDriver driver;
	@BeforeTest
	public static void setup()throws Throwable

	{
		conpro=new Properties();
		conpro.load(new FileInputStream("./PropertyFiles/Environment.properties"));
		if(conpro.getProperty("Browser").equalsIgnoreCase("chrome"))

		{
			driver= new ChromeDriver(); 
		}
		else if(conpro.getProperty("Browser").equalsIgnoreCase("firefox"))
		{
			driver=new FirefoxDriver();
		}
		else
		{
			Reporter.log("Browser value not Matching",true);
		}

	}
	@AfterTest
	public static void tearDown()
	{
		driver.quit();
	}
}



