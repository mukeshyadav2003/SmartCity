package city;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Businformation")
public class Businformation extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Businformation() {
      
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url="jdbc:mysql://localhost:3306/smart_city";  
	    String uname="root";
	    String pword="mukeshyadav";
		
	    String bname =request.getParameter("businessname");
    	String service=request.getParameter("service");
        String city=request.getParameter("city");
        String address=request.getParameter("address");
        String phoneno=request.getParameter("phoneno");
        
	    try {
	    	String sql="insert into business_data values(?,?,?,?,?)";
	    	
			Connection con=DriverManager.getConnection(url,uname,pword);
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, bname);
			ps.setString(2, service);
			ps.setString(3, city);
			ps.setString(4, address);
			ps.setString(5, phoneno);
			
			ps.executeUpdate();
			
			System.out.println("Data Insert Succefully");
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}

}
