package demo.utils;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class LoginDataProvider {

	 String path = "D:\\TestNG\\demo\\src\\test\\resources\\loginDetails.xlsx";

	    @DataProvider(name = "valid")
	    public Object[][] validlogin() throws IOException {
	        return excelSheet("valid");
	    }

	    @DataProvider(name = "invalid", parallel = true)
	    public Object[][] invalidLogin() throws IOException{
	        return excelSheet("invalid");
	    }

	    public Object[][] excelSheet(String sheet) throws IOException{
	        String sheets = sheet;
	        return excelSheetReader(path, sheets);
	    }

	    public Object[][] excelSheetReader(String paths, String sheets) throws IOException {

	    	FileInputStream fis = new FileInputStream(paths);
	    	XSSFWorkbook book = new XSSFWorkbook(fis);

	    	XSSFSheet sheet = book.getSheet(sheets);

	    	

	    	DataFormatter format = new DataFormatter();

	    	int rowNo = sheet.getPhysicalNumberOfRows();
	    	int colNo = sheet.getRow(0).getLastCellNum();


	    	Object[][] data = new Object[rowNo][colNo];

	    	for (int i = 0; i < rowNo; i++) {
	    		XSSFRow row = sheet.getRow(i);

	    		for (int j = 0; j < colNo; j++) {
	    			XSSFCell cell = row.getCell(j);
	    			data[i][j] = format.formatCellValue(cell);
	    	}
    }

	    		book.close();
	    		return data;
	    }
	}

