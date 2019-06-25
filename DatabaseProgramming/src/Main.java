import java.sql.DriverManager;
import java.sql.ResultSet;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class Main {
	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver Loaded! ");
			
			Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/Students","sualeh","sualeh");
			
			
			Statement statement = (Statement) conn.createStatement();
			ResultSet resultSet = (ResultSet)	statement.executeQuery("SELECT * FROM Student");
			
			while (resultSet.next()) {
				System.out.println("ID: " + resultSet.getString(1) + " Name: " +  resultSet.getString(2));
			}
			
			conn.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
