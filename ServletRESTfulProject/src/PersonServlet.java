
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.mysql.jdbc.Connection;

/**
 * Servlet implementation class PersonServlet
 */
public class PersonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PreparedStatement insertPreparedStatement;
	private PreparedStatement selectPreparedStatement;
	private PreparedStatement deletePreparedStatement;
	private PreparedStatement updatePreparedStatement;
	java.sql.Connection conn;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PersonServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// establish connection

		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver Loaded! ");

			conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/Persons", "sualeh", "sualeh");

			insertPreparedStatement = conn
					.prepareStatement("INSERT INTO `Person` (`name`,`about`,`birthYear`) VALUES (?,?,?) ");
			selectPreparedStatement = conn.prepareStatement("SELECT * FROM  `Person` WHERE `name` = ? ");
			updatePreparedStatement = conn
					.prepareStatement("UPDATE `Person` SET `about` = ? , `birthYear` = ? WHERE `name` = ? ");
			deletePreparedStatement = conn.prepareStatement("DELETE FROM `Person` WHERE `name` = ?");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		try {
//			selectPreparedStatement = conn
//					.prepareStatement("SELECT * FROM  `Person` WHERE `name` = ? ");
//		} catch (SQLException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		
//		System.out.println("Prepared Statment");
//		
		String requestUrl = request.getRequestURI();
		String name = requestUrl.substring("/ServletRESTfulProject/people/".length());
		System.out.println("Name i got was " + name + " " + requestUrl);

		try {
			selectPreparedStatement.setString(1, name);
		} catch (SQLException e) {
			System.out.println("Not found!");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ResultSet rs;
		try {
			rs = selectPreparedStatement.executeQuery();
			if (rs.next()) {

				String about = rs.getString("about");
				if (about == null) {
					System.out.println("About Not found!");
				}
				String year = rs.getString("birthYear");

				String json = "{\n";
				json += "\"name\": " + JSONObject.quote(name) + ",\n";
				json += "\"about\": " + JSONObject.quote(about) + ",\n";
				json += "\"birthYear\": " + JSONObject.quote(year) + "\n";
				json += "}";
				response.getOutputStream().println(json);
			} else {
				System.out.println("Not found!");
				response.getOutputStream().println("{}");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.getOutputStream().println("{}");
		}

	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String requestUrl = req.getRequestURI();
		String name = requestUrl.substring("/ServletRESTfulProject/people/".length());

		String about = req.getParameter("about");
		String birthYear = req.getParameter("birthYear");

		System.out.println(about + " " + birthYear);
		System.out.println("Name was " + name);

		synchronized (updatePreparedStatement) {

			try {
				updatePreparedStatement.clearParameters();
			} catch (SQLException e1) {

				System.out.println("Unable to clear parameters");
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			try {
				updatePreparedStatement.setString(1, about);
				updatePreparedStatement.setString(2, birthYear);
				updatePreparedStatement.setString(3, name);

				int count = updatePreparedStatement.executeUpdate();
				if (count != 1)
					resp.getOutputStream().println("Unable to update");
				else {
					resp.getOutputStream().println("Updated " + count + " values !");
				}
			} catch (SQLException e) {
				System.out.println("Query is wrong");
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String requestUrl = req.getRequestURI();
		String name = requestUrl.substring("/ServletRESTfulProject/people/".length());
		System.out.println("Name was " + name);

		synchronized (deletePreparedStatement) {
			try {
				deletePreparedStatement.clearParameters();
				deletePreparedStatement.setString(1, name);

				int count = deletePreparedStatement.executeUpdate();
				if (count != 1)
					resp.getOutputStream().println("Unable to delete");
				else
					resp.getOutputStream().println("Deleted " + count + " values !");
			} catch (SQLException e) {
				System.out.println("Query is wrong");
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

//	      Enumeration<String> paramNames = request.getParameterNames();
//	      
//	      while(paramNames.hasMoreElements()) {
//	         String paramName = (String)paramNames.nextElement();
//	         System.out.println(paramName);
//	         String[] paramValues = request.getParameterValues(paramName);
//
//	         // Read single valued data
//	         if (paramValues.length == 1) {
//	            String paramValue = paramValues[0];
//	            if (paramValue.length() == 0)
//	               System.out.println("<i>No Value</i>");
//	               else
//	               System.out.println(paramValue);
//	         } else {
//	            // Read multiple valued data
//	            System.out.println("<ul>");
//
//	            for(int i = 0; i < paramValues.length; i++) {
//	               System.out.println(" " + paramValues[i]);
//	            }
//	         }
//	      }

		String name = request.getParameter("name");
		String about = request.getParameter("about");
		String birthYear = request.getParameter("birthYear");

		synchronized (insertPreparedStatement) {

			try {
				insertPreparedStatement.clearParameters();

				insertPreparedStatement.setString(1, name);
				insertPreparedStatement.setString(2, about);
				insertPreparedStatement.setString(3, birthYear);

				insertPreparedStatement.execute();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

}
