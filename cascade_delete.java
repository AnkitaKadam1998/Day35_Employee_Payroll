package com.day34;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DeleteEmployee {
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException   {
		
		String url = "jdbc:mysql://localhost:3306/employee_payroll_service";
		String userName = "root";
		String password = "Ankita@1104";
		
		//query to delete contact by contact id using cascade delete by setting foreign key
		String query = "delete from emp_details where Emp_id = 1";
		//loading and registering driver
		Class.forName("com.mysql.cj.jdbc.Driver");
		//establishing connect with url username and password
		Connection con = DriverManager.getConnection(url,userName,password);
		//creating a statement
		Statement st = con.createStatement();
		//updating the query
		int count = st.executeUpdate(query);
		
		System.out.println(count + "rows affected");
		//closing statement and connection
		st.close();
		con.close();
		
	}
}
