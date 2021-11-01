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
	
	// https://www.amazon.in/gp/slredirect/picassoRedirect.html/ref=pa_sp_atf_aps_sr_pg1_1?ie=UTF8&adId=A0052772MZBZ53468TH5&url=%2FNew-Apple-iPhone-XR-64GB%2Fdp%2FB08L8BK9V6%2Fref%3Dsr_1_1_sspa%3Fcrid%3D2MOJAFJ6RXER6%26dchild%3D1%26keywords%3DiPhone%2BXR%26qid%3D1635233880%26sprefix%3Diphone%2Bxr%252Caps%252C337%26sr%3D8-1-spons%26psc%3D1&qualifier=1635233880&id=799641058421504&widgetName=sp_atf
	//Page Objects
	//1. First search result link
	@FindBy(xpath="//body/div[@id='a-page']/div[@id='search']/div[1]/div[1]/div[1]/span[3]/div[2]/div[2]/div[1]/span[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/h2[1]/a[1]/span[1]")
	//@FindBy(xpath="//span[text()='Apple iPhone XR (64GB) - (Product) RED']")
	private WebElement productLink;
	
	
	//Action Methods
	//1. Click on product link
	public ProductDetailsPage clickProductLink() {
		productLink.click();
		return new ProductDetailsPage(driver);
	}
}
