package com.amazon.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage {
	
	WebDriver driver;
	
	//Constructor
	public CartPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//Page objects
	//1. Delete item from cart link
	@FindBy(xpath="//input[@value='Delete']")
	private WebElement deleteItemLink;
	
	//2. Your cart is empty text
	@FindBy(xpath="//h1[normalize-space()='Your Amazon Cart is empty.']")
	private WebElement cartIsEmptyMessage;
	
	
	//Action methods
	public void clickDeleteItemLink() {
		deleteItemLink.click();
	}
	
	public String getCartIsEmptyMessageText() {
		return cartIsEmptyMessage.getText();
	}
	
	public boolean cartIsEmptyIsDisplayed() {
		return cartIsEmptyMessage.isDisplayed();
	}

}
