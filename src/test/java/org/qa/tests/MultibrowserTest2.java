package org.qa.tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.qa.libs.ExcelUtility;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MultibrowserTest2 {
	WebDriver driver;
	Properties prop;
	
	@DataProvider
	public Object[][] getusername()
	{
		return null;
	
	}
	@Parameters("browser")
	@Test
	public void driverinitialization(String browser) throws FileNotFoundException, IOException
	{
		if(browser.equalsIgnoreCase("chrome"))
		{
			driver=new ChromeDriver();
			WebDriverManager.chromedriver().setup();
		}
		else if(browser.equalsIgnoreCase("firefox"))
		{
			driver=new FirefoxDriver();
			WebDriverManager.firefoxdriver().setup();
		}
	
	    prop = new Properties();
		File f = new File("D:\\Automation\\work spaces\\endtoend.framework1\\Configs\\qa.properties");
		FileInputStream fis = new FileInputStream(f);
		prop.load(fis);
		driver.get(prop.getProperty("url"));
		driver.manage().window().maximize();		
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));		
        Login("7036037090");	
	}
	public void Login(String phoneno)
	{
		WebElement mobileno= driver.findElement(By.xpath("//input[contains(@class,'_18u87m')]"));
		mobileno.sendKeys(phoneno);
		
		//mobileno.sendKeys(prop.getProperty("username"));
		driver.findElement(By.xpath("//button[text()='Request OTP']")).click();
		//driver.quit();
	}

}
