package utils;

import org.apache.poi.ss.usermodel.*;
//import org.apache.poi.xssf.usermodel.XSSFChartSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.io.IOException;

public class ExcelUtils {

    public static Object[][] getExcelData(String path, String sheetName) throws IOException {
        FileInputStream file = new FileInputStream(path);
        Workbook xss = new XSSFWorkbook(file);
        Sheet sheet = xss.getSheet(sheetName);
        int rowCount = sheet.getPhysicalNumberOfRows();
        int columnCount = sheet.getRow(0).getPhysicalNumberOfCells();
        Object[][] data=new Object[rowCount-1][columnCount];
        DataFormatter formatter = new DataFormatter();
        for(int i=1;i<rowCount;i++)
        {
            Row row= sheet.getRow(i);
            for(int j=0;j<columnCount;j++)
            {
                Cell cell = row.getCell(j);
                data[i-1][j]=formatter.formatCellValue(cell);
            }
        }
        xss.close();
        file.close();
        return data;
    }
}