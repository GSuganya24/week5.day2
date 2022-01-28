package week5.day2;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;

public class BaseClass_Lead_Parameter {
	public ChromeDriver driver;
	public String filename;
 @Parameters({"url" , "userName" , "password"})
  @BeforeMethod
  public void beforeMethod(String url, String uName, String pwd) {
	  WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.findElement(By.id("username")).sendKeys(uName);
		driver.findElement(By.id("password")).sendKeys(pwd);
		driver.findElement(By.className("decorativeSubmit")).click();
		driver.findElement(By.linkText("CRM/SFA")).click();
		driver.findElement(By.linkText("Leads")).click();
  }
  
  
  @AfterMethod
  public void afterMethod() {
	  driver.close();
  }
  @DataProvider(name = "fetchData")
  public String[][] sendData() throws IOException {
	  return ReadXcel.readXcel(filename); 
  }

}
