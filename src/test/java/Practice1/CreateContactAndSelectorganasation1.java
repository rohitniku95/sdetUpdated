package Practice1;


	import java.io.FileInputStream;
	import java.io.IOException;
	import java.util.Properties;
	import java.util.Random;
	import java.util.Set;
	import java.util.concurrent.TimeUnit;

	import org.apache.poi.ss.usermodel.Workbook;
	import org.apache.poi.ss.usermodel.WorkbookFactory;
	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.firefox.FirefoxDriver;
	import org.openqa.selenium.ie.InternetExplorerDriver;
	import org.openqa.selenium.interactions.Actions;

	public class CreateContactAndSelectorganasation1 {
	    public static void main(String[] args) throws IOException, InterruptedException 
	    {
	   	 WebDriver driver=null;
	   //read data from property file
			System.setProperty("webdriver.chrome.driver","./driver/chromedriver.exe");
			FileInputStream fis=new FileInputStream("./data/ComData.properties");
			Properties prop=new Properties();
			prop.load(fis);
		         String browser=prop.getProperty("browser");
		         String url=prop.getProperty("url");
		         String username=prop.getProperty("username");
		         String passcode=prop.getProperty("password");
		  		
			if(browser.equals("chrome"))
			{
				driver=new ChromeDriver();
			}
			
			else if(browser.equals("firefox"))		
			{
				driver=new FirefoxDriver();
			}
			else if(browser.equals("internetexplorer"))
			{
				driver =new InternetExplorerDriver();
			}
			else
			{
				driver=new ChromeDriver();
	 		}
	 		//login
	 		driver.manage().window().maximize();
	 		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
	 		driver.get(url);
	 		
	 		//create organization
	 		//read data from excel
	 		Random rand=new Random();
	 		int randNum=rand.nextInt(1000);
	 		 
			Random rand2=new Random();
			int randNum2=rand.nextInt(1000);
			 
			 
			FileInputStream fil3=new FileInputStream("./data/testScriptData.xlsx");
			Workbook wb2 = WorkbookFactory.create(fil3);
			 

			 String lastname=wb2.getSheet("Sheet1").getRow(1).getCell(1).getStringCellValue()+randNum2;
			
	 		FileInputStream fil2=new FileInputStream("./data/testScriptData.xlsx");
	 		 Workbook wb = WorkbookFactory.create(fil2);
	 		 String orgName=wb.getSheet("Sheet1").getRow(1).getCell(0).getStringCellValue()+randNum;
	 		driver.findElement(By.xpath("//input[@name='user_name']")).sendKeys(username);
	 		driver.findElement(By.xpath("//input[@name='user_password']")).sendKeys(passcode);
	 		driver.findElement(By.id("submitButton")).click();
	         driver.findElement(By.xpath("//a[text()='Organizations']")).click();
	         driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
	         
	         driver.findElement(By.xpath("//input[@name='accountname']")).sendKeys(orgName);
	         driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
	        
	 		Thread.sleep(10000);
	 		driver.findElement(By.xpath("//a[text()='Contacts']")).click();
	 		driver.findElement(By.xpath("//img[@alt='Create Contact...']")).click();
	        driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys(lastname); 
	       
	       driver.findElement(By.xpath("(//img[@alt='Select'])[1]")).click();
	       
	       String parentid=driver.getWindowHandle();
	       
	       Set<String> child=driver.getWindowHandles();
	                        child.remove(parentid);
	    	        for(String b:child)
	    	        {
	    	        	driver.switchTo().window(b);
	    	        }
	    	 driver.findElement(By.linkText(orgName)).click();         
	    	 driver.switchTo().window(parentid);
	    	 driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
	    	//logout
			   Actions act=new Actions(driver);
			   act.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).perform();	
	         driver.findElement(By.xpath("//a[text()='Sign Out']")).click();		 
		driver.quit(); 
		  }
	}


