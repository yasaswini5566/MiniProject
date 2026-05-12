package utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class ExcelStore {

    private static final String RESULT_PATH =
            "C:\\Users\\2487721\\IdeaProjects\\MiniProject\\.mvn\\resource\\TestResults.xlsx";

    public static void writeResult(String testName,
                                   String expectedResult,
                                   String actualResult,
                                   String status) {

        try {
            Workbook workbook;
            Sheet sheet;
            File file = new File(RESULT_PATH);

            if (file.exists()) {
                FileInputStream fis = new FileInputStream(file);
                workbook = WorkbookFactory.create(fis);
                sheet = workbook.getSheet("Results");
            } else {
                workbook = new XSSFWorkbook();
                sheet = workbook.createSheet("Results");

                Row header = sheet.createRow(0);
                header.createCell(0).setCellValue("TestCaseName");
                header.createCell(1).setCellValue("Expected Result");
                header.createCell(2).setCellValue("Actual Result");
                header.createCell(3).setCellValue("Status");
            }

            CellStyle passStyle = workbook.createCellStyle();
            passStyle.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
            passStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            CellStyle failStyle = workbook.createCellStyle();
            failStyle.setFillForegroundColor(IndexedColors.ROSE.getIndex());
            failStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            int rowCount = sheet.getLastRowNum();
            Row row = sheet.createRow(rowCount + 1);

            row.createCell(0).setCellValue(testName);
            row.createCell(1).setCellValue(expectedResult);
            row.createCell(2).setCellValue(actualResult);

            Cell statusCell = row.createCell(3);
            statusCell.setCellValue(status);

            if (status.equalsIgnoreCase("PASS")) {
                statusCell.setCellStyle(passStyle);
            } else if (status.equalsIgnoreCase("FAIL")) {
                statusCell.setCellStyle(failStyle);
            }

            sheet.autoSizeColumn(0);
            sheet.autoSizeColumn(1);
            sheet.autoSizeColumn(2);
            sheet.autoSizeColumn(3);

            FileOutputStream fos = new FileOutputStream(RESULT_PATH);
            workbook.write(fos);
            fos.close();
            workbook.close();

        } catch (Exception e) {
            System.out.println("Failed to write results: " + e.getMessage());
        }
    }
}