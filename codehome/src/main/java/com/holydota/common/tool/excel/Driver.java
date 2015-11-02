package com.holydota.common.tool.excel;

import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Driver {

    public static void main(String[] args) throws IOException {

        XSSFWorkbook xwb = new XSSFWorkbook("/Users/luoqi/Downloads/hehe.xlsx");
        XSSFSheet sheet = xwb.getSheetAt(0);
        XSSFRow row;
        for (int i = sheet.getFirstRowNum(); i < sheet.getPhysicalNumberOfRows(); i++) {
            row = sheet.getRow(i);
            StringBuilder sb = new StringBuilder("UPDATE Driver SET carNo = '");
            int first = row.getFirstCellNum();
            sb.append(row.getCell(first + 1) + "' , company ='" + row.getCell(first + 2) + "' WHERE driverId="
                      + (int) row.getCell(first).getNumericCellValue() + ";");
            System.out.println(sb.toString());
        }

    }
}
