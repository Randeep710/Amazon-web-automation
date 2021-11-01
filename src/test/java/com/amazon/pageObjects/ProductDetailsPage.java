package com.amazon.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductDetailsPage {
	
	WebDriver driver;
	
	//Constructor
	public ProductDetailsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//PageObjects
	//1. Add-to-cart button
	@FindBy(xpath="//input[@id='add-to-cart-button']")
	private WebElement addToCartButton;
	
	//2. Added to Cart text
	@FindBy(xpath="//div[@id='attachDisplayAddBaseAlert']//h4[@class='a-alert-heading'and text()='Added to Cart']")
	private WebElement addedToCartText;
	
	//3. Cart button
	@FindBy(xpath="//input[@aria-labelledby='attach-sidesheet-view-cart-button-announce']")
	private WebElement cartButton;
	
	//Action Methods
	//1. Click on addToCartButton
	public void clickAddToCartButton() {
		addToCartButton.click();
	}
	
	//2. Get "Added to Cart" text
	public String getAddedToCardText() {
		return addedToCartText.getText();
	}
	
	//3. "Added to Cart" message visible or not
	public boolean addedToCartIsDisplayed() {
		return addedToCartText.isDisplayed();
	}
	
	//6.Click on Cart button
	public CartPage clickCartButton() {
		cartButton.click();
		return new CartPage(driver);
	}

}
