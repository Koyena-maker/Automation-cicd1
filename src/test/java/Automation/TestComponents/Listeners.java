package Automation.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import koyenamukherjee.resources.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener
{
	ExtentTest test;
	ExtentReports extent=ExtentReporterNG.getReport();
	ThreadLocal <ExtentTest> extentTest=new ThreadLocal<ExtentTest>();//thread safe
   @Override
   public void onTestStart(ITestResult result)
   {
	  test=extent.createTest(result.getMethod().getMethodName()); 
	  extentTest.set(test);//unique thread id is generated for 'test'
   }
   
   @Override
   public void onTestSuccess(ITestResult result)
   {
	   extentTest.get().log(Status.PASS, "Test is pass!");
   }
   
   @Override
   public void onTestFailure(ITestResult result)
   {
	   String filePath = null;
	 extentTest.get().fail(result.getThrowable());
	  
	  
	  try 
	  {
		driver=(WebDriver)result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
	  } 
	  catch (Exception e)//illegal argument
	  {
		
		e.printStackTrace();
	  } 
	
	  try {
		 filePath=getScreenshot(result.getMethod().getMethodName(),driver);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
   }
   
   @Override
   public void onFinish(ITestContext context)
   {
	  extent.flush();
   }
}
