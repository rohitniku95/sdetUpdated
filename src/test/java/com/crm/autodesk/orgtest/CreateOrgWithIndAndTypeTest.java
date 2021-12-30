package com.crm.autodesk.orgtest;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.autodesk.ObjectRepository.CreateOrganizationPage;
import com.autodesk.ObjectRepository.HomePage;
import com.autodesk.ObjectRepository.OrganizationInfoPage;
import com.autodesk.ObjectRepository.OrganizationsPage;
import com.crm.autodesk.genericUtility.BaseClass;

public class CreateOrgWithIndAndTypeTest extends BaseClass {

	@Test(groups = "RegressionSuite")
	public void CreateOrgWithIndAndTypeTest() throws Throwable {

		int ranDomNum = jLib.getRandomNumber();

		String org = eLib.getDataFromExcel("orgname", 5, 2) + ranDomNum;
		String ind = eLib.getDataFromExcel("orgname", 5, 3);
		String type1 = eLib.getDataFromExcel("orgname", 5, 4);

		HomePage hp = new HomePage(driver);
		hp.clickOnOrganizationLink();

		OrganizationsPage op = new OrganizationsPage(driver);
		op.clickOnCreateOrg();

		CreateOrganizationPage cop = new CreateOrganizationPage(driver);
		cop.createOrgWithIndustryAndType(org, ind, type1);

		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String act_msg = oip.OrgInfo();

		Assert.assertTrue(act_msg.contains(org));


		System.out.println("org is successfuly created : pass");

		String act_suc_ind = oip.IndustriesCreatedInfo();
		SoftAssert sa = new SoftAssert();
		sa.assertTrue(act_suc_ind.contains(act_suc_ind));


		System.out.println("Industrie created : pass");

		String act_type_created = oip.TypeCreatedInfo();
		sa.assertTrue(act_type_created.contains(type1));

		System.out.println("type not created :fail");
	}
}

