package practice;

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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.crm.autodesk.genericUtility.ExcelUtility;
import com.crm.autodesk.genericUtility.FileUTiltiy;
import com.crm.autodesk.genericUtility.JavaUtility;
import com.crm.autodesk.genericUtility.WebdriverUtility;

public class ProductpageAndDuplicate2 {
	public static void main(String[] args) throws Throwable {

		FileUTiltiy fLib = new FileUTiltiy();
		WebdriverUtility wLib = new WebdriverUtility();
		ExcelUtility eLib = new ExcelUtility();
		JavaUtility jLib = new JavaUtility();

		Random ran = new Random();
		int ranDomNum = ran.nextInt(1000);
		
		String URL = fLib.getPropertyKeyValue("url");
		String USERNAME = fLib.getPropertyKeyValue("username");
		String PASSWORD = fLib.getPropertyKeyValue("password");
		String BROWSER = fLib.getPropertyKeyValue("browser");
		
		String prName = eLib.getDataFromExcel("Product", 1, 2);
		/*
		FileInputStream fis = new FileInputStream("./data/ComData.properties");
		Properties Pobj = new Properties();
		Pobj.load(fis);
		String URL = Pobj.getProperty("url");
		String USERNAME = Pobj.getProperty("username");
		String PASSWORD = Pobj.getProperty("password");
		String BROWSER = Pobj.getProperty("browser");
		String QTY = Pobj.getProperty("Qty/Unit");

		FileInputStream fis_e = new FileInputStream("./data/testScriptData.xlsx");
		Workbook wb = WorkbookFactory.create(fis_e);
		Sheet sh = wb.getSheet("Product");
		Row row = sh.getRow(1);
		String prName = row.getCell(2).getStringCellValue() + ranDomNum;

        */
		
		WebDriver driver;
		if (BROWSER.equals("firefox")) {
			driver = new FirefoxDriver();
		} else if (BROWSER.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (BROWSER.equals("ie")) {
			driver = new InternetExplorerDriver();
		} else {
			driver = new ChromeDriver();
		}
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get(URL);
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		driver.findElement(By.linkText("Products")).click();
		driver.findElement(By.xpath("//img[@alt='Create Product...']")).click();
		driver.findElement(By.name("productname")).sendKeys(prName);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		driver.findElement(By.xpath("//input[@title='Duplicate [Alt+U]']")).click();
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		driver.findElement(By.linkText("Products")).click();
		WebElement p1 = driver.findElement(By.xpath("(//a[(text()='" + prName + "')])[3]"));
		String pro = p1.getText();
		System.out.println(pro);
		if (pro.equals(prName)) {
			System.out.println("Duplicated");
		} else {
			System.out.println("Not Duplicated");
		}
		wLib.mouseOverOnElemnet(driver, driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']")));
		driver.findElement(By.linkText("Sign Out")).click();
		driver.quit();
	}

}
