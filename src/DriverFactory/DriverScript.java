package DriverFactory;

import org.junit.runner.Description;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import CommonFunLibrary.BranchCreationPage;
import CommonFunLibrary.BranchUpdatePage;
import CommonFunLibrary.BranchesPage;
import CommonFunLibrary.LoginPage;
import CommonFunLibrary.LogoutPage;
import Utilities.ExcelFileUtil;
import constant.PBConstant;

public class DriverScript extends PBConstant{
String inputpath="C:\\Selenium11oclock\\Automation_FrameWorks\\TestInput\\Controller.xlsx";
String outputpath="C:\\Selenium11oclock\\Automation_FrameWorks\\TestOutput\\Keyword.xlsx";
String TCSheet="TestCases";
String TSSheet="TestSteps";
ExtentReports report;
ExtentTest test;
@Test
public void PrimusBank()throws Throwable
{
	report=new ExtentReports("./ExtentReports/keyword.html");
	LoginPage login=PageFactory.initElements(driver, LoginPage.class);
	BranchesPage barnches=PageFactory.initElements(driver, BranchesPage.class);
	BranchCreationPage branchc=PageFactory.initElements(driver, BranchCreationPage.class);
	BranchUpdatePage upadatebranch=PageFactory.initElements(driver, BranchUpdatePage.class);
	LogoutPage logout=PageFactory.initElements(driver, LogoutPage.class);
	boolean res=false;
	String tcres=null;
	//access excel methods
	ExcelFileUtil xl=new ExcelFileUtil(inputpath);
	//count no row in TCSheet
	int TCcount=xl.rowCount(TCSheet);
	//count no row in TSSheet
	int TScount=xl.rowCount(TSSheet);
Reporter.log("No of rows in TCSheet::"+TCcount+"  "+"Noo f rows in TSSheet::"+TScount,true);
for(int i=1;i<=TCcount;i++)
{
	//start test case
	test=report.startTest("KeyWord Framework");
	//read execute cell
	String execute=xl.getCellData(TCSheet, i, 2);
	if(execute.equalsIgnoreCase("Y"))
	{
		//read tcid cell
		String tcid=xl.getCellData(TCSheet, i, 0);
		for(int j=1;j<=TScount;j++)
		{
			//read description column from TSSheet
			String Description=xl.getCellData(TSSheet, j, 2);
			//read tsid cell
			String tsid=xl.getCellData(TSSheet, j, 0);
			if(tcid.equalsIgnoreCase(tsid))
			{
			//read keyword cell
				String keyword=xl.getCellData(TSSheet, j, 3);
			if(keyword.equalsIgnoreCase("AdminLogin"))	
			{
			res=login.verifyLogin("Admin", "Admin");
			test.log(LogStatus.INFO, Description);
			}
			else if(keyword.equalsIgnoreCase("NewBranchCreation"))
			{
				barnches.verifybrances();
				res=branchc.verifyBcreation("Kadiri", "Anantapur", "madanapalli", "kadiri3", "tanakalu", "56789", "INDIA","Andhra Pradesh","Nellore");
				test.log(LogStatus.INFO, Description);
			}
			else if(keyword.equalsIgnoreCase("UpdateBranch"))
			{
				barnches.verifybrances();
			res=upadatebranch.verifyBupdate("Manadapalli", "KAdiri", "yerracheruvu palli");
			test.log(LogStatus.INFO, Description);
			}
			else if(keyword.equalsIgnoreCase("AdminLogout"))
			{
				res=logout.verifyLogout();
				test.log(LogStatus.INFO, Description);
			}
			String tsres="";
			if(res)
			{
				tsres="Pass";
			xl.setCellData(TSSheet, j, 4, tsres, outputpath);	
			test.log(LogStatus.PASS, Description);
			}
			else
			{
				tsres="Fail";
			xl.setCellData(TSSheet, j, 4, tsres, outputpath);	
			test.log(LogStatus.FAIL, Description);
			}
			if(!tsres.equalsIgnoreCase("Fail"))
			{
				tcres=tsres;
			}
			}
			report.endTest(test);
			report.flush();
		}
		//write into TCsheet
		xl.setCellData(TCSheet, i, 3, tcres, outputpath);
	}
	else
	{
		//write as Not Executed in Results cell
		xl.setCellData(TCSheet, i, 3, "Not executed", outputpath);
	}
}
}
}











