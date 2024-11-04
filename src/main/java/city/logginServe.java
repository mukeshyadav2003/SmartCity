package city;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class logginServe
 */
@WebServlet("/logginServe")
public class logginServe extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public logginServe() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		  PrintWriter out = response.getWriter();
		  
		   String url="jdbc:mysql://localhost:3306/logggin";  
	       String uname="root";
	       String pword="mukeshyadav";
	     
	       
	       String sql="select * from main where username=? and password=?";
	       
	   	String user=request.getParameter("user");
		String password=request.getParameter("password");
				try {
					Connection con= DriverManager.getConnection(url,uname,pword);
				
					PreparedStatement ps= con.prepareStatement(sql);
					ps.setString(1, user);
					ps.setString(2, password);
					ResultSet rs=ps.executeQuery();
					
					
					if(rs.next()) {
						RequestDispatcher rd= request.getRequestDispatcher("location.jsp");
						rd.forward(request,response);
					}
					
					else {
						 out.println("<h1> wrong password </h1>");
					}
					
					    rs.close(); // Close ResultSet
				        ps.close(); // Close PreparedStatement
				} catch (SQLException e) {
			
					e.printStackTrace();
				}
	}

}
