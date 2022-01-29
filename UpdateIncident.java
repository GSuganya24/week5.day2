package week5.day2;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class UpdateIncident extends BaseClass_Incident_Parameter {
	@BeforeClass
	public void wbName2() {
		filename = "UpdateIncident";
	}
	@Test(dataProvider = "sendData")
	public  void updateIncident(String name) throws InterruptedException {
		driver.switchTo().defaultContent();
		driver.findElement(By.xpath("//div[text()='Open - Unassigned']")).click();
		Thread.sleep(1000);
		//Click the first unassigned record
		driver.switchTo().frame("gsft_main");
		WebElement search1 = driver.findElement(By.xpath("//td[@name='caller_id']//input[@class='list_header_search  form-control form-control-search']"));
		search1.sendKeys(name);
		search1.sendKeys(Keys.ENTER);
		Thread.sleep(1000);
		driver.findElement(By.xpath("//table[@id='incident_table']/tbody//td[3]/a")).click();
		Thread.sleep(2000);
		//Taking the incident number
		String inciNum1 = driver.findElement(By.id("incident.number")).getText();
		//Select Urgency
		Select dd1 = new Select(driver.findElement(By.id("incident.urgency")));
		dd1.selectByValue("1");
		//Select State
		Select dd2 = new Select(driver.findElement(By.id("incident.state")));
		dd2.selectByValue("2");
		driver.findElement(By.id("sysverb_update_bottom")).click();
		Thread.sleep(2000);
		WebElement search2 = driver.findElement(By.xpath("//input[@class='form-control']"));
		search2.sendKeys(inciNum1);
		search2.sendKeys(Keys.ENTER);
		Thread.sleep(500);
		driver.findElement(By.xpath("//table[@id='incident_table']/tbody/tr/td[3]/a")).click();
		//Get the values of the fields 'urgency&state' and whether updated
		WebElement urgency = driver.findElement(By.xpath("//select[@id='incident.urgency']//option[@selected='SELECTED']"));
		String urg = urgency.getText();
		System.out.println(urg);
		WebElement state = driver.findElement(By.xpath("//select[@id='incident.state']//option[2][@selected='SELECTED']"));
		String state1 = state.getText();
		System.out.println(state1);
		if(urg.contains("High") && state1.contains("Progress")) {
			System.out.println("The incident is updated");
		}else {
			System.out.println("The incident is not updated");
		}

		
	}
}
