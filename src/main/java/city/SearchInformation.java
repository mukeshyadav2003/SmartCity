package city;

import java.io.IOException;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




@WebServlet("/SearchInformation")
public class SearchInformation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public SearchInformation() {
        super();
 
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String url = "jdbc:mysql://localhost:3306/smart_city";
		    String user = "root";
		    String password = "mukeshyadav";

		    String service = request.getParameter("Businesssearch");
		    String location = request.getParameter("Locationsearch");

		    // Use PreparedStatement to avoid SQL injection
		    String sql = "SELECT * FROM business_data WHERE service = ? AND city = ?";

		    response.setContentType("text/html");
		    PrintWriter out = response.getWriter();

		    try (Connection con = DriverManager.getConnection(url, user, password);
		         PreparedStatement ps = con.prepareStatement(sql)) {
		        
		        ps.setString(1, service);
		        ps.setString(2, location);
		        
		        ResultSet rs = ps.executeQuery();
		        ResultSetMetaData rsmd = rs.getMetaData();
		        int columnCount = rsmd.getColumnCount();
		        
		        if (rs.next()) {
		            // Output column names
		            for (int i = 1; i <= columnCount; i++) {
		                out.println("<h1>" + rsmd.getColumnName(i) + ": " + rs.getString(i) + "</h1>");
		            }
		        } else {
		            out.println("<h1>No results found.</h1>");
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		        out.println("<h1>Error: " + e.getMessage() + "</h1>");
		    } finally {
		        out.close(); // Ensure the PrintWriter is closed
		    }
		    doGet(request, response);
		}
		
	

}
