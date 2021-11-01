package com.amazon.pageObjects;

import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class LandingPage {
	
	WebDriver driver;
	
	//constructor
	public LandingPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//Page elements
	//1. Change Language Link
	@FindBy(id="icp-nav-flyout")
	private WebElement changeLanguageLink;
	
	//hindi link
	@FindBy(xpath="//div[@id='nav-flyout-icp']//span[contains(@class,'nav-text')][contains(text(),'हिंदी - HI')]")
	private WebElement hindiLanguageLink;
	
	//3. Search Category
	@FindBy(id="searchDropdownBox")
	private WebElement searchCategory;
		
	//4. Search Text
	@FindBy(id="twotabsearchtextbox")
	private WebElement searchTextInput;
	

	//Action methods
	//1. Click on change language link
	public ChangeLanguageSettingsPage clickChangeLanguageLink() {
		changeLanguageLink.click();
		return new ChangeLanguageSettingsPage(driver);
	}
	
	//2. Change page language at one go
	public void changeLanguage() throws InterruptedException {
			
		Actions action = new Actions(driver);
		
		action.moveToElement(changeLanguageLink).build().perform();
		action.moveToElement(hindiLanguageLink).click().build().perform();
	}
	
	//3. Select a search category
	public void selectSearchCategory(String categoryName) {
		Select select = new Select(searchCategory);
		List<WebElement> categoryList = select.getOptions();
		
		for(WebElement category : categoryList) {
			if(category.getText().equalsIgnoreCase(categoryName)) {
				category.click();
			}
		}
	}
	
	//4. Enter search text
	public ProductSearchResultsPage enterSearchText(String productName) {
		searchTextInput.sendKeys(productName);
		searchTextInput.sendKeys(Keys.ENTER);
		return new ProductSearchResultsPage(driver);
	}
	
}
