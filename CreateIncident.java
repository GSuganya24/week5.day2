package week5.day2;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class CreateIncident extends BaseClass_Incident_Parameter {
	@BeforeClass
	public void wbName() {
		filename = "CreateIncident";
	}
//	@Test(enabled = true,priority = 1, alwaysRun = true, invocationCount = 3, threadPoolSize = 2)
	@Test(dataProvider = "sendData")
	public void createIncident(String name, String notes) throws InterruptedException, IOException {
		driver.findElement(By.id("sysverb_new")).click();
		driver.findElement(By.xpath("//span[@class='icon icon-search']")).click();
		// Switch to the newly opened window
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> window1 = new ArrayList<String>(windowHandles);
		driver.switchTo().window(window1.get(1));
		WebElement caller = driver.findElement(By.xpath("//input[@class='form-control']"));
		caller.sendKeys(name);
		caller.sendKeys(Keys.ENTER);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//td/a")).click();
		Thread.sleep(1000);
		// Moving the control to parent window
		driver.switchTo().window(window1.get(0));
		driver.switchTo().frame("gsft_main");
		driver.findElement(By.xpath("//a[@id='lookup.incident.short_description']")).click();
		//Switching to new window
		Set<String> windowHandles1 = driver.getWindowHandles();
		List<String> window2 = new ArrayList<String>(windowHandles1);
		driver.switchTo().window(window2.get(1));
		driver.findElement(By.xpath("//td/a")).click();
		Thread.sleep(1000);
		driver.switchTo().window(window2.get(0));
		driver.switchTo().frame("gsft_main");
		driver.findElement(By.id("incident.comments")).sendKeys(notes);
		// Getting the attribute of incident number
		WebElement num = driver.findElement(By.xpath("//input[@id='incident.number']"));
		String incidentNum = num.getAttribute("value");
		System.out.println("Incident Number : " + incidentNum);
		driver.findElement(By.id("sysverb_insert_bottom")).click();
		Thread.sleep(2000);
		WebElement search1 = driver.findElement(By.xpath("//input[@class='form-control']"));
		search1.sendKeys(incidentNum);
		search1.sendKeys(Keys.ENTER);
		Thread.sleep(500);
		inciNum = driver.findElement(By.xpath("//td[@class='vt']/a")).getText();
		if(inciNum.equals(incidentNum)) {
			System.out.println("The incident creation is Successful");
			File source = driver.getScreenshotAs(OutputType.FILE);
			File destination = new File("./images/Incident.png");
			FileUtils.copyFile(source, destination);
		}else {
			System.out.println("The incident creation is unsuccessful");
		}
		
	}
//	@DataProvider(name = "sendData")
//	public String[][] data() throws IOException{
////		String[][] data = new String[3][2];
////		data[0][0] = "Suganya";
////		data[0][1] = "Created Incident for Suganya";
////		data[1][0] = "Rishikesh";
////		data[1][1] = "Created Incident for Rishikesh";
////		data[2][0] = "Dharshan";
////		data[2][1] = "Created Incident for Dharshan";
////		String[][] data = ReadXcel_Incident.readIncident(); // can directly return the value since its static instead saving it in diff variable
////		return data;
//		return ReadXcel_Incident.readIncident();
//	}
}
