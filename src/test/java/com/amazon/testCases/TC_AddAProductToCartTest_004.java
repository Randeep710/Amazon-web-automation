package com.amazon.testCases;

import java.io.IOException;
import java.util.Set;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.amazon.pageObjects.LandingPage;
import com.amazon.pageObjects.ProductDetailsPage;
import com.amazon.pageObjects.ProductSearchResultsPage;

public class TC_AddAProductToCartTest_004 extends BaseClass {
	
	@Test
	public void addAProductToCart() throws InterruptedException, IOException {
		
		logger.info("URL is opened");
		
		LandingPage landingPage = new LandingPage(driver);
		
		//Test data as variables for searching a product
		String searchCategory = "all categories";
		String searchText = "iphone 11";
		
		
		landingPage.selectSearchCategory(searchCategory);
		logger.info("Search Category is selected");
		
		ProductSearchResultsPage productSearchResultsPage = landingPage.enterSearchText(searchText);
		logger.info("Search Text is entered");
		
		ProductDetailsPage productDetailsPage = productSearchResultsPage.clickProductLink();
		
		Thread.sleep(6000);
		
		String parentWindow = driver.getWindowHandle();
		System.out.println("Parent window handle = "+parentWindow);
		
		Set<String> windows = driver.getWindowHandles();
		
		int count = windows.size();
		System.out.println("Total windows = "+count);
		
		for(String window : windows) {
			if(!window.equals(parentWindow)) {
				driver.switchTo().window(window);
			}
		}
		
		productDetailsPage.clickAddToCartButton();
		logger.info("Clicked on Add to Cart button");
		
		Thread.sleep(3000);
		
		String text = productDetailsPage.getAddedToCardText();
		System.out.println("Text : "+text);
		
		//Verify if "Added to Cart" text is visible on screen
		boolean result = productDetailsPage.addedToCartIsDisplayed();
		
		System.out.println("Verify whether product is added to cart : "+result);
		logger.info("Verified visisbility of Added to Cart message on screen");
			
		
		if(result == true) {
			logger.info("Test Case Status : PASSED");
			Assert.assertTrue(true);
		} else {
			captureScreenshot(driver, "addAProductToCart");  //capture screenshot as Test Case FAILS
			logger.info("Test Case Status : PASSED");
			Assert.assertTrue(false);
		}
		
	}

}
