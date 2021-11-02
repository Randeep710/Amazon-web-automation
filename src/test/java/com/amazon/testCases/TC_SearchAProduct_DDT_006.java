package com.amazon.testCases;

import java.io.IOException;

import org.junit.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.amazon.pageObjects.LandingPage;
import com.amazon.pageObjects.ProductSearchResultsPage;
import com.amazon.utilities.ExcelUtils;

public class TC_SearchAProduct_DDT_006 extends BaseClass {
	
	@Test(dataProvider="Category-Product-Data")
	public void searchAProductDDT(String category, String product) throws IOException, InterruptedException {
		
		logger.info("URL is opened");
		
		Thread.sleep(3000);
		
		//create an object of LandingPage.java class in order to access its methods.
		LandingPage landingPage = new LandingPage(driver);
				
		landingPage.selectSearchCategory(category);
		
		logger.info("Search Category '"+category+"' is selected");
		
		Thread.sleep(3000);
		
		ProductSearchResultsPage productSearchResultsPage = landingPage.enterSearchText(product);
		
		logger.info("Search Text '"+product+"' is entered and ENTER key is pressed");
		
		Thread.sleep(3000);
		
		String pageTitle = "Amazon.in : "+product;
		
		if(driver.getTitle().equals(pageTitle)) {
			
			logger.info("Test Case status : PASSED");
			Assert.assertTrue(true);
			landingPage = productSearchResultsPage.clickAmazonHomeLink();
			logger.info("Clicked on Amazon home link \n\n");
			Thread.sleep(3000);
			
		} else {
			captureScreenshot(driver, "searchAProductDDT");  //capture screenshot as Test Case FAILS
			logger.info("Test Case status : FAILED");
			Assert.assertTrue(false);
		}
		
	}
	
	@DataProvider(name="Category-Product-Data")
	String[][] getData() throws IOException{
		String path = System.getProperty("user.dir")+"/src/test/java/com/amazon/testData/Category-Product-Data.xlsx";
		int rowCount = ExcelUtils.getRowCount(path, "Sheet1");
		int colCount = ExcelUtils.getCellCount(path, "Sheet1", 1);
		
		String[][] categoryProductData = new String[rowCount][colCount];
		
		for(int i=1; i<=rowCount; i++) {
			for(int j=0; j<colCount; j++) {
				categoryProductData[i-1][j] = ExcelUtils.getCellData(path, "Sheet1", i, j);
			}
		}
		return categoryProductData;
	}

}
