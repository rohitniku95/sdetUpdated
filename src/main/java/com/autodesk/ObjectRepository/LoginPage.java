package com.autodesk.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {// step 1:create a sepreat class for web page

	// step 2:identify all webelement and declear them as private

	@FindBy(name = "user_name")
	private WebElement usernameEdt;

	@FindBy(name = "user_password")
	private WebElement passwordEdt;

	@FindBy(id = "submitButton")
	private WebElement loginBtn;

	// step 3:initialize the element using constructor

	public LoginPage(WebDriver driver)// here we using constructor LoginPage
	{
		PageFactory.initElements(driver, this);
	}
	// step 4:Declare all the elements as private & provide

	public WebElement getUsernameEdt() {
		return usernameEdt;
	}

	public WebElement getPasswordEdt() {
		return passwordEdt;
	}

	public WebElement getLoginBtn() {
		return loginBtn;
	}
//Business Library
	public void loginToApp(String userName, String password) {
		/* step 1: login */
		usernameEdt.sendKeys(userName);
		passwordEdt.sendKeys(password);
		loginBtn.click();
	}
}
