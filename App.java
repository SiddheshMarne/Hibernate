package com.tca;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class App {

	public static void main(String[] args) throws Exception {

		Connection con = null;
		PreparedStatement ps = null;
		String dbURL = "jdbc:mysql://localhost:3306/hfbdb";
		String user = "root";
		String password = "root";
		Scanner sc = new Scanner(System.in);

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(dbURL, user, password);

			/*
			 * System.out.println("Enter the Roll Number :"); int rno = sc.nextInt();
			 * System.out.println("Enter Name"); String name = sc.next();
			 * System.out.println("Enter the Per : "); double per = sc.nextDouble();
			 */
			File f = new File("Student.txt");
			if (!f.exists()) {
				System.out.println("File does not exists");
				System.exit(0);
			}
			if (!f.isFile()) {
				System.out.println("Not a file");
				System.exit(0);
			}
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			String str = "";
			int sval = 0;
			ps = con.prepareStatement("insert into student values(?,?,?)");
			while (true) {
				str = br.readLine();

				if (str == null) {
					break;
				}
				String[] arrOfStr = str.split(",");
				System.out.println(str);

				ps.setInt(1, Integer.parseInt(arrOfStr[0]));
				ps.setString(2, arrOfStr[1]);
				ps.setDouble(3, Double.parseDouble(arrOfStr[2]));
				sval = ps.executeUpdate();

			}
			fr.close();

			if (sval == 1) {
				System.out.println("Records added");

			} else {
				System.out.println("Unable to Insert");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.close();
		}

	}

}
