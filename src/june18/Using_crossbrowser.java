package june18;

import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.BeforeTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;

public class Using_crossbrowser {
	WebDriver driver;
	String url="http://orangehrm.qedgetech.com/";
	@Parameters({"browser"})
	  @BeforeMethod
	  public void setUp(String brw) 
	  {
		if(brw.equalsIgnoreCase("chrome"))
		{
			System.out.println("Executing on Chrome browser");
			System.setProperty("webdriver.chrome.driver", "C:\\Selenium11oclock\\Automation_FrameWorks\\Drivers\\chromedriver.exe");
			  driver=new ChromeDriver();
			  driver.get(url);
			  driver.manage().window().maximize();
		}
		else if(brw.equalsIgnoreCase("firefox"))
		{
			System.out.println("Executing on Firefox Browser");
			System.setProperty("webdriver.gecko.driver", "C:\\Selenium11oclock\\Automation_FrameWorks\\Drivers\\geckodriver.exe");
			  driver=new FirefoxDriver();
			  driver.get(url);
		}
		else
		{
			System.out.println("Browser value not matching");
		}
	  }
  @Test(dataProvider = "supplydata")
  public void verifylogin(String username,String password)throws Throwable
  {
	  driver.findElement(By.name("txtUsername")).sendKeys(username);
	  driver.findElement(By.name("txtPassword")).sendKeys(password);
	  driver.findElement(By.name("Submit")).click();
	  Thread.sleep(4000);
	  if(driver.getCurrentUrl().contains("dash"))
	  {
		  Reporter.log("Login Success",true);
	  }
	  else
	  {
		  Reporter.log("Login Fail",true);
	  }
  }

  @DataProvider
  public Object[][] supplydata() {
    Object login[][]=new Object[5][2];//five rows and two columns
    login[0][0]="Admin";
    login[0][1]="Qedge123!@#";
    login[1][0]="test";
    login[1][1]="Qedge123!@#";
    login[2][0]="Admin";
    login[2][1]="Qedge123!@#";
    login[3][0]="Admin";
    login[3][1]="test";
    login[4][0]="akhilesh";
    login[4][1]="test";
    return login;
  }
  

  @AfterMethod
  public void tearDown()
  {
	  driver.close();
  }

}
