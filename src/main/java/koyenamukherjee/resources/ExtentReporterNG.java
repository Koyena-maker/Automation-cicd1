package koyenamukherjee.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG 
{
   public static ExtentReports getReport()
   {
	   String path=System.getProperty("user.dir")+"//reports//index.html";
		ExtentSparkReporter sr=new ExtentSparkReporter(path);
		sr.config().setReportName("Web Automation Results");
		sr.config().setDocumentTitle("Test Results");
		
		//ExtentSparkReporter is a helper class of ExtentReports class
		ExtentReports extent=new ExtentReports();
		extent.attachReporter(sr);
		extent.setSystemInfo("Tester", "Koyena Mukherjee");
		return extent;
   }
}
