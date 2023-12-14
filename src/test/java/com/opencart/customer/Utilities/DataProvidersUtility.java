package com.opencart.customer.Utilities;

import org.testng.annotations.DataProvider;

public class DataProvidersUtility {

    @DataProvider(name = "LoginData")
    public String[][] getLoginData() {
        try {
            String xlPath = System.getProperty("user.dir") + "\\data\\LoginData.xlsx";

            ExcelUtility excelUtility = new ExcelUtility(xlPath);

            int totalRows = excelUtility.getRowCount("Sheet1");
            int totalCells = excelUtility.getCellCount("Sheet1", 1);

            String[][] loginData = new String[totalRows][totalCells];

            for (int i = 1; i <= totalRows; i++) {
                for (int j = 0; j < totalCells; j++) {
                    loginData[i - 1][j] = excelUtility.getCellData("Sheet1", i, j);
                }
            }
            return loginData;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new String[0][];
    }
}
