package com.autodesk.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductAvaility {
	//Step 1:Click on Product
	
	//Step 2:Create new product
	@FindBy(xpath= "//img[@alt='Create Product...']")
	private WebElement createProductlookupImg;
	//Step 3:insert Product name
	@FindBy(name = "productname")
	private WebElement productNameEdt;
	//Step 4:insert quantity per unit
	@FindBy(name = "qty_per_unit")
	private WebElement quantityPerunitEdt;
	//Step 5:save button
	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;
	
	public ProductAvaility(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	public WebElement getCreateProductlookupImg() {
		return createProductlookupImg;
	}
	public WebElement getProductNameEdt() {
		return productNameEdt;
	}
	public WebElement getQuantityPerunitEdt() {
		return quantityPerunitEdt;
	}
	public WebElement getSaveBtn() {
		return saveBtn;
	}
	public WebElement productimgclick() {
		return createProductlookupImg;
	}
	public void createProductLookupImg() {
		createProductlookupImg.click();
	}
    public void createProduct(String productName) {
    	productNameEdt.sendKeys(productName);
    }
    public void createquantityPerunit(String qty) {
    	quantityPerunitEdt.sendKeys(qty);
    }
    public void clickOnSaveButton() {
    	saveBtn.click();
    }
    
}
