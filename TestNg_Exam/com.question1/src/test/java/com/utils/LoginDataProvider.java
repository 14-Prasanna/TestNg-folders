package com.utils;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class LoginDataProvider {
	
	
	public static String path = "D:\\TestNg_Exam\\com.question1\\src\\test\\resources\\ResgiterAndLogin.xlsx";

	@DataProvider(name = "Register")
	public Object[][] Register() throws IOException{
		return excelSheet("Register");
	}
	
	@DataProvider(name = "LoginValid")
	public Object[][] LoginValid() throws IOException{
		return excelSheet("loginValid");
	}
	
	@DataProvider(name = "invalid")
	public Object[][] invalid() throws IOException{
		return excelSheet("Logininvalid");
	}
	
	public Object[][] excelSheet(String sheet) throws IOException{
		return excelSheetReader(sheet, path);
	}
	
	public Object[][] excelSheetReader(String sheetName, String PathName) throws IOException{
		
		FileInputStream fis = new FileInputStream(PathName);
		try (XSSFWorkbook book = new XSSFWorkbook(fis)) {
			XSSFSheet sheet = book.getSheet(sheetName);
			
			int rowNo = sheet.getPhysicalNumberOfRows();
			int colNo = sheet.getRow(0).getLastCellNum();
			
			DataFormatter format = new DataFormatter();
			
			Object[][] data = new Object[rowNo-1][colNo];
			
			
			for(int i=1;i<rowNo;i++) {
				XSSFRow row = sheet.getRow(i);
				
				for(int j=0;j<colNo;j++) {
					XSSFCell cell = row.getCell(j);
					data[i-1][j] = format.formatCellValue(cell);
				}
			}
			
			return data;
		}
	}
}
