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
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
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

public class MultibrowserTest3 {
	WebDriver driver;
	Properties prop;
	
		@Parameters("browser")
	@BeforeMethod
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
        //Login("7036037090");	
	}
	
	@DataProvider
	public String[] getTestData() throws EncryptedDocumentException, IOException
	{
		 File f = new File("C:\\Users\\saiha\\OneDrive\\Documents\\data_phoneNumber.xlsx");
	       FileInputStream fis= new FileInputStream(f);
	       Workbook wb= WorkbookFactory.create(fis);
	       Sheet sheet= wb.getSheet("Sheet1");
	       int rowsno= sheet.getPhysicalNumberOfRows();
	       int colno=sheet.getRow(0).getLastCellNum();
	       //int colno2=sheet.getRow(0).getPhysicalNumberOfCells();
	       int number1=(int) sheet.getRow(1).getCell(0).getNumericCellValue();
	       String str1=String.valueOf(number1);
	       int number2=(int) sheet.getRow(2).getCell(0).getNumericCellValue();
	       String str2=String.valueOf(number2);
	       String[] str = {str1,str2};    	          
		return str;	  
		
	}
	
	@Test(dataProvider="getTestData")	
	public void Login(String number)
	{
		WebElement mobileno= driver.findElement(By.xpath("//input[contains(@class,'_18u87m')]"));
		mobileno.sendKeys(number);		
		//mobileno.sendKeys(prop.getProperty("username"));
		driver.findElement(By.xpath("//button[text()='Request OTP']")).click();
		//driver.quit();
	}
	
	@AfterMethod
	public void Logout()
	{
		
	}
	
	

}
