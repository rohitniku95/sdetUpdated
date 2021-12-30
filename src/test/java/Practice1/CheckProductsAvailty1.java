package Practice1;

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

public class CheckProductsAvailty1 {
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
		String qty = Pobj.getProperty("Qty/Unit");	
		
		
		FileInputStream fis_e=new FileInputStream("./data/testScriptData.xlsx");
		 Workbook wb = WorkbookFactory.create(fis_e);
		 Sheet sh = wb.getSheet("Product");
		 Row row=sh.getRow(1);
		 String prName = row.getCell(2).getStringCellValue() + ranDomNum ;
		 
		 
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
		 driver.findElement(By.linkText("Products")).click();
		 driver.findElement(By.xpath("//img[@alt='Create Product...']")).click();
		 driver.findElement(By.name("productname")).sendKeys(prName);
		 driver.findElement(By.name("qty_per_unit")).sendKeys(qty);
		 driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		 
		 String act_msg = driver.findElement(By.className("lvtHeaderText")).getText();
		 String act_qty = driver.findElement(By.id("dtlview_Qty/Unit")).getText();
		 if(act_msg.contains(prName) && act_qty.contains(qty) ) {
				System.out.println("product is successfully created with name and unit/qty");
			}else {
				System.out.println("product is not created");
			}
		 
			Actions a=new Actions(driver);
			a.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).perform();
			driver.findElement(By.linkText("Sign Out")).click();
			driver.quit();
		 
	}

}
