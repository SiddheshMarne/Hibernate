package com.tca;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class App {

	public static void main(String[] args)throws Exception 
	{
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String dbURL = "jdbc:mysql://localhost:3306/hfbdb";
		String user = "root";
		String password = "root";
		try 
		{

			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(dbURL, user, password);
			
			ps = con.prepareStatement("select * from student");
			rs = ps.executeQuery();
			
			System.out.println("RNO\tNAME\tPER");
			while(rs.next())
			{
				System.out.println(rs.getInt("rno") +"\t"+rs.getString("name") +"\t"+rs.getFloat("per"));
			}
			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		} finally {
			con.close();
		}
	}

}
