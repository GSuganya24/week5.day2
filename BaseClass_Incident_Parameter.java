package week5.day2;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass_Incident_Parameter {
	
	public ChromeDriver driver;
	public static String inciNum;
	public String filename;
	@Parameters({"url" ,"username", "password"})
	@BeforeMethod()
public void beforeMethod(String url, String uName, String pwd) throws InterruptedException {
	WebDriverManager.chromedriver().setup();
    driver = new ChromeDriver();
	driver.get(url);
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	// Enter the frame
	driver.switchTo().frame("gsft_main");
	driver.findElement(By.id("user_name")).sendKeys(uName);
	driver.findElement(By.id("user_password")).sendKeys(pwd);
	driver.findElement(By.id("sysverb_login")).click();
	Thread.sleep(3000);

	WebElement search = driver.findElement(By.xpath("//input[@id='filter']"));
	search.sendKeys("incident");
	Thread.sleep(3000);
	search.sendKeys(Keys.ENTER);
	Thread.sleep(5000);
	// Enter the frame
			driver.switchTo().frame("gsft_main");
			driver.findElement(By.xpath("//b[text()='All']")).click();
			Thread.sleep(1000);
	}
	
	@AfterMethod()
		public void afterMethod() {
		driver.close();
		}
	@DataProvider(name = "sendData")
	public String[][] data() throws IOException{
		return ReadXcel_Incident.readIncident(filename);
	}
		

}
