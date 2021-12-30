package practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class SampleJDBCExcuteQuery {

	public static void main(String[] args) throws SQLException {
		
		/*step 1: Register the Driver*/
		Driver driver=new Driver();
		DriverManager.registerDriver(driver);
		
		/*Step 2: Get connection with database-provide db name*/
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employeedb","root","root");
		
		/*Step 3: Issue create statement*/
		Statement state = con.createStatement();
		
		/*Step 4: execute a querry - provide table name*/
		
		ResultSet result = state.executeQuery("select * from employeeinfo1;");
		
		while(result.next()) {
			System.out.println(result.getString(2)+" "+result.getString(3));
			
		}
		/*Step 5: close the database*/
		con.close();
	}

}
