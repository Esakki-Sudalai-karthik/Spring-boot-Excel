package com.example.ExcelDemo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ExcelDemoApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(ExcelDemoApplication.class, args);
		
//		FileInputStream file = new FileInputStream(new File("C:\\Users\\sudal\\OneDrive\\Documents\\Student.xlsx"));
//		
//		XSSFWorkbook wb = new XSSFWorkbook(file);
//		
//		XSSFSheet sheet = wb.getSheetAt(0);
//		
//		Iterator<Row> itr = sheet.iterator();
//		
//		while(itr.hasNext())
//		{
//			Row row = itr.next();
//			Iterator<Cell> cellIterator = row.cellIterator();
//		while(cellIterator.hasNext())
//		{
//			Cell cell = cellIterator.next();
//			switch(cell.getCellType())
//			{
//			case Cell.CELL_TYPE_STRING:
//				System.out.print(cell.getStringCellValue() + "\t\t\t");
//				break;
//			case Cell.CELL_TYPE_NUMERIC:
//				System.out.print(cell.getNumericCellValue() + "\t\t\t");
//				break;
//			default:	
//			}
//		}
//		System.out.println("");
//		}
		
		
	}

}
