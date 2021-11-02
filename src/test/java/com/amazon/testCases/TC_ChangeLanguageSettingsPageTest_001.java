package com.amazon.testCases;

import java.io.IOException;

import org.junit.Assert;
import org.testng.annotations.Test;

import com.amazon.pageObjects.ChangeLanguageSettingsPage;
import com.amazon.pageObjects.LandingPage;

public class TC_ChangeLanguageSettingsPageTest_001 extends BaseClass {
	
	@Test
	public void changeLanguageSettings() throws InterruptedException, IOException {
		
		logger.info("URL is opened");
		
		LandingPage landingPage = new LandingPage(driver);
		
		ChangeLanguageSettingsPage changeLanguageSettingsPage = new ChangeLanguageSettingsPage(driver);
		
		changeLanguageSettingsPage = landingPage.clickChangeLanguageLink();
		logger.info("Clicked on chage language link inside Landing Page");	
		
		changeLanguageSettingsPage.selectHindiLanguageLink();
		logger.info("Clicked on HINDI language link inside Change Language Settings Page");
		
		changeLanguageSettingsPage.clickSaveButton();
		logger.info("Clicked on SAVE button inside Change Language Settings Page");
		
		String hindiPageTitle = "भारत का ऑनलाइन खरीदारी साईट : मोबाइल, किताबें, घडियां, जूते और अधिक - Amazon.in";
		
		if(driver.getTitle().equals(hindiPageTitle)) {
			logger.info("Test Case status : PASSED");
			Assert.assertTrue(true);
		} else {
			captureScreenshot(driver, "changeLanguageSettings");  //capture screenshot as Test Case FAILS
			logger.info("Test Case status : FAILED");
			Assert.assertTrue(false);
		}
	}

}
