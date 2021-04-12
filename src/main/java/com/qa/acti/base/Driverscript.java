package com.qa.acti.base;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class Driverscript {

	public static WebDriver driver;
	Properties prop;	

	public Driverscript ()
	{
		try
		{
			File src = new File("./ACconfig/config.properties");
			FileInputStream fis = new FileInputStream(src);
			prop = new Properties();
			prop.load(fis);
		}

		catch (Exception e)
		{
			System.out.println("Unable to load the file"+ e.getMessage());
		}
	}
@Test
	public void initApplication()
	{
		String browser = prop.getProperty("browser");
		if(browser.equalsIgnoreCase("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", "./Acbrowser/chromedriver.exe");
			driver = new ChromeDriver();
		}
		
		else if(browser.equalsIgnoreCase("firefox"))
		{
			System.setProperty("webdriver.gecko.driver", "./Acbrowser/geckodriver.exe");
			driver = new FirefoxDriver();
		}
		else
		{
			System.out.println("Browser name not specified corectly - Plese provide proper brwoser name");
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		String url = prop.getProperty("qaurl");
		driver.get(url);

	}

}
