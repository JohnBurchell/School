package listings;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import login.connectionManager;

/**
 * @author <b>John</b>
 * <br><br>This servlet is used to create a new user and is supplied data by the createUser jsp page.<br>
 * <br>
 * This doPost method again, takes user input from the previous page and<br>
 * inputs them into the SQL query to create a new user.<br>
 */

@WebServlet("/CreateNewUser")
public class CreateNewUser extends HttpServlet {
		
	private static final long serialVersionUID = 1L;
		
		Connection conn = null;
		PreparedStatement pStatement = null;
		ResultSet rs = null;
		
		
		@Override
		public void doPost(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException {	
			
			
			String ownerFirstName = request.getParameter("ownerFirstName");
			String ownerLastName = request.getParameter("ownerLastName");
			String ownerUserName = request.getParameter("ownerUserName");		
			String ownerPassword = request.getParameter("ownerPassword");
			String ownerAddress = request.getParameter("ownerAddress");		
			String ownerPhoneNumber = request.getParameter("ownerPhoneNumber");
			String ownerAccountLevel = request.getParameter("ownerAccountLevel");	
			
			try { 
				
				conn = connectionManager.getConnection();
				
				
				String QueryString = "insert into owners (ownerFirstName, ownerLastName, ownerUserName, ownerPassword, ownerAddress, ownerPhoneNumber, ownerAccountLevel) " 
						+ " values (?, ?, ?, ?, ?, ?, ?)";
				
				pStatement = conn.prepareStatement(QueryString);
				//assign Values
				pStatement.setString(1, ownerFirstName);
				pStatement.setString(2, ownerLastName);
				pStatement.setString(3, ownerUserName);
				pStatement.setString(4, ownerPassword);
				pStatement.setString(5, ownerAddress);
				pStatement.setString(6, ownerPhoneNumber);
				pStatement.setString(7, ownerAccountLevel);				
				//Execute
				pStatement.executeUpdate();
				
				System.out.println("Creation completed!");
				
			}
			
			catch(Exception e) {
				System.out.println("Errors abound!");
				e.printStackTrace();
			}
		    
			RequestDispatcher rd = request.getRequestDispatcher("administer.jsp");
			rd.forward(request, response);
		      
			}
		
		
		}
		

