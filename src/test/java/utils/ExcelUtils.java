package utils;

import org.apache.poi.ss.usermodel.*;
import java.io.FileInputStream;

public class ExcelUtils {

    public static String getCellData(String key) {
        try {
            FileInputStream fis = new FileInputStream(
                    "C:\\Users\\2487721\\IdeaProjects\\MiniProject\\.mvn\\resource\\TestData1.xlsx");
            Workbook wb = WorkbookFactory.create(fis);
            Sheet sheet = wb.getSheetAt(0);

            for (Row row : sheet) {
                if (row.getCell(0).getStringCellValue().equalsIgnoreCase(key)) {
                    return row.getCell(1).getStringCellValue();
                }
            }
        } catch (Exception e) {
            System.out.println("Excel read error");
        }
        return null;
    }
}