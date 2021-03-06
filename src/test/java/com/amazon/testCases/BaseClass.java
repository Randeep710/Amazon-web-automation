package com.amazon.testCases;





import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.amazon.utilities.ReadConfig;

public class BaseClass {
	
	//creating a ReadConfig object to access its methods,
	//in order to read config.properties file
	ReadConfig readConfig = new ReadConfig();
	
	public String baseURL = readConfig.getApplicationURL();
	public static WebDriver driver;
	public static Logger logger;
	
	@Parameters("browser")
	@BeforeClass
	public void setup(String browser) {
		
		//System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"//Drivers//chromedriver.exe");
		
		if(browser.equals("chrome")) {
			
			System.setProperty("webdriver.chrome.driver", readConfig.getChromePath());
			driver = new ChromeDriver();
		}
		
		if(browser.equals("firefox")) {
			
			System.setProperty("webdriver.gecko.driver", readConfig.getFirefoxPath());
			driver = new FirefoxDriver();
		}
		
		
		//Log4j
		logger = Logger.getLogger("amazonWeb");
		PropertyConfigurator.configure("Log4j.properties");
		
		//Driver waits for PageLoad and Implicitly wait
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				
		
		//Go to URL
		driver.get(baseURL);
	}
	
	
	//Method to capture screenshot when TestCase FAILS
	public void captureScreenshot(WebDriver driver, String testName) throws IOException {
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
		File destination = new File(System.getProperty("user.dir")+"/Screenshots/"+testName+".png");
		FileUtils.copyFile(source, destination);
		System.out.println("Screenshot captured");
	}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}

}
