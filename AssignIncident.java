package week5.day2;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class AssignIncident extends BaseClass_Incident_Parameter {
//	public static void main(String[] args) throws InterruptedException {
	@BeforeClass
	public void wbName1() {
		filename = "AssignIncident";
	}
	@Test(dataProvider = "sendData")
public void assignIncident(String name, String group, String notes) throws InterruptedException {
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
		//Taking the incident number
		String inciNum1 = driver.findElement(By.id("incident.number")).getText();
		driver.findElement(By.id("lookup.incident.assignment_group")).click();
		//Opens new window
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> window = new ArrayList<String>(windowHandles);
		driver.switchTo().window(window.get(1));
		WebElement search = driver.findElement(By.xpath("//div[@class='input-group']/input"));
		search.sendKeys(group);
		search.sendKeys(Keys.ENTER);
		Thread.sleep(1000);
		driver.findElement(By.xpath("//table[@id='sys_user_group_table']/tbody/tr/td[3]/a")).click();
		Thread.sleep(1000);
		driver.switchTo().window(window.get(0));
		driver.switchTo().frame("gsft_main");
		driver.findElement(By.xpath("(//textarea[contains(@class,'sn-string-textarea form-control')])[3]")).sendKeys(notes);
		driver.findElement(By.id("sysverb_update")).click();
		Thread.sleep(2000);
		WebElement search2 = driver.findElement(By.xpath("//input[@class='form-control']"));
		search2.sendKeys(inciNum1);
		search2.sendKeys(Keys.ENTER);
		Thread.sleep(1000);
		String assignmentGrp = driver.findElement(By.xpath("//table[@id='incident_table']/tbody//td[10]/a")).getText();
			if(assignmentGrp.equals("Software")) {
				System.out.println("The incident is assigned to "+assignmentGrp+" Group");
			}else {
				System.out.println("The incident is assigned to "+assignmentGrp+" Group. But not the Software group");
			}
		}
		


}
