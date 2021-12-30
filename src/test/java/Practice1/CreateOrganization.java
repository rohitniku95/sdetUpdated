package Practice1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

public class CreateOrganization {
	public static void main(String[] args) throws Throwable {

		FileUTiltiy fLib = new FileUTiltiy();
		JavaUtility jLib = new JavaUtility();
		WebdriverUtility wLib = new WebdriverUtility();
		ExcelUtility eLib = new ExcelUtility();

		String BROWSER = fLib.getPropertyKeyValue("browser");
		String URL = fLib.getPropertyKeyValue("url");
		String USERNAME = fLib.getPropertyKeyValue("username");
		String PASSWORD = fLib.getPropertyKeyValue("password");

		int randomnum = jLib.getRandomNumber();

		String org = eLib.getDataFromExcel("orgname", 1, 2) + randomnum;

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

		LoginPage lp = new LoginPage(driver);
		lp.loginToApp(USERNAME, PASSWORD);

		HomePage hp = new HomePage(driver);
		hp.clickOnOrganizationLink();

		OrganizationsPage op = new OrganizationsPage(driver);
		op.clickOnCreateOrg();

		CreateOrganizationPage cop = new CreateOrganizationPage(driver);
		cop.createOrg(org);

		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String actsucc_msg = oip.OrgInfo();
		if (actsucc_msg.contains(org)) {
			System.out.println("org is successfully created : pass");
		} else {
			System.out.println("org is not created :fail");
		}
		hp.logout(driver);
		driver.quit();
	}

}
