package week5.day2;

import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadXcel {
public static String[][] readXcel(String fileName) throws IOException {
	//Set the path for Excel workbook
	@SuppressWarnings("resource")
	XSSFWorkbook wb = new XSSFWorkbook("./data/" +fileName+ ".xlsx");
	//Get into the sheet
	XSSFSheet ws = wb.getSheet("CreateLead");
	//Get the number of rows(Exclude Header)
	int rowCount = ws.getLastRowNum();
	System.out.println("Number of rows excludes header : "+rowCount);
	//Get the number of rows includes header
	int totalRowCount = ws.getPhysicalNumberOfRows();
	System.out.println("Number of rows including header : "+totalRowCount);
	//Getting the column count
	int cellCount = ws.getRow(0).getLastCellNum();
	System.out.println("Number of columns : "+cellCount);
	//Getting the data
	String[][] data = new String[rowCount][cellCount]; 
	for(int i=1; i<=rowCount; i++) {
		XSSFRow row = ws.getRow(i);
		for(int j=0; j< cellCount; j++) {
			XSSFCell cell = row.getCell(j);
			String cellValue = cell.getStringCellValue();
			System.out.println(data);
			data[i-1][j]=cellValue;
		}

	}
	return data;
	

}
}
