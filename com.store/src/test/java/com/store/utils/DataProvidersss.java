package com.store.utils;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class DataProvidersss {

    @DataProvider(name = "valid")
    public Object[][] excelvalid() {

        Object[][] arr = excelData();

        return new Object[][] {
            arr[0]
        };
    }

    @DataProvider(name = "invalid")
    public Object[][] excelInvalid() {

        Object[][] arr = excelData();

        return new Object[][] {
            arr[1]
        };
    }

    public Object[][] excelData() {

        Object[][] arr = getExcel("D:\\TestNG\\com.store\\src\\test\\resources\\login.xlsx", "Sheet1");

        return arr;
    }

    public String[][] getExcel(String file, String sheetname) {

        String[][] data = null;

        try {
            FileInputStream fis = new FileInputStream(file);
            XSSFWorkbook work = new XSSFWorkbook(fis);
            XSSFSheet sheet = work.getSheet(sheetname);

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