package com.example.ExcelDemo.ReadExceltoMySql;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook; 

public class ExceltoMysqlDemo {

	public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/readexcel","root","root");
		
		Set<String> unique = new HashSet<String>();

		File f1 = new File("C:\\Users\\sudal\\OneDrive\\Documents\\stddet.xlsx");
		
		FileInputStream fis = new FileInputStream(f1);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet = wb.getSheetAt(0);
				
		String sql = "insert into stddet (Name,Age,City) values (?,?,?)";
		
		PreparedStatement statement = con.prepareStatement(sql);
		
		Iterator<Row> rowIterator = sheet.iterator();
		
		rowIterator.next();				
		while(rowIterator.hasNext())
		{
			Row row = rowIterator.next();
			Iterator<Cell> cellIterator = row.cellIterator();
		int index = 1;
		while(cellIterator.hasNext())
		{	
			Cell cell = cellIterator.next();
			switch (cell.getCellType())
			{
				case Cell.CELL_TYPE_STRING: 
					System.out.print(cell.getStringCellValue() + "\t\t");
					statement.setString(index++, cell.getStringCellValue());
					break;
				case Cell.CELL_TYPE_NUMERIC:
					System.out.print(cell.getNumericCellValue() + "\t\t");
					statement.setDouble(index++, cell.getNumericCellValue());
					break;
				default:
					break;
			}	
		}
		
		System.out.println("");
		statement.execute();
		}
		System.out.println("Data inserted.");
		
		
		
//		for(Row row : sheet)
//		{
//			
//			if (row.getRowNum() == 0) continue;
//		
//			String name = row.getCell(0).getStringCellValue();
//			int Age = (int)row.getCell(1).getNumericCellValue();
//			String City = row.getCell(2).getStringCellValue();
//			
//			statement.setString(1, name);
//			statement.setDouble(2, Age);
//			statement.setString(3, City);
//			statement.execute();
//		}
//		System.out.println("Data Inserted");
		
	}

}







