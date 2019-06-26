import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;

public class MyServlet extends HttpServlet {

	private static final long serialVersionUID = -5281450407514571526L;

	private PreparedStatement preparedStatement;
	@Override
	public void init() throws ServletException {
		// connection
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver Loaded! ");

			java.sql.Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/Students",
					"sualeh", "sualeh");

			preparedStatement = conn
					.prepareStatement("INSERT INTO `Student` (`StudentID`,`StudentName`) VALUES (?,?) ");

//			conn.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = req.getParameter("studentID");
		String name = req.getParameter("studentName");

		try {
			preparedStatement.setString(1, id);
			preparedStatement.setString(2, name);
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

		resp.setContentType("text/html");
		PrintWriter pw = resp.getWriter();

		System.out.println(id + " ");
		pw.println("Student add with ID: " + id + " and name was " + name);
	}
}
