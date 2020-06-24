package CommonFunLibrary;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;
public class LoginPage {
WebDriver driver;
public LoginPage(WebDriver driver)
{
this.driver=driver;
}
//store locators for login
//@FindBy=locator="value"
@FindBy(name="txtuId")
WebElement enterusername;
@FindBy(name="txtPword")
WebElement enterpassword;
@FindBy(name="login")
WebElement clickLogin;
//method for login
public boolean verifyLogin(String username,String password)throws Throwable
{
this.enterusername.sendKeys(username);
this.enterpassword.sendKeys(password);
this.clickLogin.click();
Thread.sleep(5000);
String expected="adminflow";
String actual=driver.getCurrentUrl();
if(actual.toLowerCase().contains(expected.toLowerCase()))
{
Reporter.log("Login Success::"+expected+"   "+actual,true);
return true;
}
else
{
Reporter.log("Login Fail::"+expected+"   "+actual,true);
return false;
}
}
}
