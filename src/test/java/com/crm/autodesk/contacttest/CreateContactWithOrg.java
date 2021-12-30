package com.crm.autodesk.contacttest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.autodesk.ObjectRepository.ContactsInfopage;
import com.autodesk.ObjectRepository.ContactsPage;
import com.autodesk.ObjectRepository.CreateContactPage;
import com.autodesk.ObjectRepository.CreateOrganizationPage;
import com.autodesk.ObjectRepository.HomePage;
import com.autodesk.ObjectRepository.LoginPage;
import com.autodesk.ObjectRepository.OrganizationsPage;
import com.crm.autodesk.genericUtility.BaseClass;
import com.crm.autodesk.genericUtility.ExcelUtility;
import com.crm.autodesk.genericUtility.FileUTiltiy;
import com.crm.autodesk.genericUtility.JavaUtility;
import com.crm.autodesk.genericUtility.WebdriverUtility;

public class CreateContactWithOrg extends BaseClass {
	@Test(groups = "RegressionSuite")
	
	public void createContactWithOrg() throws Throwable {
		
		int ranDomNum = jLib.getRandomNumber();
		
		String contact = eLib.getDataFromExcel("contact", 1, 2) + ranDomNum;
		String org = eLib.getDataFromExcel("orgname", 1, 2) + ranDomNum;

		HomePage hp = new HomePage(driver);
		hp.clickOnOrganizationLink();
		
		OrganizationsPage op = new OrganizationsPage(driver);
		op.clickOnCreateOrg();
		
		CreateOrganizationPage cop = new CreateOrganizationPage(driver);
		cop.createOrg(org);
		
		wLib.waitForElementToBeClickabale(driver,driver.findElement(By.className("dvHeaderText")));
		hp.clickOnContactsLink();
		
		ContactsPage cp = new ContactsPage(driver);
		cp.clickOnCreateContactImg();
		
		CreateContactPage ccp = new CreateContactPage(driver);
		ccp.createContactWithOrg(org, contact, driver);
		
		ContactsInfopage cip = new ContactsInfopage(driver);
		String acct_msg_contact = cip.getcontactInfo();
		
		Assert.assertTrue(acct_msg_contact.contains(contact));
			System.out.println("contact is successfuly created : pass");
		
			
			  String acct_msg_org = cip.orgHeaderTextinContact(); 
			  SoftAssert sa =new SoftAssert();
			  sa.assertTrue(acct_msg_org.contains(org));
			  System.out.println(" contact is not created with org : fail");
			  sa.assertAll();
			 
		}
	}

