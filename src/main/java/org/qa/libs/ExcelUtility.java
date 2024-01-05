package org.qa.libs;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility
{
	public static String[] getDataFromExcel() throws EncryptedDocumentException, IOException
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
}
