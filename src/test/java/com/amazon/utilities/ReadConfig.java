package com.amazon.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {
	
	Properties properties;
	
	//Constructor
	public ReadConfig() {
		
		File file = new File("./Configuration/config.properties");
		
		try {
			
			FileInputStream fis =  new FileInputStream(file);
			properties = new Properties();
			properties.load(fis);
			
		} catch(Exception e) {
			System.out.println("Exeption encountered : "+e.getMessage());
		}
	}
	
	
	//Methods to read each key-value pair in config.properties file under Configuration folder:
	//1. get URL
	public String getApplicationURL() {
		return properties.getProperty("baseURL");
	}
	
	//2. get chromedriver path for chrome browser
	public String getChromePath() {
		return properties.getProperty("chromePath");
	}
	
	//3. get geckodriver path for firefox browser
	public String getFirefoxPath() {
		return properties.getProperty("firefoxPath");
	}
	
	

}
