
package helperFunctions;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import java.io.File;
import java.io.FileInputStream;

public class ExcelReader {
    public static String readCellValue(String filePath, int sheetNumber, int rowNum, int cellNum) {
        try (FileInputStream fis = new FileInputStream(new File(filePath));
             Workbook workbook = WorkbookFactory.create(fis)) {
            Sheet sheet = workbook.getSheetAt(sheetNumber);
            Row row = sheet.getRow(rowNum);
            return row.getCell(cellNum).getStringCellValue();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}