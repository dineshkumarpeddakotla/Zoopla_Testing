/*
 *Purpose : Class is implemented for reading data from excel sheet and writing data into a excel sheet
 *
 * @author Dinesh Kumar Peddakotla
 * @version 1.0
 * @since 15-07-2021
 */

package com.zoopla.utility;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelUtil {

    FileInputStream fis;
    FileOutputStream fos;
    XSSFWorkbook workbook;
    XSSFSheet sheet;
    XSSFCell cell;
    CellStyle style;
    String path;
    File file;

    /**
     * parameterized constructor
     * @param path file path of excel file
     * @param sheetName sheet name of workbook
     */
    public ExcelUtil(String path, String sheetName) {
        this.path = path;
        try {
            file = new File(path);
            if (!file.exists()) {
                throw new FileNotFoundException("File doesn't Exits"); //custom file not found exception
            }
            fis = new FileInputStream(file);
            workbook = new XSSFWorkbook(fis);
            sheet = workbook.getSheet(sheetName);
            fis.close();
        } catch (IOException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * getRowCount method is used to get the no of rows present in a sheet
     * @return no of rows
     */
    private int getRowCount() {
        int rowCount = 0;
        try {
            rowCount = sheet.getPhysicalNumberOfRows();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rowCount;
    }

    /**
     * getCountCount method is used to get the no of column in a row
     * @return no of columns in a row
     */
    private int getColumnCount() {
        int Column_Count = 0;
        try {
            Column_Count = sheet.getRow(0).getPhysicalNumberOfCells();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Column_Count;
    }

    /**
     * getCellData method is used to read data of a each cell
     * @param rowNum row value of particular cell in a sheet
     * @param colNum column value of particular cell in a sheet
     * @return column data
     */
    private String getCellData(int rowNum, int colNum) {
        cell = sheet.getRow(rowNum).getCell(colNum);
        DataFormatter formatter = new DataFormatter();
        String data;
        try {
            data = formatter.formatCellValue(cell); //Returns the formatted value of a cell as a String regardless of the cell type.
        } catch (Exception e) {
            e.printStackTrace();
            data = "";
        }

        return data;
    }

    /**
     * readData method is used get all data inside a sheet
     * @return data inside a sheet
     */
    public Object[][] readData() {
        int rows = getRowCount();
        int columns = getColumnCount();

        String[][] data = new String[rows - 1][columns];

        for (int i = 1; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                data[i - 1][j] = getCellData(i, j);
            }
        }

        return data;
    }

    /**
     * setCellData method is used to set the value into cell of a excel sheet
     * @param testCaseName test method name is giving as parameter
     * @param testStatus status of a test, whether test is pass or fail and skipped
     * @param rowNumber giving row number in a sheet
     */
    private void setCellData(String testCaseName, String testStatus, int rowNumber) {
        try {
            XSSFRow currentRow = sheet.getRow(rowNumber);
            currentRow.createCell(0).setCellValue(testCaseName);//set data into a cell
            currentRow.createCell(4).setCellValue(testStatus);//set status into a cell
            cell = currentRow.getCell(4); //getting cell of status

            style = workbook.createCellStyle(); //cell style created in a cell

            if (testStatus.equals("Pass")) {
                style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
            } else if (testStatus.equals("Fail")) {
                style.setFillForegroundColor(IndexedColors.RED.getIndex());
            } else {
                style.setFillForegroundColor(IndexedColors.ORANGE.getIndex());
            }

            //fill cell with colour
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            cell.setCellStyle(style); // cell style is updated

            file = new File(path);
            if (!file.exists()) {
             throw new FileNotFoundException("File doesn't exits"); //custom file not found exception is used
            }

            //writing the data FileOutputStream is used
            fos = new FileOutputStream(file);
            workbook.write(fos); // data is written in sheet
            fos.close(); //file is closed
        } catch (FileNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * writeData method is used to write data into a excel sheet
     * @param parameters parameters used in testcase is given object array
     * @param testName test name of a method
     * @param testStatus status of test method, whether the test case is passed or failed or skipped
     */
    public void writeData(Object[] parameters, String testName, String testStatus) {
        int rowCount = getRowCount();

        for (int i=0; i< rowCount; i++) {
            String email = getCellData(i, 1);//email value from sheet
            String password = getCellData(i, 2);//password value from sheet

            if (email.equals(parameters[0]) && password.equals(parameters[1])) {
                setCellData(testName, testStatus, i);
            }
        }
    }
}
