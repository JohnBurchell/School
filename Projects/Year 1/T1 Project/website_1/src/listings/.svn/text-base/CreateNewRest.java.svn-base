package listings;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import login.connectionManager;

/**
 * @author <b>John</b>
 * <br><br>This servlet is used to create a new restaurant and is supplied data by the createRest jsp page.<br>
 * <br>
 * This post method gets the username from the session and then creates the new restaurant<br>
 * with the current logged in user as it's owner, if not otherwise stated.<br>
 * It is done with a simple sql insert statement based on the returned values from the<br> 
 * entry boxes etc on the previous jsp page.<br>
 */

@WebServlet("/CreateNewRest")
public class CreateNewRest extends HttpServlet {
		
	private static final long serialVersionUID = 1L;
		
		Connection conn = null;
		Statement stmt = null;	
		PreparedStatement createRest = null;
		
		
	@Override
	public void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		
		//Get username from session
		HttpSession session = request.getSession(true);		
		String owner = (String) session.getAttribute("username");		
		
		//Assign variables from session bean in the session.
		String restName = request.getParameter("restName");
		String restAddress = request.getParameter("restAddress");
		String restOpeningHours = request.getParameter("restOpeningHours");		
		String restDescription = request.getParameter("restDescription");
		String restType = request.getParameter("restType");	
		String restNumber = request.getParameter("restNumber");
		
		
		try { 
			
			conn = connectionManager.getConnection();			
			
			//Query
			String newRest = "insert into restaurant (restName, restAddress, restOpeningHours, restScore, restVotes, restDescription, restType, ownerUserName, restNumber) " +
			" values (?, ?, ?, 0, 0, ?, ?, ?, ?)";
			
			//Assign query to statement
			createRest = conn.prepareStatement(newRest);
			
			//Assign values
			createRest.setString(1, restName);
			createRest.setString(2, restAddress);
			createRest.setString(3, restOpeningHours);
			createRest.setString(4, restDescription);
			createRest.setString(5, restType);
			createRest.setString(6, owner);
			createRest.setString(7, restNumber);
			
			//Execute
			createRest.executeUpdate();
			
			//Close connections
			conn.close();
			createRest.close();			
			
			System.out.println("Creation completed!");
			//Add an entry into the stats DB
			insertRestaurntIntoStats(restName);
			
		}
		
		catch(Exception e) {
			System.out.println("Errors abound!");
			e.printStackTrace();
		}
	    
		RequestDispatcher rd = request.getRequestDispatcher("AdminRestServlet");
		rd.forward(request, response);
	      
		}
	
	/**
	 * This method inserts the stats required into the stats database<br>
	 * so that the restaurant can be searched against and statistics added for it<br>
	 * for the stats pages.<br>
	 */
	
	public void insertRestaurntIntoStats(String restName) {
		
		
		try { 
			
			conn = connectionManager.getConnection();			
			
			//Query
			String newRest = "insert into statsRestaurants (RestName) values (?)";
			
			//Assign query to statement
			createRest = conn.prepareStatement(newRest);
			
			//Assign values
			createRest.setString(1, restName);	
			
			//Execute
			createRest.executeUpdate();
			
			//Close connections
			conn.close();
			createRest.close();			
			
			System.out.println("Creation completed!");
			
		}
		
		catch(Exception e) {
			System.out.println("Errors abound!");
			e.printStackTrace();
		}
		
		
	}
	
}