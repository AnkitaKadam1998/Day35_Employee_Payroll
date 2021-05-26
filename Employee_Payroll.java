package com.day34;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Employee_Payroll {
	
	public static final String INSERT_EMPLOYEE_QUERY = "insert into employee_detail (EmpId,Name,Gender,email_id) values (?,?,?,?)";

	public static final String INSERT_SALARY_QUERY = "insert into employee_salary (Employee_id, deduction, taxable_pay, tax, net_pay) values (?,?,?,?,?)";
	
	public static void main(String args[]) throws ClassNotFoundException
	{
		Connection con = null;
		try {
		//initializing url,username and password
		String url = "jdbc:mysql://localhost:3306/employee_payroll_service";
		String userName = "root";
		String password = "Ankita@1104";
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		con = DriverManager.getConnection(url,userName,password);
		
		con.setAutoCommit(false);
		
		insertEmpDetail(con,4,"sanket","m","sanket80@gmail.com");
		insertSalaryData(con,4,10000,40000,4000,36000);

		con.commit();
		

		} catch (SQLException e) {
			e.printStackTrace();
			try {
				con.rollback();
				System.out.println("JDBC Transaction rolled back successfully");
			} catch (SQLException e1) {
				System.out.println("SQLException in rollback"+e.getMessage());
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}

}

	 private static void insertEmployeeDetail(Connection con, int EmpId, String Name,String Gender,String email_id ) throws SQLException 
	{
		PreparedStatement stmt = con.prepareStatement(INSERT_EMPLOYEE_QUERY);
		stmt.setInt(1,EmpId );
		stmt.setString(2, Name);
		stmt.setString(3,Gender);
		stmt.setString(4, email_id);
		
		stmt.executeUpdate();

		System.out.println("Employee Data inserted successfully for ID=" + EmpId);
		stmt.close();
		// TODO Auto-generated method stub
		
	}
	
	private static void insertSalaryData(Connection con, int Employee_id , int deduction , int taxable_pay, int tax, int net_pay) throws SQLException 
	{
		PreparedStatement stmt = con.prepareStatement(INSERT_SALARY_QUERY);
		stmt.setInt(1,Employee_id);
		stmt.setInt(2, deduction);
		stmt.setInt(3, taxable_pay);
		stmt.setInt(4,tax);
		stmt.setInt(5,net_pay);
		
		stmt.executeUpdate();

		System.out.println("Address Data inserted successfully for ID=" + Employee_id);
		stmt.close();
		
	}
}



