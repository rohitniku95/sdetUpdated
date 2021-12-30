package Practice1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.autodesk.ObjectRepository.CreateOrganizationPage;
import com.autodesk.ObjectRepository.HomePage;
import com.autodesk.ObjectRepository.LoginPage;
import com.autodesk.ObjectRepository.OrganizationInfoPage;
import com.autodesk.ObjectRepository.OrganizationsPage;
import com.crm.autodesk.genericUtility.ExcelUtility;
import com.crm.autodesk.genericUtility.FileUTiltiy;
import com.crm.autodesk.genericUtility.JavaUtility;
import com.crm.autodesk.genericUtility.WebdriverUtility;

public class CreateOrgWithIndAndType {
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

		String org = eLib.getDataFromExcel("orgname", 5, 2) + randomnum;
		String ind = eLib.getDataFromExcel("orgname", 5, 3);
		String type1 = eLib.getDataFromExcel("orgname", 5, 4);

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
		
		LoginPage lp=new LoginPage(driver);
		lp.loginToApp(USERNAME, PASSWORD);
		
		HomePage hp=new HomePage(driver);
		hp.clickOnOrganizationLink();
		
		OrganizationsPage op=new OrganizationsPage(driver);
		op.clickOnCreateOrg();
		
		CreateOrganizationPage cop = new CreateOrganizationPage(driver);
		cop.createOrgWithIndustryAndType(org, ind, type1);
		
		OrganizationInfoPage oip=new OrganizationInfoPage(driver);
		String actsucc_msg = oip.OrgInfo();
		if (actsucc_msg.contains(org)) {
			System.out.println("org is successfully created : pass");

		} else {
			System.out.println("org is not created :fail");
		}
		
		//driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		//driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		//driver.findElement(By.id("submitButton")).click();
		//driver.findElement(By.linkText("Organizations")).click();
		//driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
		//driver.findElement(By.name("accountname")).sendKeys(org);

		//wLib.select(driver.findElement(By.name("industry")), ind);

		//wLib.select(driver.findElement(By.name("accounttype")), type1);

		//driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

		String act_succ_ind = driver.findElement(By.id("dtlview_Industry")).getText();
		if (act_succ_ind.contains(ind)) {
			System.out.println("industry is successfully created : pass");

		} else {
			System.out.println("industry is not created :fail");
		}

		String act_succ_typ = driver.findElement(By.id("dtlview_Type")).getText();
		if (act_succ_typ.contains(type1)) {
			System.out.println("type is successfully created : pass");

		} else {
			System.out.println("type is not created :fail");
		}
		
		
        hp.logout(driver);
		//wLib.mouseOverOnElemnet(driver, driver.findElement(By.xpath("//img[@src = 'themes/softed/images/user.PNG']")));
		//driver.findElement(By.linkText("Sign Out")).click();
		driver.quit();
	}

}
