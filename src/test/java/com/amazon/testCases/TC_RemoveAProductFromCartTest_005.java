package com.amazon.testCases;

import java.io.IOException;
import java.util.Set;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.amazon.pageObjects.CartPage;
import com.amazon.pageObjects.LandingPage;
import com.amazon.pageObjects.ProductDetailsPage;
import com.amazon.pageObjects.ProductSearchResultsPage;

public class TC_RemoveAProductFromCartTest_005 extends BaseClass {
	
	@Test
	public void removeAProductFromCart() throws InterruptedException, IOException {
		
		logger.info("URL is opened");
		
		LandingPage landingPage = new LandingPage(driver);
		
		//Test data as variables for searching a product
		String searchCategory = "All Categories";
		String searchText = "iphone 11";
		
		
		landingPage.selectSearchCategory(searchCategory);
		logger.info("Search Category is selected");
		
		ProductSearchResultsPage productSearchResultsPage = landingPage.enterSearchText(searchText);
		logger.info("Search Text is entered");
		
		ProductDetailsPage productDetailsPage = productSearchResultsPage.clickProductLink();
		
		Thread.sleep(6000);
		
		//Handling new tab
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
		System.out.println("Text displayed after a Product is added to Cart : "+text);
		
		//Verify if "Added to Cart" text is visible on screen
		boolean result = productDetailsPage.addedToCartIsDisplayed();
		
		System.out.println("Verify whether product is added to cart : "+result);
		logger.info("Verified visisbility of Added to Cart message on screen");
		
		CartPage cartPage = productDetailsPage.clickCartButton();
		
		Thread.sleep(3000);
		
		//Remove product from cart
		cartPage.clickDeleteItemLink();
		
		String emptyCartText = cartPage.getCartIsEmptyMessageText();
		System.out.println("Text displayed when the Cart is empty : "+emptyCartText);
		
		System.out.println("Title of the page : "+driver.getTitle());
		
		//Verify if "Your Amazon Cart is empty" text is visible on screen
		boolean verification = cartPage.cartIsEmptyIsDisplayed();
		System.out.println("Verify whether cart is empty : "+verification);
		logger.info("Verified visisbility of Your Cart is empty message on screen");
		
		String pageTitle = "Amazon.in Shopping Cart";
		
		if(driver.getTitle().equals(pageTitle) && verification==true) {
			logger.info("Test Case Status : PASSED");
			Assert.assertTrue(true);
		} else {
			captureScreenshot(driver, "removeAProductFromCart");  //capture screenshot as Test Case FAILS
			logger.info("Test Case Status : FAILED");
			Assert.assertTrue(false);
		}
		
	}

}
