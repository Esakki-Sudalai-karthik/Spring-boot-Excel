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
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.mysql.cj.protocol.Resultset;

public class ReadExcelDuplicates {

	public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/readexcel","root","root");
		
		Set<String> uniqueNames = new HashSet<String>();

		File f1 = new File("C:\\Users\\sudal\\OneDrive\\Documents\\stddet.xlsx");
		
		FileInputStream fis = new FileInputStream(f1);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet = wb.getSheetAt(0);
		
		
		String insertsql = "insert into stddetails (Name,Age,City) values (?,?,?)";
		String checksql = "select count(*) from stddetails where Name = ?";
		
		PreparedStatement statement = con.prepareStatement(insertsql);
		PreparedStatement statement2 = con.prepareStatement(checksql);
		

		for(int i= 1; i<=sheet.getLastRowNum();i++)
		{
			XSSFRow row = sheet.getRow(i);
			
            if (row != null)
            {	
                String Name = row.getCell(0) != null ? row.getCell(0).toString() : null;
                int Age = (int) (row.getCell(1) != null ? row.getCell(1).getNumericCellValue() : 0);
                String City = row.getCell(2) != null ? row.getCell(2).toString() : null;

                // Use HashSet to check for duplicates
                if (uniqueNames.add(Name)) 
                {
                	statement2.setString(1, Name);
                	
                	ResultSet rs = statement2.executeQuery();
                	
                	if(rs.next() && rs.getInt(1) == 0)
                		// Insert into database if the name is unique
                	{
                		statement.setString(1, Name);
                		statement.setInt(2, Age);
                		statement.setString(3, City);
                		statement.executeUpdate();
                		System.out.println("Inserted: " + Name);	
                	}
                	else 
                	{
                		System.out.println("Duplicate entry in database : " + Name);
                	}
                } 
                else
			    {
                	System.out.println("Duplicate entry in HashSet: "+Name );
			 	}
            }
        }
        System.out.println("Data processing complete.");		
	}
}
		

	
	
		

