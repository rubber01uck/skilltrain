package pro;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ui {
	
	static public Connection con;
	static public ResultSet rs;
	static public Statement st;
	
	public ui () {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/skilltrain?allowLoadLocalInfile=true&serverTimezone=UTC","root","1234");
			st=con.createStatement();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
