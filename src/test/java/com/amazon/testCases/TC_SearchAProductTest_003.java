package com.amazon.testCases;

import java.io.IOException;

import org.junit.Assert;
import org.testng.annotations.Test;

import com.amazon.pageObjects.LandingPage;
import com.amazon.pageObjects.ProductSearchResultsPage;

public class TC_SearchAProductTest_003 extends BaseClass {
	
	@Test
	public void searchAProduct() throws InterruptedException, IOException {
		
		//go to URL
		//driver.get(baseURL);  --> taken care by BaseClass
		
		logger.info("URL is opened");
		
		Thread.sleep(3000);
		
		//variables
		final String categoryName = "all categories";
		final String searchText = "iphone xr";
		
		//create an object of LandingPage.java class in order to access its methods.
		LandingPage landingPage = new LandingPage(driver);
				
		landingPage.selectSearchCategory(categoryName);
		
		logger.info("Search Category is selected");
		
		Thread.sleep(3000);
		
		ProductSearchResultsPage productSearchResultsPage = landingPage.enterSearchText(searchText);
		
		logger.info("Search Text is entered and ENTER key is pressed");
		
		Thread.sleep(3000);
		
		String pageTitle = "Amazon.in : "+searchText;
		
		if(driver.getTitle().equals(pageTitle)) {
			
			logger.info("Test Case status : PASSED");
			Assert.assertTrue(true);
		} else {
			captureScreenshot(driver, "searchAProduct");  //capture screenshot as Test Case FAILS
			logger.info("Test Case status : FAILED");
			Assert.assertTrue(false);
		}
		
	}

}
