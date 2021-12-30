package practice;

import java.awt.Desktop.Action;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;

public class CreateOrgTest {

	public static void main(String[] args) throws IOException, InterruptedException {
		FileInputStream fis=new FileInputStream("./data/ComData.properties");
		Properties Pobj=new Properties();
		Pobj.load(fis);
		String URL = Pobj.getProperty("url");
		String USERNAME = Pobj.getProperty("username");
		String BROWSER = Pobj.getProperty("browser");
		String PASSWORD = Pobj.getProperty("password");
		System.out.println(PASSWORD);
		
		//Get random number
		Random ran=new Random();
		int ranDomNum = ran.nextInt(1000);
		
		//Read test data from excel file
		FileInputStream fis_e=new FileInputStream("./data/TestData.xlsx");
		 Workbook wb = WorkbookFactory.create(fis_e);
		 Sheet sh = wb.getSheet("Sheet1");
		 Row row=sh.getRow(1);	
		 String orgName = row.getCell(2).getStringCellValue() + ranDomNum;
		 
		 WebDriver driver;
		 if(BROWSER.equals("firefox")) {
			 driver=new FirefoxDriver();
		 }
		 else if(BROWSER.equals("chrome")) {
			 driver=new ChromeDriver();
		 }
		 else if(BROWSER.equals("ie")){
			 driver=new InternetExplorerDriver();
		 }else {
			 driver=new ChromeDriver();
		 }
		 //Step 1:Login
		 driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		 driver.get(URL);
		 driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		 Thread.sleep(5000);
		 
		 driver.findElement(By.name("user_password")).sendKeys("admin");
		 driver.findElement(By.id("submitButton")).click();
		 //Navigate to organization Button
		 driver.findElement(By.linkText("Organizations")).click();
		 //click on create organization button
		 driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
		 //enter details and create new organization
		 driver.findElement(By.name("accountname")).sendKeys(orgName);
		 driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		 //verify organization name
		 String text = driver.findElement(By.className("dvHeaderText")).getText();
		 if(text.contains(orgName)) {
			 System.out.println("org is sucussfully created pass");
		 }else {
			 System.out.println("org is not crated fail");
		 }
		 //logout
		 Actions act=new Actions(driver);
		 act.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).perform();
		 driver.findElement(By.linkText("Sign Out")).click();
		 driver.close();
	}

}
