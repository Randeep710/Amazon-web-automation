package com.amazon.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ChangeLanguageSettingsPage {
	
	WebDriver driver;
	
	//Constructor
	public ChangeLanguageSettingsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	//Page Objects
	//1. Hindi Language link
	@FindBy(xpath="//span[@class='a-label a-radio-label'][contains(text(),'हिंदी - HI')]")
	private WebElement hindiLanguageLink;
	
	//2. Save button
	@FindBy(xpath="//input[@class='a-button-input' and @type='submit']")
	private WebElement saveButton;
	
	
	//Action methods
	public void selectHindiLanguageLink() {
		hindiLanguageLink.click();
	}
	
	public void clickSaveButton() {
		saveButton.click();
	}

}
