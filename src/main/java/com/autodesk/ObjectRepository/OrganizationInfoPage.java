package com.autodesk.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationInfoPage {
	
	@FindBy(xpath ="//span[@class='dvHeaderText']")
	private WebElement orgHeaderText;
	
	@FindBy(id="dtlview_Industry")
	private WebElement industriesCreatedInfo;
	
	@FindBy(id="dtlview_Type")
	private WebElement typeCreatedInfo;
	
	public OrganizationInfoPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public WebElement getOrgHeaderText() {
		return orgHeaderText;
	}
    
	public WebElement getIndustriesCreatedInfo() {
		return industriesCreatedInfo;
	}

	public WebElement getTypeCreatedInfo() {
		return typeCreatedInfo;
	}
	//Business Library
	
	public String OrgInfo() {
		return (orgHeaderText.getText());
	}
	public String IndustriesCreatedInfo() {
		return (industriesCreatedInfo.getText());
	}
public String TypeCreatedInfo() {
	return (typeCreatedInfo.getText());
   }
	
	}

