package Practice1;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateContactWithOrgTest1 {
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
		 Sheet sh = wb.getSheet("orgname");
		 Row row=sh.getRow(1);
		 String orgname = row.getCell(2).getStringCellValue() + ranDomNum ;
		 
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
		 driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		 driver.findElement(By.id("submitButton")).click();
		 //Navigate to organization Button
		 driver.findElement(By.linkText("Organizations")).click();
		 //click on create organization button
		 driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
		 //enter details and create new organization
		 driver.findElement(By.name("accountname")).sendKeys(orgname);
		 driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		 
		 WebDriverWait wait=new WebDriverWait(driver,10);
		 wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.className("dvHeaderText"))));
		 
		 driver.findElement(By.linkText("Contacts")).click();
		 driver.findElement(By.xpath("//img[@alt='Create Contact...']")).click();
		 driver.findElement(By.name("lastname")).sendKeys(NAME);
		 driver.findElement(By.xpath("//input[@name='account_name']/following-sibling::img")).click();
		 Set<String> set = driver.getWindowHandles();
		 Iterator<String> it = set.iterator();
		 
		 while(it.hasNext()) {
			 String currentID = it.next();
			 driver.switchTo().window(currentID);
			 String cPageTitle = driver.getTitle();
			 if(cPageTitle.contains("Accounts")) {
				 break;
			 }
		 }
		 driver.findElement(By.name("search_text")).sendKeys(orgname);
		 driver.findElement(By.name("search")).click();
		 driver.findElement(By.xpath("//a[text()='"+orgname+"']")).click();
		Set<String> set1 = driver.getWindowHandles();
		Iterator<String> it1 = set1.iterator();
		
		while(it1.hasNext()) {
			String currentID = it1.next();
			driver.switchTo().window(currentID);
			String cpageTitle = driver.getTitle();
			if(cpageTitle.contains("contacts")) {
				break;
			}
			
		}
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		Actions a=new Actions(driver);
		a.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).perform();
		driver.findElement(By.linkText("Sign Out")).click();
		driver.quit();

  }

}
