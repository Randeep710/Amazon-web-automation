package com.amazon.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductSearchResultsPage {
	
	WebDriver driver;
	
	//Constructor
	public ProductSearchResultsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	
	//Page Objects
	//1. First search result link
	@FindBy(xpath="//body/div[@id='a-page']/div[@id='search']/div[1]/div[1]/div[1]/span[3]/div[2]/div[2]/div[1]/span[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/h2[1]/a[1]/span[1]")
	private WebElement productLink;
	
	//2. Amazon home link
	@FindBy(id="nav-logo-sprites")
	private WebElement amazonHomeLink;
	
	
	//Action Methods
	//1. Click on product link
	public ProductDetailsPage clickProductLink() {
		productLink.click();
		return new ProductDetailsPage(driver);
	}
	
	//2. Click on Amazon home link
	public LandingPage clickAmazonHomeLink() {
		amazonHomeLink.click();
		return new LandingPage(driver);
	}
}
