package com.opencart.customer.Utilities;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelUtility {

    public FileInputStream fileInput;
    public FileOutputStream fileOutput;
    public XSSFWorkbook workbook;
    public XSSFSheet sheet;
    public XSSFRow row;
    public XSSFCell cell;
    public CellStyle style;
    String xlPath;

    public ExcelUtility(String path) {
        this.xlPath = path;
    }

    public int getRowCount(String sheetName) throws IOException {
        fileInput = new FileInputStream(xlPath);
        workbook = new XSSFWorkbook(fileInput);
        sheet = workbook.getSheet(sheetName);
        int rowCount = sheet.getLastRowNum();
        workbook.close();
        fileInput.close();
        return rowCount;
    }

    public int getCellCount(String sheetName,int rowNum) throws IOException {
        fileInput = new FileInputStream(xlPath);
        workbook = new XSSFWorkbook(fileInput);
        sheet = workbook.getSheet(sheetName);
        row = sheet.getRow(rowNum);
        int cellCount = row.getLastCellNum();
        workbook.close();
        fileInput.close();
        return cellCount;
    }

    public String getCellData(String sheetName, int rowNum, int colNum) throws IOException {
        fileInput = new FileInputStream(xlPath);
        workbook = new XSSFWorkbook(fileInput);
        sheet = workbook.getSheet(sheetName);
        row = sheet.getRow(rowNum);
        cell = row.getCell(colNum);

        DataFormatter formatter = new DataFormatter();
        String data;
        try {
            data = formatter.formatCellValue(cell);
        }
        catch (Exception e) {
            data="";
        }
        workbook.close();
        fileInput.close();
        return data;
    }

    public void setCellData(String sheetName, int rowNum, int colNum, String data) throws IOException {
        File excelFile = new File(xlPath);

        if (!excelFile.exists()) {
            workbook = new XSSFWorkbook();
            fileOutput = new FileOutputStream(xlPath);
            workbook.write(fileOutput);
        }

        fileInput = new FileInputStream(xlPath);
        workbook = new XSSFWorkbook(fileInput);

        if (workbook.getSheetIndex(sheetName) == 1) {
            workbook.createSheet(sheetName);
            sheet = workbook.getSheet(sheetName);
        }

        if (sheet.getRow(rowNum) == null) {
            sheet.createRow(rowNum);
            row = sheet.getRow(rowNum);
        }
        cell = row.createCell(colNum);
        cell.setCellValue(data);
        fileOutput = new FileOutputStream(xlPath);
        workbook.write(fileOutput);
        workbook.close();
        fileInput.close();
        fileOutput.close();
    }

    public void fillGreenColor(String sheetName, int rowNum, int colNum) throws IOException {
        fileInput = new FileInputStream(xlPath);
        workbook = new XSSFWorkbook(fileInput);
        sheet = workbook.getSheet(sheetName);
        row = sheet.getRow(rowNum);
        cell = row.getCell(colNum);

        style = workbook.createCellStyle();

        style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);

        cell.setCellStyle(style);
        fileOutput = new FileOutputStream(xlPath);
        workbook.write(fileOutput);
        workbook.close();
        fileInput.close();
        fileOutput.close();
    }

    public void fillRedColor(String sheetName, int rowNum, int colNum) throws IOException {
        fileInput = new FileInputStream(xlPath);
        workbook = new XSSFWorkbook(fileInput);
        sheet = workbook.getSheet(sheetName);
        row = sheet.getRow(rowNum);
        cell = row.getCell(colNum);

        style = workbook.createCellStyle();

        style.setFillForegroundColor(IndexedColors.RED.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);

        cell.setCellStyle(style);
        fileOutput = new FileOutputStream(xlPath);
        workbook.write(fileOutput);
        workbook.close();
        fileInput.close();
        fileOutput.close();
    }
}
