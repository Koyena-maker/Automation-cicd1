package Automation.TestComponents;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import koyenamukherjee.pageobjects.Landingpage;

public class BaseTest {
	public WebDriver driver;
	 public Landingpage lpg;
  
	public WebDriver initialiseDriver() throws IOException
	{
		
	    Properties prop=new Properties();
		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\koyenamukherjee\\resources\\GlobalData.properties");
		prop.load(fis);
		String browserName=System.getProperty("browser")!=null ? System.getProperty("browser") : prop.getProperty("browser");
		//prop.getProperty("browser");
		
		if(browserName.contains("chrome"))
		{
		 ChromeOptions options=new ChromeOptions();
		 WebDriverManager.chromedriver().setup();
		 if(browserName.contains("headless"))
		 {
			 options.addArguments("headless");
		 }
		 driver=new ChromeDriver(options);
		 driver.manage().window().setSize(new Dimension(1440,900));
		}
		
		
		else if (browserName.contains("firefox"))
		{
			 FirefoxOptions options=new FirefoxOptions();
			 WebDriverManager.firefoxdriver().setup();
			 if(browserName.contains("headless"))
			 {
				 options.addArguments("-headless");
			 }
			 driver=new FirefoxDriver(options);
			 driver.manage().window().setSize(new Dimension(1440,900));
		}
		
		
		else if (browserName.contains("edge"))
		{
			EdgeOptions options=new EdgeOptions();
			 WebDriverManager.edgedriver().setup();
			 if(browserName.contains("headless"))
			 {
				 options.addArguments("headless");
			 }
			 driver=new EdgeDriver(options);
			 driver.manage().window().setSize(new Dimension(1440,900));
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		return driver;
	}
	
	public  List<HashMap<String, String>> getJsonDataforHashmap(String filePath) throws IOException 
	{
		
		String jsonData=FileUtils.readFileToString(new File(filePath),
				StandardCharsets.UTF_8);
	    ObjectMapper mapper=new ObjectMapper();
	    List<HashMap<String,String>> data=mapper.readValue(jsonData, new TypeReference< List<HashMap<String,String>>> (){});
	    return data;
	}

	
	@BeforeMethod(alwaysRun=true)
	public  Landingpage launchApplication() throws IOException
	{
		 driver=initialiseDriver();
		 lpg=new Landingpage(driver);
		 lpg.goTo();
		 return  lpg;
	}
	
	@AfterMethod(alwaysRun=true)
	public  void tearDown()
	{
		driver.quit();
	}

	public String getScreenshot(String testcaseName,WebDriver driver) throws IOException 
	{
		TakesScreenshot sc = (TakesScreenshot) driver;// cast driver to screenshot mode for taking pictures
		File localMachiene = sc.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "//reports//" + testcaseName + ".png");
		FileUtils.copyFile(localMachiene, file);
		return System.getProperty("user.dir") + "//reports//" + testcaseName + ".png";
	}
	    
}
