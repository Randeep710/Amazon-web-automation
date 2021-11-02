package com.amazon.testCases;

import java.io.IOException;

import org.junit.Assert;
import org.testng.annotations.Test;

import com.amazon.pageObjects.LandingPage;

public class TC_ChangePageLanguageTest_002 extends BaseClass {
	
	@Test
	//changing the language of the page without navigating to ChangeLanguageSettings page
	//involves use of Actions class to handle Menu and Sub-menu elements
	public void changePageLanguage() throws InterruptedException, IOException {
		
		logger.info("URL is opened");
		
		LandingPage landingPage = new LandingPage(driver);
		
		landingPage.changeLanguage();
		logger.info("Page Language is changed");
		
		String pageTitle = driver.getTitle();
		System.out.println("Page Title = "+pageTitle);
		
		String hindiPageTitle = "भारत का ऑनलाइन खरीदारी साईट : मोबाइल, किताबें, घडियां, जूते और अधिक - Amazon.in";
		
		if(driver.getTitle().equals(hindiPageTitle)) {
			logger.info("Test Case status : PASSED");
			Assert.assertTrue(true);
		} else {
			captureScreenshot(driver, "changePageLanguage");  //capture screenshot as Test Case FAILS
			logger.info("Test Case status : FAILED");
			Assert.assertTrue(false);
		}
		
	}

}
