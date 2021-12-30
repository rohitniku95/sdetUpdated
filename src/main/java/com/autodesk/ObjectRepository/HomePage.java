package com.autodesk.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.autodesk.genericUtility.WebdriverUtility;

public class HomePage extends WebdriverUtility{
	WebDriver driver;  //global driver variable
	
	//step 2:deceleration
	
	@FindBy(linkText= "Organizations")
	private WebElement organizationlnk;
	
	@FindBy(linkText= "Contacts")
	private WebElement contactsLnk;
	
	@FindBy(linkText= "Opportunities")
	private WebElement opportunitiesLnk;
	
	@FindBy(linkText= "Products")
	private WebElement productsLnk;
	
	@FindBy(linkText= "Documents")
	private WebElement documentsLnk;
	
	@FindBy(linkText= "Email")
	private WebElement emailLnk;
	
	@FindBy(linkText= "Trouble Tickets")
	private WebElement troubleTicketsLnk;
	
	@FindBy(xpath= "//img[@src='themes/softed/images/user.PNG']")
	private WebElement administratorImg;
	
	@FindBy(linkText= "Sign Out")
	private WebElement signOutLnk;
	
	public HomePage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver , this);
	}
//step 4:Utilization

	public WebElement getOrganizationlnk() {
		return organizationlnk;
	}

	public WebElement getContactsLnk() {
		return contactsLnk;
	}

	public WebElement getOpportunitiesLnk() {
		return opportunitiesLnk;
	}

	public WebElement getProductsLnk() {
		return productsLnk;
	}

	public WebElement getDocumentsLnk() {
		return documentsLnk;
	}

	public WebElement getEmailLnk() {
		return emailLnk;
	}

	public WebElement getTroubleTicketsLnk() {
		return troubleTicketsLnk;
	}

	public WebElement getAdministratorImg() {
		return administratorImg;
	}

	public WebElement getSignOutLnk() {
		return signOutLnk;
	}
	//business library to click on organization
	
	public void clickOnOrganizationLink() {
		organizationlnk.click();
	}
	//business library to click on contacts
	public void clickOnContactsLink() {
		contactsLnk.click();
	}
	public void clicOnProductLink() {
		productsLnk.click();
	}
	//business library to click on contacts
	public void logout(WebDriver driver) {
		mouseOverOnElemnet(driver, administratorImg);
		signOutLnk.click();
	}
}
