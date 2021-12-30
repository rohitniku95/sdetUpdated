package com.autodesk.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactsPage {
	
	@FindBy(xpath = "//img[@alt='Create Contact...']")
	private WebElement createContactLookupImg;
	
	@FindAll({@FindBy(name= "search_text"),@FindBy(xpath ="//input[@class='txtBox']")})
	private WebElement searchtextedt;
	
	@FindBy(name ="submit")
	private WebElement searchNowBtn;
	
	//initialization
	public ContactsPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public WebElement getCreateContactLookupImg() {
		return createContactLookupImg;
	}

	public WebElement getSearchtextedt() {
		return searchtextedt;
	}

	public WebElement getSearchNowBtn() {
		return searchNowBtn;
	}
	//Utilization
	
	public void clickOnCreateContactImg() {
		createContactLookupImg.click();
		
	}
}
