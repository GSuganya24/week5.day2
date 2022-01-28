package week5.day2;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class CreateLead extends BaseClass_Lead_Parameter{
	@BeforeClass
	public void setUp() {
		filename = "CreateLead";
	}
	@Test(dataProvider = "fetchData")
public void createLead(String cName, String fName, String lName) {
		    driver.findElement(By.linkText("Create Lead")).click();
			//Fill the details of all the fields in Leadtab
			driver.findElement(By.id("createLeadForm_companyName")).sendKeys(cName);
			driver.findElement(By.id("createLeadForm_firstName")).sendKeys(fName);
			driver.findElement(By.id("createLeadForm_lastName")).sendKeys(lName);
		    driver.findElement(By.className("smallSubmit")).click();
		    //Printing the title of the page after clicking submit
		    String title = driver.getTitle();
		    System.out.println(title);
	}		    
//		    @DataProvider(name = "fetchData", parallel = true)
//		    public String[][] sendData() throws IOException{ //This one we are moving to TestParameter class. since its common for Edit lead and create lead
////		    	String[][] data = new String[2][3];
////		    	data[0][0] = "TestLeaf";
////		    	data[0][1] = "Suganya";
////		    	data[0][2] = "G";
////		    	data[1][0] = "IBM";
////		    	data[1][1] = "Rishikesh";
////		    	data[1][2] = "V";
////		    	return data;
//		    	return ReadXcel.readXcel("CreateLead");
//		    }
//		    
		    
		    

			
			
			

}
