package CommonFunLibrary;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

public class LogoutPage {
WebDriver driver;
public LogoutPage(WebDriver driver)
{
	this.driver=driver;
}
@FindBy(xpath="//td//td//td//td[3]//a[1]//img[1]")
WebElement clicklogout;
@FindBy(name="login")
WebElement clicklogin;
public boolean verifyLogout()throws Throwable
{
	this.clicklogout.click();
	Thread.sleep(5000);
	if(clicklogin.isDisplayed())
	{
		Reporter.log("Logout Success::",true);
		return true;
	}
	else
	{
		Reporter.log("Logout Fail::",true);
		return false;
	}
}
}
