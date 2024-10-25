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
import java.sql.ResultSet;
import java.sql.SQLException;


@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	  public  static String  Load_Driver = "com.mysql.cj.jdbc.Driver";

	    public  static String  URL = "jdbc:mysql://localhost:3306/userdb";

	    public static  String Password = "root";

	    public static  String UserName = "root";
	    
	    Connection connection;
    
    public login() {
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
		
	   String uname= request.getParameter("uname");
	   String upass= request.getParameter("upass");
	   
	   
	   try {
		PreparedStatement pStatement = connection.prepareStatement("select * from uinfo where uname=?");
		
		pStatement.setString(1, uname);
		ResultSet rs=pStatement.executeQuery();
		
		PrintWriter pw=response.getWriter();
		
		pw.println("<html> <body bgcolor=black text=white> <center>");
		
		if(rs.next()) {
			pw.println("welcome" + uname);
			
		}else {
			pw.println("invalid user");
		}
		
		pw.println("</center></body></html>");
	} catch (SQLException e) {
		
		e.printStackTrace();
	}
	}
	
	


}
