package login;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/reg")
public class register extends HttpServlet {
	
  
	private static final long serialVersionUID = 1L;
	 public  static String  Load_Driver = "com.mysql.cj.jdbc.Driver";

	    public  static String  URL = "jdbc:mysql://localhost:3306/userdb";

	    public static  String Password = "root";

	    public static  String UserName = "root";
	    
	    Connection connection;


	public register() {
        super();
        
    }

	
	public void init(ServletConfig config) throws ServletException {
		   try {
				 connection = DriverManager.getConnection(URL , UserName ,Password);
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fname =request.getParameter("fname");
		String flast = request.getParameter("flast");
		String uname = request.getParameter("uname");
		String upass = request.getParameter("upass");
		
		try {
			PreparedStatement ps=connection.prepareStatement("insert into uinfo values (?,?,?,?)");
			
			ps.setString(1, fname);
			ps.setString(2, flast);
			ps.setString(3, uname);
			ps.setString(4, upass);
			
			ps.executeUpdate();
			
			PrintWriter pw =response.getWriter();
			
			pw.println("<html><body bgcolor=black text=white> <center>");
			pw.println("<h2>Data Register Successfully..</h2>");
			pw.println(" <a href=Login.html>Login</a>");
			pw.println("</center></body></html>");
			
//			 response.sendRedirect("Login.html");
			
		} catch (SQLException e) {
//			 PrintWriter pw = response.getWriter();
//	            pw.println("<html><body bgcolor=black text=white> <center>");
//	            pw.println("<h2>Error occurred during registration. Please try again.</h2>");
//	            pw.println("<a href='register.html'>Go Back to Registration</a>");
//	            pw.println("</center></body></html>");
			
			e.printStackTrace();
		}
		
	}

}
