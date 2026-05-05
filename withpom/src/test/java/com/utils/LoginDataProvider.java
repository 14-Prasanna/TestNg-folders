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
	
	@DataProvider(name = "valid", parallel = true) 
	public Object[][] valid() throws IOException{
		
		return excelSheetRead("valid");
	}
	
	@DataProvider(name = "invalid", parallel = true)
	public Object[][] invalid()  throws IOException{
		
		return excelSheetRead("invalid");
	
	}
	
	public Object[][] excelSheetRead(String sheet) throws IOException{
		
		String path = "D:\\TestNG\\withpom\\src\\test\\resources\\loginData.xlsx";
		String sheetName = sheet;
		return excelDataRead(path, sheetName);
	}
	
	public Object[][] excelDataRead(String paths, String sheetName) throws IOException {

    FileInputStream fis = new FileInputStream(paths);
    XSSFWorkbook book = new XSSFWorkbook(fis);
    XSSFSheet sheet = book.getSheet(sheetName);

    int Totalrow = sheet.getPhysicalNumberOfRows();
    int Totalcol = sheet.getRow(0).getLastCellNum();

    Object[][] data = new Object[Totalrow - 1][Totalcol];

    DataFormatter format = new DataFormatter();

    for (int i = 1; i < Totalrow; i++) {

        XSSFRow row = sheet.getRow(i);

        for (int j = 0; j < Totalcol; j++) {

            XSSFCell cell = row.getCell(j);
            data[i - 1][j] = format.formatCellValue(cell);
        }
    }

    book.close();
    fis.close();

    return data;
}

}
