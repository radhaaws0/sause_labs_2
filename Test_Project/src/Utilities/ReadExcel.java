package Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.DataProvider;




public class ReadExcel {

	@DataProvider(name="userdata")
	public String[][] getdata(Method m) throws EncryptedDocumentException, IOException {
		
		String excelSheetName = m.getName();
		
		File f = new File(System.getProperty("user.dir") + "//src//Testdata//testdata.xlsx");
		FileInputStream fis = new FileInputStream(f);
		Workbook workbook = WorkbookFactory.create(fis);
		Sheet sheetname = workbook.getSheet(excelSheetName);

		// Get total rows

		int totalrows = sheetname.getLastRowNum();
		System.out.println("Total rows are " + totalrows);

		// Get total colums

		Row rowcells = sheetname.getRow(0);
		int totalcols = rowcells.getLastCellNum();
		System.out.println("Total columns are " + totalcols);
		
		
		String testdata[][]= new String[totalrows][totalcols];
		DataFormatter dataformat = new DataFormatter();
		
		for (int i=1;i<=totalrows;i++) {
			
			for (int j=0;j<totalcols;j++) {
				
				
				testdata[i-1][j]=dataformat.formatCellValue(sheetname.getRow(i).getCell(j));
				
			}
		}
		
		return testdata;

	}

}
