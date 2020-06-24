package june19;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UsingExplicit {

	public static void main(String[] args) {
		WebDriver driver=new ChromeDriver();
		driver.get("http://primusbank.qedgetech.com/");
		driver.manage().window().maximize();
		driver.findElement(By.name("txtuId")).sendKeys("Admin");
		driver.findElement(By.name("txtPword")).sendKeys("Admin");
		driver.findElement(By.name("login")).click();
		WebDriverWait wait=new WebDriverWait(driver, 300);
		//wait until branches link is visible
wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tr//tr//tr//tr//tr[2]//td[1]//a[1]//img[1]")));
driver.findElement(By.xpath("//tr//tr//tr//tr//tr[2]//td[1]//a[1]//img[1]")).click();
//wait until roles is clickable
wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//tr//tr//tr[4]//td[1]//a[1]//img[1]")));
driver.findElement(By.xpath("//tr//tr//tr[4]//td[1]//a[1]//img[1]")).click();
	}

}
