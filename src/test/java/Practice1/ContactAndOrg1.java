package Practice1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
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

public class ContactAndOrg1 {
	public static void main(String[] args) throws IOException, InterruptedException {
		
		Random ran=new Random();
		int ranDomNum = ran.nextInt(1000);
		FileInputStream fis=new FileInputStream("./data/ComData.properties");
		Properties Pobj=new Properties();
		Pobj.load(fis);
		String URL = Pobj.getProperty("url");
		String USERNAME = Pobj.getProperty("username");
		String PASSWORD = Pobj.getProperty("password");
		String BROWSER = Pobj.getProperty("browser");
		String NAME = Pobj.getProperty("name")+ ranDomNum;
		
		FileInputStream fis_e=new FileInputStream("./data/testScriptData.xlsx");
		 Workbook wb = WorkbookFactory.create(fis_e);
		 Sheet sh = wb.getSheet("OrgCont");
		 Row row=sh.getRow(1);
		 String orgName = row.getCell(2).getStringCellValue() + ranDomNum ;
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
		 driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		 driver.get(URL);
		 driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		 Thread.sleep(5000);
		 
		 driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		 driver.findElement(By.id("submitButton")).click();
		 //Navigate to organization Button
		 driver.findElement(By.linkText("Organizations")).click();
		 //click on create organization button
		 driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
		 //enter details and create new organization
		 driver.findElement(By.name("accountname")).sendKeys(orgName);
		 driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		 Thread.sleep(2000);
		 //For contact click
		 driver.findElement(By.linkText("Contacts")).click();
		 driver.findElement(By.xpath("//img[@alt='Create Contact...']")).click();
		 driver.findElement(By.name("lastname")).sendKeys(NAME);
		 driver.findElement(By.linkText("//src=themes/softed/images/select.gif")).click();
	}

}

