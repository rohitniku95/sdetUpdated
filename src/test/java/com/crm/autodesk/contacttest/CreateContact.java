package com.crm.autodesk.contacttest;

import static org.testng.Assert.assertEquals;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.autodesk.ObjectRepository.ContactsInfopage;
import com.autodesk.ObjectRepository.ContactsPage;
import com.autodesk.ObjectRepository.CreateContactPage;
import com.autodesk.ObjectRepository.HomePage;
import com.autodesk.ObjectRepository.LoginPage;
import com.crm.autodesk.genericUtility.BaseClass;
import com.crm.autodesk.genericUtility.ExcelUtility;
import com.crm.autodesk.genericUtility.FileUTiltiy;
import com.crm.autodesk.genericUtility.WebdriverUtility;

@Listeners(com.crm.autodesk.genericUtility.ListnersImplementation.class)
public class CreateContact extends BaseClass {


	@Test(groups = "smokeSuite")
	public void createContact() throws Throwable {

		int ranDomNum = jLib.getRandomNumber();

		String contact = eLib.getDataFromExcel("contact", 1, 2) + ranDomNum;

		HomePage hp = new HomePage(driver);
		hp.clickOnContactsLink();

		ContactsPage cp = new ContactsPage(driver);
		cp.clickOnCreateContactImg();
		
		Assert.fail();

		CreateContactPage ccp = new CreateContactPage(driver);
		ccp.createContact(contact);


		ContactsInfopage cip = new ContactsInfopage(driver);
		String acct_text = cip.getcontactInfo();

        //Assert.assertTrue(acct_text.contains(contact));
		
			System.out.println("contact is successfully created : pass");
	}
}
