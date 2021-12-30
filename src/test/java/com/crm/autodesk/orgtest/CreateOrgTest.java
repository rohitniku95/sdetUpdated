package com.crm.autodesk.orgtest;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.autodesk.ObjectRepository.CreateOrganizationPage;
import com.autodesk.ObjectRepository.HomePage;
import com.autodesk.ObjectRepository.OrganizationInfoPage;
import com.autodesk.ObjectRepository.OrganizationsPage;
import com.crm.autodesk.genericUtility.BaseClass;
import com.crm.autodesk.genericUtility.JavaUtility;

public class CreateOrgTest extends BaseClass {

	@Test(groups = "smokeSuite")
	public void createOrgTest() throws Throwable {
		// get random number
		int ranDomNum = jLib.getRandomNumber();

		// Read data from Excel

		String org = eLib.getDataFromExcel("orgname", 1, 2) + ranDomNum;

		// navigate to org module
		HomePage hp = new HomePage(driver);
		hp.clickOnOrganizationLink();

		// click on create org button
		OrganizationsPage op = new OrganizationsPage(driver);
		op.clickOnCreateOrg();

		// enter all the details and create new organization
		CreateOrganizationPage cop = new CreateOrganizationPage(driver);
		cop.createOrg(org);

		// verify org name in header of the msg
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String act_msg = oip.OrgInfo();
		
		Assert.assertTrue(act_msg.contains(org));
        
		//SoftAssert sa = new SoftAssert();
		//sa.assertTrue(act_msg.contains(org));
		//Reporter.log("org", true);
		///sa.assertAll();
		
	}

	@Test
	public void demo() {
		System.out.println("demo");
	}
}
