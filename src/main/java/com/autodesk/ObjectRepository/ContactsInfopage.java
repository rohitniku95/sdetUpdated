package com.autodesk.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactsInfopage {
	
	//Declaration
	@FindBy(className = "dvHeaderText")
	private WebElement contactHeaderInfoText;
	
	@FindBy(id = "mouseArea_Organization Name")
	private WebElement orgheaderText;
	
	//initialization
	public ContactsInfopage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	
	//utilization
	
			
	public WebElement getContactHeaderInfoText() {
		return contactHeaderInfoText;
	}
	

	
	//Business Library
	
	public WebElement getOrgheaderText() {
		return orgheaderText;
	}


	public String getcontactInfo() {
		return (contactHeaderInfoText.getText());
		
	}
	
	public String orgHeaderTextinContact() {
		return (orgheaderText.getText());
		
	}

}
