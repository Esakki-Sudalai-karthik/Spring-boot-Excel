package com.example.ExcelDemo.ReadExcel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ReadExcel {

	public static void main(String args[]) throws IOException, ClassNotFoundException, SQLException {
		
		
		FileInputStream file = new FileInputStream(new File("C:\\Users\\sudal\\OneDrive\\Documents\\stddet.xlsx"));
		
		XSSFWorkbook wb = new XSSFWorkbook(file);
		
		XSSFSheet sheet = wb.getSheetAt(0);
		
		for(Row row : sheet)
		{
			for(Cell cell : row)
			{
				switch(cell.getCellType())
				{
				case Cell.CELL_TYPE_STRING:
					System.out.print(cell.getStringCellValue() + "\t");
					break;
				case Cell.CELL_TYPE_NUMERIC:
					System.out.print(cell.getNumericCellValue() + "\t");
				default:
					break;
				}
			}
			System.out.println("");
		}
	}
}
		
		
		
		
//		Iterator<Row> rowIterator = sheet.iterator();
//		rowIterator.next();
//		while(rowIterator.hasNext())
//		{
//			Row row = rowIterator.next();
//	
//			Iterator<Cell> cellIterator = row.cellIterator();
//					
//		while(cellIterator.hasNext())
//		{
//			Cell cell = cellIterator.next();
//			switch(cell.getCellType())
//			{
//			case Cell.CELL_TYPE_STRING:
//				cell.getStringCellValue();
////				System.out.print(cell.getStringCellValue() + "\t\t");
//				break;
//			case Cell.CELL_TYPE_NUMERIC:
//				cell.getNumericCellValue();
////				System.out.print(cell.getNumericCellValue() + "\t\t");
//				break;
//			default:	
//				break;
//			}
//		}
//		System.out.println("");
//		}

