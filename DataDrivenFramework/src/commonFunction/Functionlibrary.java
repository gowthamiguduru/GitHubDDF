 package commonFunction;

import java.time.Duration;

import org.openqa.selenium.By;
import org.testng.Reporter;

import config.AppUtil;

public class Functionlibrary extends AppUtil {
	public static boolean login(String username,String password)
	{
		driver.get(conpro.getProperty("Url"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.xpath(conpro.getProperty("Objuser"))).sendKeys(username);
		driver.findElement(By.xpath(conpro.getProperty("Objpass"))).sendKeys(password);
		driver.findElement(By.xpath(conpro.getProperty("ObjLogin"))).click();
		String Expected="dashboard";
		String Actual=driver.getCurrentUrl();
		if(Actual.contains(Expected))
		{
			Reporter.log("Login success:   "+Actual+"  "+Expected,true);
			return true;
		}
		else
		{
			Reporter.log("Login Fail:   "+Actual+"  "+Expected,true);
			return false;
		}



	}

}
