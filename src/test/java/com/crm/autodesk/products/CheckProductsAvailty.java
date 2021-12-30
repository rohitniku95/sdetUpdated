package com.crm.autodesk.products;

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
import org.openqa.selenium.interactions.Actions;

import com.autodesk.ObjectRepository.HomePage;
import com.autodesk.ObjectRepository.LoginPage;
import com.autodesk.ObjectRepository.ProductAvaility;
import com.autodesk.ObjectRepository.ProductInfo;
import com.crm.autodesk.contacttest.CreateContact;
import com.crm.autodesk.genericUtility.ExcelUtility;
import com.crm.autodesk.genericUtility.FileUTiltiy;
import com.crm.autodesk.genericUtility.JavaUtility;
import com.crm.autodesk.genericUtility.WebdriverUtility;

public class CheckProductsAvailty {
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
	    String qty = eLib.getDataFromExcel("Product", 1, 5);

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
		wLib.WaitForPageToLoad(driver);
		driver.get(URL);
		
		LoginPage lp=new LoginPage(driver);
		lp.loginToApp(USERNAME, PASSWORD);
		
		HomePage hp=new HomePage(driver);
		hp.clicOnProductLink();
		
		ProductAvaility pa=new ProductAvaility(driver);
		
		pa.createProductLookupImg();
		
		pa.createProduct(prName);
		
		pa.createProduct(prName);
		pa.createquantityPerunit(qty);
		pa.clickOnSaveButton();
		
		

		/*driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		driver.findElement(By.linkText("Products")).click();
		driver.findElement(By.xpath("//img[@alt='Create Product...']")).click();
		driver.findElement(By.name("productname")).sendKeys(prName);
		driver.findElement(By.name("qty_per_unit")).sendKeys(QTY);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();*/
        
		/*String act_msg = driver.findElement(By.className("lvtHeaderText")).getText();
		String act_qty = driver.findElement(By.id("dtlview_Qty/Unit")).getText();*/
		ProductInfo pi=new ProductInfo(driver);
		String act_msg = pi.getProductInfo();
		String act_qty = pi.getQtyunitInfo();
		
		if (act_msg.contains(prName) && act_qty.contains(qty)) {
			System.out.println("product is successfully created with name and unit/qty");
		} else {
			System.out.println("product is not created");
		}
		wLib.mouseOverOnElemnet(driver, driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']")));
		/*
		 * Actions a=new Actions(driver); a.moveToElement(driver.findElement(By.xpath(
		 * "//img[@src='themes/softed/images/user.PNG']"))).perform();
		 */
		//driver.findElement(By.linkText("Sign Out")).click();
		hp.logout(driver);
		driver.quit();
	}
}
