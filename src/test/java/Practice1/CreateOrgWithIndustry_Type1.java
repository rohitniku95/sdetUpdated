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
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class CreateOrgWithIndustry_Type1 {
	static {
		System.setProperty("webdriver.chrome.driver","./driver/chromedriver.exe");
	}
	public static void main(String[] args) throws IOException {
		//get the java representation object of the physical file
		FileInputStream fis=new FileInputStream("./data/ComData.properties");
	
		//create an object of property class to load all key
		Properties pobj=new Properties();
		pobj.load(fis);
		
		//Read the value using get property key
		String URL = pobj.getProperty("url");
		String USERNAME = pobj.getProperty("username");
		String PASSWORD = pobj.getProperty("password");
		String BROWSER = pobj.getProperty("browser");
		
		//get random number
		Random ran=new Random();
		int randomNum = ran.nextInt();
		//Read the data from excel file
		FileInputStream fis_e=new FileInputStream("./data/testScriptData.xlsx");
		Workbook wb=WorkbookFactory.create(fis_e);
		Sheet sh = wb.getSheet("orgname");
		Row row = sh.getRow(1);
		String orgname = row.getCell(2).getStringCellValue()+ randomNum;
		Sheet sh1 = wb.getSheet("contactname");
		String indusName = sh1.getRow(1).getCell(3).getStringCellValue();
		String typeName = sh1.getRow(1).getCell(4).getStringCellValue();
		
		WebDriver driver=null;
		if(BROWSER.equals("chrome")) {
			driver=new ChromeDriver();
		}else if(BROWSER.equals("firefox")) {
			driver=new FirefoxDriver();
		}
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		driver.get(URL);
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		driver.findElement(By.linkText("Organizations")).click();
		driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
		driver.findElement(By.name("accountname")).sendKeys(orgname);
		
		Select s=new Select(driver.findElement(By.name("industry")));
		s.selectByVisibleText(indusName);
		
		Select s1=new Select(driver.findElement(By.name("accounttype")));
		s1.selectByVisibleText(typeName);
		
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		String act_msg = driver.findElement(By.className("dvHeaderText")).getText();
		if(act_msg.contains(orgname)) {
			System.out.println("org is successfully created with industry and type");
		}else {
			System.out.println("org is not created");
		}
		Actions a=new Actions(driver);
		a.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).perform();
		driver.findElement(By.linkText("Sign Out")).click();
		driver.quit();
		
		
	}

}
