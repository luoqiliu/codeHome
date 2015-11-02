package com.holydota.common.tool.excel;

import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Forbid {
    public static void main(String[] args) throws IOException {

        XSSFWorkbook xwb = new XSSFWorkbook("/Users/luoqi/Downloads/hehe.xlsx");
        XSSFSheet sheet = xwb.getSheetAt(0);
        XSSFRow row;
        for (int i = sheet.getFirstRowNum(); i < sheet.getPhysicalNumberOfRows(); i++) {
            row = sheet.getRow(i);
            StringBuilder sb = new StringBuilder(
                "UPDATE T_DriverStar SET ForbidTime = '0',ForbidReason='运营要求和滴滴信息同步解禁',Forbid=0 WHERE driverId=");
            int first = row.getFirstCellNum();
            sb.append((int) row.getCell(first).getNumericCellValue() + ";");
            System.out.println(sb.toString());
        }

    }
}
