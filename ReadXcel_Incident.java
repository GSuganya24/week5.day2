package week5.day2;

import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadXcel_Incident {
public static String[][] readIncident(String filename) throws IOException {
	//Set the path of the Excel workbook
	XSSFWorkbook wb = new XSSFWorkbook("./data/"+filename+".xlsx");
	
	//Get into the sheet
	XSSFSheet ws = wb.getSheet("CreateIncident");
	
	//Get rows count with header
	int totalRowsCount = ws.getPhysicalNumberOfRows();
	System.out.println(totalRowsCount);
	//Get rows count without header
	int rowsCount = ws.getLastRowNum();
	System.out.println(rowsCount);
	//Get no. of cells count
	int cellCount = ws.getRow(0).getLastCellNum();
	System.out.println(cellCount);
	String[][] data = new String [rowsCount][cellCount];
	for(int i = 1; i<=rowsCount; i++) {
		XSSFRow row = ws.getRow(i);
		for(int j = 0;j<cellCount;j++) {
			//Get into the cell
			XSSFCell cell = row.getCell(j);
			//Retrieve the data
			String cellValue = cell.getStringCellValue();
			data[i-1][j] = cellValue;
		}
	}
	//Close the workbook
	wb.close();
	return data;
}
}
