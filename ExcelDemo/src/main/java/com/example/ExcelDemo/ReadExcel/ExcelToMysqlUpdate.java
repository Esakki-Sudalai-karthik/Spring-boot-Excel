package com.example.ExcelDemo.ReadExcel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelToMysqlUpdate {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/readexcel","root","root");
		
//		Set<String> uniqueNames = new HashSet<String>();

		File f1 = new File("C:\\Users\\sudal\\OneDrive\\Documents\\stddet.xlsx");
		
		FileInputStream fis = new FileInputStream(f1);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet = wb.getSheetAt(0);
		
		
		String insertsql = "INSERT into stddet (Name,Age,City) values (?,?,?)";
		String updatesql = "UPDATE stddet SET Age = ?, City = ? WHERE Name = ?";
		String checksql = "SELECT count(*) from stddet WHERE Name = ?";
		
		PreparedStatement insertStmt = con.prepareStatement(insertsql);
		PreparedStatement updateStmt = con.prepareStatement(updatesql);
		PreparedStatement CheckStmt = con.prepareStatement(checksql);
		
		for(int i = 1; i<=sheet.getLastRowNum(); i++) 
		{
			XSSFRow row = sheet.getRow(i);
			
			if(row != null)
			{
				String Name = row.getCell(0) != null ? row.getCell(0).toString() : null;
				int Age = (int) (row.getCell(1) != null ? row.getCell(1).getNumericCellValue() : 0);
				String City = row.getCell(2) != null ? row.getCell(2).toString() : null;
				
				//check the name if already exists
				CheckStmt.setString(1, Name);
				ResultSet rs = CheckStmt.executeQuery();
						
				if(rs.next() && rs.getInt(1) > 0)
				{
					//check the name if already exists and update the name field 
					updateStmt.setInt(1, Age);
					updateStmt.setString(2, City);
					updateStmt.setString(3, Name);
					updateStmt.executeUpdate();
					System.out.println("Updated : "+Name);
				}
				else
				{
					//insert the new record
					insertStmt.setString(1, Name);
					insertStmt.setInt(2, Age);
					insertStmt.setString(3, City);
					insertStmt.executeUpdate();
					System.out.println("Inserted : "+Name);
				}
			}
		}
		System.out.println("Data processing complete.");
	}
}
