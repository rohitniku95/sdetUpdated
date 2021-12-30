package com.crm.autodesk.products;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.crm.autodesk.genericUtility.ExcelUtility;
import com.crm.autodesk.genericUtility.FileUTiltiy;
import com.crm.autodesk.genericUtility.JavaUtility;
import com.crm.autodesk.genericUtility.WebdriverUtility;

public class ProductFilterEdit {
	public static void main(String[] args) throws Throwable {

		FileUTiltiy fLib = new FileUTiltiy();
		WebdriverUtility wLib = new WebdriverUtility();
		ExcelUtility eLib = new ExcelUtility();
		JavaUtility jLib = new JavaUtility();

		String BROWSER = fLib.getPropertyKeyValue("browser");
		String URL = fLib.getPropertyKeyValue("url");
		String USERNAME = fLib.getPropertyKeyValue("username");
		String PASSWORD = fLib.getPropertyKeyValue("password");

		int randomnum = jLib.getRandomNumber();

		String pro = eLib.getDataFromExcel("product", 1, 2) + randomnum;
		String procat = eLib.getDataFromExcel("product", 1, 3);
		String viewname = eLib.getDataFromExcel("product", 1, 4) + randomnum;

		WebDriver driver = null;
		if (BROWSER.equals("firefox")) {
			driver = new FirefoxDriver();
		} else if (BROWSER.equals("chrome")) {
			driver = new ChromeDriver();

		} else if (BROWSER.equals("interexplo")) {
			driver = new InternetExplorerDriver();

		}

		wLib.WaitForPageToLoad(driver);
		driver.get(URL);
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		driver.findElement(By.linkText("Products")).click();
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
		driver.findElement(By.name("productname")).sendKeys(pro);

		wLib.select(driver.findElement(By.name("productcategory")), procat);

		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		String actsucc_msg = driver.findElement(By.className("lvtHeaderText")).getText();
		if (actsucc_msg.contains(pro)) {
			System.out.println("product is successfully created and selected : pass");

		} else {
			System.out.println("product is not created and selected :fail");
		}

		driver.findElement(By.linkText("Products")).click();
		driver.findElement(By.linkText("Create Filter")).click();
		driver.findElement(By.name("viewName")).sendKeys(viewname);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

		wLib.select(driver.findElement(By.name("viewname")), viewname);

		driver.findElement(By.linkText("Edit")).click();

	}

}
