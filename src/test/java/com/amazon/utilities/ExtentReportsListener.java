package com.amazon.utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

//This is a Listener Class used to generate Extent Reports
public class ExtentReportsListener extends TestListenerAdapter
{
	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest test;
	
	//1. On Test Start	
	public void onStart(ITestContext testContext)
	{
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()); //capture time stamp
		String reportName = "Test-Report-"+timeStamp+".html"; //add time stamp along with the name of the report
		
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+ "/test-output/"+reportName); //specify location of the report
		htmlReporter.loadXMLConfig(System.getProperty("user.dir")+ "/extentReports-config.xml"); //load configuration file for extent reports
		
		extent = new ExtentReports();
		
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Host name","localhost");
		extent.setSystemInfo("Environemnt","QA");
		extent.setSystemInfo("user","randeep");
		
		htmlReporter.config().setDocumentTitle("Amazon-web-automation"); //title of the report
		htmlReporter.config().setReportName("Functional Test Automation Report"); //name of the report
		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP); //location of the chart
		htmlReporter.config().setTheme(Theme.DARK);
	}
	
	//2. On Test Success
	public void onTestSuccess(ITestResult testResult)
	{
		test=extent.createTest(testResult.getName()); // create new entry in the report
		test.log(Status.PASS,MarkupHelper.createLabel(testResult.getName(),ExtentColor.GREEN)); //send the Passed information to the report with GREEN color highlighted
	}
	
	//3. On Test Failure
	public void onTestFailure(ITestResult testResult)
	{
		test = extent.createTest(testResult.getName()); // create new entry in the report
		test.log(Status.FAIL,MarkupHelper.createLabel(testResult.getName(),ExtentColor.RED)); //send the Failed information to the report with RED color highlighted
		
		String screenshotPath=System.getProperty("user.dir")+"\\Screenshots\\"+testResult.getName()+".png";
		
		File file = new File(screenshotPath); 
		
		if(file.exists())
		{
			try {
				test.fail("Screenshot is below:" + test.addScreenCaptureFromPath(screenshotPath));
			} 
			catch (IOException e) {
					e.printStackTrace();
			}
		}
	}
	
	//4. On Test Skipped
	public void onTestSkipped(ITestResult testResult)
	{
		test = extent.createTest(testResult.getName()); // create new entry in the report
		test.log(Status.SKIP,MarkupHelper.createLabel(testResult.getName(),ExtentColor.ORANGE)); //send the Skipped information to the report with ORANGE color highlighted
	}
	
	//5. On Test Completion
	public void onFinish(ITestContext testContext)
	{
		extent.flush();
	}
}