package com.holydota.common.tool.excel;

import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ForbidDriver {
    public static void main(String[] args) throws IOException {

        XSSFWorkbook xwb = new XSSFWorkbook("/Users/luoqi/Downloads/result.xlsx");
        XSSFSheet sheet = xwb.getSheetAt(0);
        XSSFRow row;
        for (int i = sheet.getFirstRowNum(); i < sheet.getPhysicalNumberOfRows(); i++) {
            row = sheet.getRow(i);
            StringBuilder sb = new StringBuilder(
                "UPDATE T_DriverStar SET ForbidTime = '20160',ForbidReason='阳泉10.20全城禁用2周',Forbid=5,forbidBeginTime='2015-10-20 17:00:00' WHERE driverId=");
            int first = row.getFirstCellNum();
            sb.append((int) row.getCell(first).getNumericCellValue() + ";");
            System.out.println(sb.toString());
        }

    }
}
