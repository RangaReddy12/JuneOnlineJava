package june19;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class UsingWaitStatements {

	public static void main(String[] args) {
		WebDriver driver=new ChromeDriver();
		driver.get("https://www.icicidirect.com/");
		driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);	
		driver.findElement(By.xpath("//li[@id='liMarket']//a")).click();
		driver.findElement(By.xpath("//li[@id='LiMarketStats']//a")).click();
		
		driver.findElement(By.xpath("//a[@id='hrfDSP']")).click();

	}

}
