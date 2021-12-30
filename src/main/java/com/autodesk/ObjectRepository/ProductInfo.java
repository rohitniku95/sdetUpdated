package com.autodesk.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductInfo {
	
	@FindBy(xpath="//span[@class='lvtHeaderText']")
	private WebElement productHeaderText;
	
	@FindBy(xpath="//td[@id='mouseArea_Qty/Unit']")
	private WebElement QtyUnitText;
	
	public ProductInfo(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public WebElement getProductHeaderText() {
		return productHeaderText;
	}
	//Business Language productHeader
	public String getProductInfo() {
		return (productHeaderText.getText());
	}
	//Business Language Qtyunit Header
	public String getQtyunitInfo() {
		return(QtyUnitText.getText());
	}
}
