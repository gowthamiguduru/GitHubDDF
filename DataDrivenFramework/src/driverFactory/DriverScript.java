package driverFactory;

import java.io.File;
import java.util.Iterator;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import commonFunction.Functionlibrary;
import config.AppUtil;
import utilities.ExcelFileUtil;

public class DriverScript extends AppUtil{
String inputpath="./FileInput/Login.xlsx";
String outputpath="./FileOutPut/LoginResult.xlsx";
ExtentReports reports;
ExtentTest logger;
@Test
public void startTest() throws Throwable
{
	//define path of html
	reports=new ExtentReports("./Reports/datadrivenresults.html");
//create object for Excelfileutil class for calling that methods
	ExcelFileUtil xl= new ExcelFileUtil(inputpath);  
	//count no of rows in sheet
	int rc=xl.rowCount("Logindata");
	Reporter.log("No of rows:  "+rc,true);
	//iterate all rows
		for (int i=1;  i <=rc;  i++) {
			//testcase starts
		logger=reports.startTest("login Test");
		logger.assignAuthor("Gowthami");
		logger.log(LogStatus.PASS, "Login success");
		

		//read username and password
	String username=xl.getCellData("Logindata", i, 0);
		String password=xl.getCellData("Logindata", i, 1);
		boolean res=Functionlibrary.login(username, password);
		
		if(res)
		{
			//if it is true write as login success into results cell
			xl.setCellData("Logindata", i, 2, "Login Success", outputpath);
			//wite pass into status
			xl.setCellData("Logindata", i, 3, "Pass", outputpath);
		}
		else
		{
			//take a sctreenshot for fail 
			File screen=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screen, new File("./ScreenShot/Iteration/"+i+"Loginpage.png"));
			//capture error message
		String Error_Message =	driver.findElement(By.xpath(conpro.getProperty("ObjError"))).getText();
		xl.setCellData("Logindata", i, 2, Error_Message, outputpath);
		
	xl.setCellData("Logindata", i, 3, "fail", outputpath);
	logger.log(LogStatus.FAIL, Error_Message);
		}
	reports.endTest(logger);
	reports.flush();
		}
	
	
}

}

