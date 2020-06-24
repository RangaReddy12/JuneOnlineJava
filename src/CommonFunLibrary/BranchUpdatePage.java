package CommonFunLibrary;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

public class BranchUpdatePage {
WebDriver driver;
public BranchUpdatePage(WebDriver driver)
{
	this.driver=driver;
}
@FindBy(xpath="//tr//tr[2]//td[7]//a[1]//img[1]")
WebElement clickedit;
@FindBy(xpath="//input[@id='txtbnameU']")
WebElement enterbname;
@FindBy(xpath="//input[@id='txtadd1u']")
WebElement enteradd1;
@FindBy(xpath="//input[@id='txtareaU']")
WebElement enterarea;
@FindBy(xpath="//input[@id='btnupdate']")
WebElement clickupdate;
public boolean verifyBupdate(String bname,String addresss1,String area)throws Throwable
{
	this.clickedit.click();
	Thread.sleep(5000);
	this.enterbname.clear();
	Thread.sleep(5000);
	this.enterbname.sendKeys(bname);
	Thread.sleep(5000);
	this.enteradd1.clear();
	Thread.sleep(5000);
	this.enteradd1.sendKeys(addresss1);
	Thread.sleep(5000);
	this.enterarea.clear();
	Thread.sleep(5000);
	this.enterarea.sendKeys(area);
	this.clickupdate.click();
	Thread.sleep(5000);
	String alertupdate=driver.switchTo().alert().getText();
	Thread.sleep(5000);
	driver.switchTo().alert().accept();
	Thread.sleep(5000);
	String expected="branch updated";
	if(alertupdate.toLowerCase().contains(expected.toLowerCase()))
	{
		Reporter.log(alertupdate,true);
		return true;
	}
	else
	{
		Reporter.log("Unable to update branch",true);
		return false;
	}
}
}





