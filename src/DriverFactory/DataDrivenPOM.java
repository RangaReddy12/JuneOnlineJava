package DriverFactory;

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import CommonFunLibrary.BranchCreationPage;
import CommonFunLibrary.BranchesPage;
import CommonFunLibrary.LoginPage;
import Utilities.ExcelFileUtil;

public class DataDrivenPOM {
WebDriver driver;
Properties p;
FileInputStream fi;
String inputpath="C:\\Selenium11oclock\\Automation_FrameWorks\\TestInput\\branch.xlsx";
String outputpath="C:\\Selenium11oclock\\Automation_FrameWorks\\TestOutput\\datapom.xlsx";
@BeforeTest
public void setUp()throws Throwable
{
	p=new Properties();
	fi=new FileInputStream("C:\\Selenium11oclock\\Automation_FrameWorks\\PropertyFile\\Environment.properties");
	p.load(fi);
if(p.getProperty("Browser").equalsIgnoreCase("chrome"))
{
	System.setProperty("webdriver.chrome.driver", "C:\\Selenium11oclock\\Automation_FrameWorks\\Drivers\\chromedriver.exe");
	driver=new ChromeDriver();
	driver.get(p.getProperty("Url"));
	driver.manage().window().maximize();
	LoginPage login=PageFactory.initElements(driver, LoginPage.class);
	login.verifyLogin("Admin", "Admin");
}
else if(p.getProperty("Browser").equalsIgnoreCase("firefox"))
{
	System.setProperty("webdriver.gecko.driver", "C:\\Selenium11oclock\\Automation_FrameWorks\\Drivers\\geckodriver.exe");
	driver=new FirefoxDriver();
	driver.get(p.getProperty("Url"));
	LoginPage login=PageFactory.initElements(driver, LoginPage.class);
	login.verifyLogin("Admin", "Admin");
}
}
@Test
public void verifyBranchCreation()throws Throwable
{
	String result="";
	ExcelFileUtil xl=new ExcelFileUtil(inputpath);
	//call branch creation page class
	BranchCreationPage branch=PageFactory.initElements(driver, BranchCreationPage.class);
	BranchesPage navigate=PageFactory.initElements(driver, BranchesPage.class);
	//count no of rows 
	int rc=xl.rowCount("NewBranch");
	Reporter.log("No of rows are::"+rc,true);
	for(int i=1;i<=rc;i++)
	{
	String branchname=xl.getCellData("NewBranch", i, 0);
	String address1=xl.getCellData("NewBranch", i, 1);
	String address2=xl.getCellData("NewBranch", i, 2);
	String address3=xl.getCellData("NewBranch", i, 3);
	String area=xl.getCellData("NewBranch", i, 4);
	String zcode=xl.getCellData("NewBranch", i, 5);
	String country=xl.getCellData("NewBranch", i, 6);
	String state=xl.getCellData("NewBranch", i, 7);
	String city=xl.getCellData("NewBranch", i, 8);
	navigate.verifybrances();
boolean res=branch.verifyBcreation(branchname, address1, address2, address3, area, zcode, country, state, city);
if(res)
{
	result="pass";
	xl.setCellData("NewBranch", i, 9, result, outputpath);
}
else
{
	result="Fail";
	xl.setCellData("NewBranch", i, 9, result, outputpath);
}
}
}
@AfterTest
public void tearDown()
{
	driver.close();
}
}









