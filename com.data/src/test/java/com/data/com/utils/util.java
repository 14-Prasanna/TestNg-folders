package com.data.com.utils;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class util {

    @DataProvider(name = "valid")
    public Object[][] excelvalid() throws IOException {

        Object[][] arr = excelDataProvider();

        return new Object[][] {
            arr[0]
        };
    }

    @DataProvider(name = "invalid")
    public Object[][] excelinvalid() throws IOException {

        Object[][] arr = excelDataProvider();

        return new Object[][] {
            arr[1], arr[2]
        };
    }

    public Object[][] excelDataProvider() throws IOException {

        Object[][] arrObj = getExcelData(
                "D:\\TestNG\\com.data\\src\\test\\resources\\book4.xlsx",
                "book3"
        );

        return arrObj;
    }

    private Object[][] getExcelData(String file, String sheetName) throws IOException {

        String[][] data = null;

        try {
            FileInputStream fis = new FileInputStream(file);
            XSSFWorkbook work = new XSSFWorkbook(fis);

            XSSFSheet sheet = work.getSheet(sheetName);

            int noOfRows = sheet.getPhysicalNumberOfRows();

            data = new String[noOfRows - 1][2];

            DataFormatter df = new DataFormatter();

            for (int i = 1; i < noOfRows; i++) {

                XSSFRow row = sheet.getRow(i);

                for (int j = 0; j < 2; j++) {

                    Cell cell = row.getCell(j);
                    data[i - 1][j] = df.formatCellValue(cell);
                }
            }

            work.close();
            fis.close();

        } catch (Exception e) {
            System.out.println("The exception is : " + e.getMessage());
        }

        return data;
    }
}