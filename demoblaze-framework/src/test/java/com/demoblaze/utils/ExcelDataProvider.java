package com.demoblaze.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

import java.io.*;
import java.util.*;

public class ExcelDataProvider {

    private static final Logger log = LogManager.getLogger(ExcelDataProvider.class);
    private static final String EXCEL_FILE_PATH = ConfigReader.getExcelPath();

    @DataProvider(name = "validLoginData", parallel = true)
    public static Object[][] getValidLoginData() {
        return readExcelData(ConfigReader.getValidSheet());
    }

    @DataProvider(name = "invalidLoginData", parallel = true)
    public static Object[][] getInvalidLoginData() {
        return readExcelData(ConfigReader.getInvalidSheet());
    }

    public static Object[][] readExcelData(String sheetName) {
        log.info("Reading Excel sheet: '{}' from: {}", sheetName, EXCEL_FILE_PATH);
        List<Object[]> dataList = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(EXCEL_FILE_PATH);
             XSSFWorkbook workbook = new XSSFWorkbook(fis)) {                  

            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                log.error("Sheet '{}' not found in {}", sheetName, EXCEL_FILE_PATH);
                throw new RuntimeException("Sheet not found: " + sheetName);
            }

            DataFormatter formatter = new DataFormatter();
            FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();

            int totalRows = sheet.getLastRowNum();                   
            Row headerRow = sheet.getRow(0);                               
            int totalCols = headerRow.getLastCellNum();

            log.info("Sheet '{}': {} data rows, {} columns", sheetName, totalRows, totalCols);

            for (int rowIdx = 1; rowIdx <= totalRows; rowIdx++) {
                Row row = sheet.getRow(rowIdx);
                if (row == null || isRowEmpty(row, formatter)) {
                    log.debug("Skipping empty row at index: {}", rowIdx);
                    continue;
                }

                Object[] rowData = new Object[totalCols];
                for (int colIdx = 0; colIdx < totalCols; colIdx++) {
                    Cell cell = row.getCell(colIdx, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);

                    String cellValue = formatter.formatCellValue(cell, evaluator).trim();
                    rowData[colIdx] = cellValue;

                    log.debug("Row[{}] Col[{}] = '{}'", rowIdx, colIdx, cellValue);
                }
                dataList.add(rowData);
            }

            logDataSummary(sheetName, dataList);

        } catch (IOException e) {
            log.error("IOException reading Excel: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to read Excel file: " + EXCEL_FILE_PATH, e);
        }

        return dataList.toArray(new Object[0][]);
    }

    public static List<String> getColumnData(String sheetName, int columnIndex) {
        log.info("Reading column {} from sheet '{}'", columnIndex, sheetName);
        List<String> columnData = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(EXCEL_FILE_PATH);
             XSSFWorkbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);
            DataFormatter formatter = new DataFormatter();

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {  
                Row row = sheet.getRow(i);
                if (row != null) {
                    Cell cell = row.getCell(columnIndex, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    columnData.add(formatter.formatCellValue(cell).trim());
                }
            }

        } catch (IOException e) {
            log.error("Failed to read column: {}", e.getMessage(), e);
        }

        return columnData;
    }

    public static List<String> getHeaders(String sheetName) {
        List<String> headers = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(EXCEL_FILE_PATH);
             XSSFWorkbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);
            DataFormatter formatter = new DataFormatter();
            Row headerRow = sheet.getRow(0);

            if (headerRow != null) {
                for (Cell cell : headerRow) {
                    headers.add(formatter.formatCellValue(cell).trim());
                }
            }
        } catch (IOException e) {
            log.error("Failed to read headers: {}", e.getMessage(), e);
        }
        return headers;
    }

    public static void writeTestResult(String sheetName, int rowIndex, int resultColIndex,
                                        String result) {
        log.info("Writing result '{}' to sheet '{}', row {}, col {}", result, sheetName, rowIndex, resultColIndex);

        try (FileInputStream fis = new FileInputStream(EXCEL_FILE_PATH);
             XSSFWorkbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);
            Row row = sheet.getRow(rowIndex);
            if (row == null) {
                row = sheet.createRow(rowIndex);
            }

            Cell cell = row.createCell(resultColIndex);
            cell.setCellValue(result);

            CellStyle style = workbook.createCellStyle();
            Font font = workbook.createFont();
            font.setBold(true);
            style.setFont(font);

            if ("PASS".equalsIgnoreCase(result)) {
                style.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
            } else {
                style.setFillForegroundColor(IndexedColors.RED.getIndex());
            }
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            cell.setCellStyle(style);

            try (FileOutputStream fos = new FileOutputStream(EXCEL_FILE_PATH)) {
                workbook.write(fos);
                log.info("Result written successfully to Excel.");
            }

        } catch (IOException e) {
            log.error("Failed to write result to Excel: {}", e.getMessage(), e);
        }
    }

    private static boolean isRowEmpty(Row row, DataFormatter formatter) {
        for (Cell cell : row) {
            if (!formatter.formatCellValue(cell).trim().isEmpty()) {
                return false;
            }
        }
        return true;
    }

    private static void logDataSummary(String sheetName, List<Object[]> data) {
        log.info("=== Data Summary for sheet '{}' ===", sheetName);
        log.info("Total data rows read: {}", data.size());
        for (int i = 0; i < data.size(); i++) {
            log.info("  Row {}: {}", i + 1, Arrays.toString(data.get(i)));
        }
        log.info("======================================");
    }
}
