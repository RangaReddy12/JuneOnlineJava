package june18;

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Utilities.ExcelFileUtil;

public class DataDriven {
String inputpath="C:\\Selenium11oclock\\Automation_FrameWorks\\TestInput\\Registerdata.xlsx";
String outputpath="C:\\Selenium11oclock\\Automation_FrameWorks\\TestOutput\\Results.xlsx";
String proprertypath="C:\\Selenium11oclock\\Automation_FrameWorks\\PropertyFile\\OR.properties";
WebDriver driver;
ExtentReports report;
ExtentTest test;
Properties p;
FileInputStream fi;
@BeforeTest
public void setup()throws Throwable
{
	report=new ExtentReports("./ExtentReports/Report.html");
	p=new Properties();
	fi=new FileInputStream(proprertypath);
	p.load(fi);
if(p.getProperty("Browser").equalsIgnoreCase("chrome"))
{
System.setProperty("webdriver.chrome.driver", "C:\\Selenium11oclock\\Automation_FrameWorks\\Drivers\\chromedriver.exe");
driver=new ChromeDriver();
driver.get(p.getProperty("Url"));
driver.manage().window().maximize();
}
else if(p.getProperty("Browser").equalsIgnoreCase("firefox"))
{
System.setProperty("webdriver.gecko.driver", "C:\\Selenium11oclock\\Automation_FrameWorks\\Drivers\\geckodriver.exe");
driver=new FirefoxDriver();
driver.get(p.getProperty("Url"));
}
else
{
System.out.println("browser value is not matching");
}
}
@Test
public void verifyRegister()throws Throwable
{
// create object for excelfileutil class to acess excel methods
	ExcelFileUtil xl=new ExcelFileUtil(inputpath);
	//count no of rows in Register sheet
	int rc=xl.rowCount("Register");
	//count no of cells in first row
	int cc=xl.colCount("Register");
	Reporter.log("no of rows ::"+rc+"   "+"no of cell::"+cc,true);
	for(int i=1;i<=rc;i++)
	{
	test=report.startTest("Data Driven Testing");
	String fname=xl.getCellData("Register", i, 0);
	String lname=xl.getCellData("Register", i, 1);
	String phone=xl.getCellData("Register", i, 2);
	String mail=xl.getCellData("Register", i, 3);
	String add1=xl.getCellData("Register", i, 4);
	String add2=xl.getCellData("Register", i, 5);
	String city=xl.getCellData("Register", i, 6);
	String state=xl.getCellData("Register", i, 7);
	String pcode=xl.getCellData("Register", i, 8);
	String country=xl.getCellData("Register", i, 9);
	String username=xl.getCellData("Register", i, 10);
	String password=xl.getCellData("Register", i, 11);
	String cpassword=xl.getCellData("Register", i, 12);
	driver.findElement(By.xpath(p.getProperty("clikRegister"))).click();
	Thread.sleep(2000);
	driver.findElement(By.xpath(p.getProperty("enterfname"))).sendKeys(fname);
	driver.findElement(By.xpath(p.getProperty("enterlname"))).sendKeys(lname);
	driver.findElement(By.xpath(p.getProperty("enterph"))).sendKeys(phone);
	driver.findElement(By.xpath(p.getProperty("entermail"))).sendKeys(mail);
	driver.findElement(By.xpath(p.getProperty("enteradd1"))).sendKeys(add1);
	driver.findElement(By.xpath(p.getProperty("enteradd2"))).sendKeys(add2);
	driver.findElement(By.xpath(p.getProperty("entercity"))).sendKeys(city);
	driver.findElement(By.xpath(p.getProperty("enterstate"))).sendKeys(state);
	driver.findElement(By.xpath(p.getProperty("enterpcode"))).sendKeys(pcode);
	new Select(driver.findElement(By.xpath(p.getProperty("selectcountry")))).selectByVisibleText(country);
	driver.findElement(By.xpath(p.getProperty("enteruser"))).sendKeys(username);
	driver.findElement(By.xpath(p.getProperty("enterpass"))).sendKeys(password);
	driver.findElement(By.xpath(p.getProperty("entercpass"))).sendKeys(cpassword);
	driver.findElement(By.xpath(p.getProperty("clicksubmit"))).click();
	if(password.equals(cpassword))
	{
		//get message 
String message=driver.findElement(By.xpath("//table//table//table//table//p[2]//font[1]")).getText();
	//write into results cell
xl.setCellData("Register", i, 13, message, outputpath);
xl.setCellData("Register", i, 14, "Pass", outputpath);
Reporter.log(message,true);
test.log(LogStatus.PASS, message);
	}
	else
	{
xl.setCellData("Register", i, 13, "Password and cpassword not matching", outputpath);
xl.setCellData("Register", i, 14, "Fail", outputpath);
test.log(LogStatus.FAIL, "Password and cpassword not matching");
Reporter.log("Password and cpassword not matching",true);
	}
	report.endTest(test);
	report.flush();
	}
}
@AfterTest
public void tearDown()
{
	//driver.close();
}
}













