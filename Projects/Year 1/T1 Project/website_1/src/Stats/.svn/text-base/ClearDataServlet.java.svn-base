package Stats;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import listings.RestBean;
import login.connectionManager;


/**
 * @author <b>John</b>
 * <br><br>This method deals with clearing the search data currently stored about restaurants and dishes.<br>
 */

public class ClearDataServlet extends HttpServlet {
	
	
	private static final long serialVersionUID = 1L;
	
	//Connections made and objects created etc.
	Connection conn = null;	
	ResultSet rs = null;
	RestBean rest = new RestBean();
	
	//Two global strings
	String restId;
	String selectedRest;

	
	/**
	 * This post method detects and is run when the clearstats button is pressed,<br>
	 * it then redirects to another method.<br>
	 */
	
	@Override
	public void doPost(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException {
		
					
			String buttonPressed = request.getParameter("ButtonPressed");						

			if (buttonPressed.equals("ClearStats")) {

				DeleteRest(request, response);
			}		
			
			else {
				System.out.println("Problem with button.");
			}			
		
			
			
	}
		
	/**
	 * this method deletes the data for the restaurants and dishes.<br>
	 * <br>
	 * It is done by deleting all data in the stasdishes database, and then setting all searches to 0<br>
	 * in the statsrestaurants database, this is because if i delete everything from both, there is no<br>
	 * method implemented currently that allows you to input the restaurants after creation.<br>
	 */
	
		public void DeleteRest(HttpServletRequest request,
				HttpServletResponse response) {
			
			
			PreparedStatement deleteStats = null;
			conn = connectionManager.getConnection();
		    
		    try {			    	
		    	
		    	//Query strings
		    	String deleteDishData = "DELETE from statsdishes";
		    	String modifyRestData = "UPDATE from statsrestaurants set numOfSearches = 0";
		    	
		    	//Execute strings
				deleteStats = conn.prepareStatement(deleteDishData);
				deleteStats.executeUpdate();				
				deleteStats = conn.prepareStatement(modifyRestData);
				deleteStats.executeUpdate();				
				
				//redirect
				RequestDispatcher rd = request.getRequestDispatcher("statsPage.jsp");
				rd.forward(request, response);				
				
			}
			
			catch(Exception e) {
				e.printStackTrace();
			}		
		
	}		
}