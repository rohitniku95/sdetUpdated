package com.autodesk.ObjectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.autodesk.genericUtility.WebdriverUtility;

public class CreateContactPage extends WebdriverUtility{
	
	//Declaration
	
	@FindBy(name = "lastname")
	private WebElement contactLastNameEdt;
	
	@FindBy(xpath = "//input[@name='account_name']/following-sibling::img")
	private WebElement OrgNameLookupImg;
	
	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;
	
	@FindBy(name= "search_text")
	private WebElement orgSearchText;
	
	@FindBy(name= "search")
	private WebElement orgSearchBtn;
	
	
	//initialization
	public CreateContactPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	
	public WebElement getOrgSearchText() {
		return orgSearchText;
	}
	public WebElement getOrgSearchBtn() {
		return orgSearchBtn;
	}
	
	
	public WebElement getContactLastNameEdt() {
		return contactLastNameEdt;
	}
	public WebElement getOrgNameLookupImg() {
		return OrgNameLookupImg;
	}
	public WebElement getSaveBtn() {
		return saveBtn;
	}
	//Business library for create contact
	
	public void createContact(String lastName) {
		contactLastNameEdt.sendKeys(lastName);
		saveBtn.click();
	}
	//create contact with organasation
	public void createContactWithOrg(String orgName,String lastname,WebDriver driver) {
		contactLastNameEdt.sendKeys(lastname);
		OrgNameLookupImg.click();
		
		switchToWindow(driver,"Accounts");
		orgSearchText.sendKeys(orgName);
		orgSearchBtn.click();
		
		driver.findElement(By.linkText(orgName)).click();
		switchToWindow(driver,"Contacts");
		saveBtn.click();
	}
}
