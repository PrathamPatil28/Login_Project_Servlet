package login;

import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


@WebServlet("/user")
public class user extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
    public static String Load_Driver = "com.mysql.cj.jdbc.Driver";
    public static String URL = "jdbc:mysql://localhost:3306/userdb";
    public static String Password = "root";
    public static String UserName = "root";
    Connection connection;
   
    public user() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uname = request.getParameter("uname");
        String upass = request.getParameter("upass");

        try {
            // Check user credentials
            PreparedStatement pStatement = connection.prepareStatement("SELECT * FROM uinfo WHERE uname = ? AND password = ?");
            pStatement.setString(1, uname);
            pStatement.setString(2, upass); // Assuming you want to check for password as well
            ResultSet rs = pStatement.executeQuery();

            if (rs.next()) {
                // Redirect to the user data page with username as a query parameter
                response.sendRedirect("user.html?username=" + uname);
            } else {
                PrintWriter pw = response.getWriter();
                pw.println("<html><body bgcolor=black text=white><center>");
                pw.println("<h2>Invalid user</h2>");
                pw.println("<a href='login.html'>Try Again</a>");
                pw.println("</center></body></html>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
