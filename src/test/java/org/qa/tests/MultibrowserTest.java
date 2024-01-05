package org.qa.tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.qa.libs.ExcelUtility;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MultibrowserTest {
	WebDriver driver;
	Properties prop;
	
	@Parameters("browser")
	@BeforeClass
	public void driverinitialization(String browser)
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
	}	
	
	@BeforeMethod
	public void LoginTest() throws IOException
	{
	    prop = new Properties();
		File f = new File("D:\\Automation\\work spaces\\endtoend.framework1\\Configs\\qa.properties");
		FileInputStream fis = new FileInputStream(f);
		prop.load(fis);
		driver.get(prop.getProperty("url"));
		driver.manage().window().maximize();		
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));		
	}
	
	
	@DataProvider
	public String[] getTestData() throws EncryptedDocumentException, IOException
	{
		String[] testdata=ExcelUtility.getDataFromExcel();
		return testdata;
		
	}
	
	@Test(dataProvider="getTestData")
	
	public void Login(String number)
	{
		driver.findElement(By.xpath("//span[text()='Login']")).click();
		driver.findElement(By.xpath("//input[@fdprocessedid=\"a1qli\"]")).sendKeys(prop.getProperty("username"));	
	}
	
	@AfterMethod
	public void Logout()
	{
		
	}
	
	
	@AfterClass
	public void closedriver()
	{
		driver.quit();
	}

}
