package practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class ExecuteUpdateWithTryCatch {
	public static void main(String[] args) throws SQLException {
		Connection con=null;
		try {
		//Register the driver
		Driver driver = new Driver();
		DriverManager.registerDriver(driver);
		
		//get connection
		 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employeedb","root","root");
		
		//issue create statement
		Statement state = con.createStatement();
		
		//execute a querry
		
		int result = state.executeUpdate("insert into employeeinfo1 values(3,'jay','Birpur');");
		
		if(result==1) 
		{
			System.out.println("data added successfully");
		}
		else 
		{
			System.out.println("error");
		}}
		catch(Exception e) {
			
		}
			finally {
				con.close();
				System.out.println("done");
				
			}
		}
	}

		
	




