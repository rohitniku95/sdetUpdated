package com.crm.autodesk.genericUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.autodesk.ObjectRepository.HomePage;
import com.autodesk.ObjectRepository.LoginPage;

public class BaseClass {

   public JavaUtility jLib = new JavaUtility();
   public DataBaseUtility dLib = new DataBaseUtility();
   public FileUTiltiy fLib = new FileUTiltiy();
   public WebdriverUtility wLib = new WebdriverUtility();
   public ExcelUtility eLib = new ExcelUtility();
   public WebDriver driver;
   public static WebDriver sdriver;
   
   @BeforeSuite(groups = {"smokeSuite","RegressionSuite"})
   public void dbConnection() 
   {
	 dLib.connectToDB();
	 System.out.println("====Database connection successful====");
   }
  // @Parameters("browser") //for cross browser we pass this
   @BeforeClass(groups = {"smokeSuite","RegressionSuite"})
   public void launchBrowser() throws Throwable //for cross browser we pass String BROWSER
   {
	   //Read the data
	   String BROWSER = fLib.getPropertyKeyValue("browser"); //for cross browser we disable this line
	   String URL = fLib.getPropertyKeyValue("url");
	   if(BROWSER.equalsIgnoreCase("chrome"))
	   {
		   driver = new ChromeDriver();
	   }
	   else if(BROWSER.equalsIgnoreCase("firefox"))
	   {
		   driver = new FirefoxDriver();
	   }
	   else
	   {
		   System.out.println("Invalid browser");
	   }
	   sdriver = driver;
	   wLib.WaitForPageToLoad(driver);
	   wLib.maximiseWindow(driver);
	   driver.get(URL);
	   System.out.println("====Browser Launched");
   }
   @BeforeMethod(groups = {"smokeSuite","RegressionSuite"})
   public void loginToApp() throws Throwable {
	   //Read the data
	   
	  String USERNAME = fLib.getPropertyKeyValue("username");
	  String PASSWORD = fLib.getPropertyKeyValue("password");
	  
	  LoginPage lp= new LoginPage(driver);
	  lp.loginToApp(USERNAME, PASSWORD);
	  System.out.println("=====Login Successful====");
   }
   @AfterMethod(groups = {"smokeSuite","RegressionSuite"})
   public void logoutofApp()
   {
	   HomePage hp = new HomePage(driver);
	   hp.logout(driver);
	   System.out.println("===Logout Successful");
   }
   @AfterClass(groups = {"smokeSuite","RegressionSuite"})
   public void closeBrowser()
   {
	   driver.quit();
	   System.out.println("===Browser Closed");
   }
   @AfterSuite(groups = {"smokeSuite","RegressionSuite"})
   public void closeDbConnection()
   {
	 dLib.closeDB();
	 System.out.println("===Data Base Connection Closed===");
   }
}
