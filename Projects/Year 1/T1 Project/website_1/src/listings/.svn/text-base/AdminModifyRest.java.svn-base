package listings;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import login.connectionManager;

/**
 * @author <b>John</b>
 * <br><br>This servlet allows users to modify restaurants, delete and create new ones based on form inputs<br>
 * from the previous web page. The inputs from the user are taken if a new one is created, or updated<br>
 * and the database is then queried to either execute the update, or delete the selected restaurant.<br>
 * This is determined by the currently logged in user, vs their owned restaurants.<br>
 **/

@WebServlet("/AdminModifyRest")
public class AdminModifyRest extends HttpServlet {
	
	
	private static final long serialVersionUID = 1L;
	
	//Connection and other variables that the whol servlet reruire.
	Connection conn = null;	
	ResultSet rs = null;
	
	RestBean rest = new RestBean();
	Collection<RestBean> myBeans = new ArrayList<RestBean>();
	String restId;
	String selectedRest;
	
	
	/**
	 * <br><br>On post, the system should return the restaurants that the owner has access to<br>
	 * based on their username. Getting the username from the session and then populate<br>
	 * the object (restBean) with the data from an SQL query. This is then returned as stated<br>
	 * before.<br>
	 **/
	
@Override
public void doPost(HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {	
	
	myBeans.clear();
	
	//Get attribute from the session
	selectedRest = request.getParameter("restSelectedName");	
	
	populateRestInfo(selectedRest);	
    
    myBeans.add(rest);
    //Sets the object into the session.
	request.setAttribute("currentRest", myBeans);
	//Returns the object, and forwards to another page.
	RequestDispatcher rd = request.getRequestDispatcher("ModifyRest.jsp");
	rd.forward(request, response);
      
	}

/**
 * <br><br>This method populates the restaurants based on the currently logged in user<br>
 * The selected restaurant comes from the session and the currently logged in user.<br>
 **/

	public void populateRestInfo(String selectedRest) {
	
		

		try {			
			PreparedStatement getRestNames = null;
			
			conn = connectionManager.getConnection();			
			//Query
			String getRests = "select * from restaurant where restName like ?";
			//Prepare statement
			getRestNames = conn.prepareStatement(getRests);
			//Assign variable and wildcards
			getRestNames.setString(1, "%" + selectedRest + "%");
			//Execute
			rs = getRestNames.executeQuery();		
		
			//If the query returns any values, they are processed below.
			while (rs.next()) {
				
				//Setters are called and the strings are then assigned to their respective
				//locations in the respective bean.
				String restName = rs.getString("restName");
				String restAddress = rs.getString("restAddress");
				String restOpenHours = rs.getString("restOpeningHours");
				String restDescription = rs.getString("restDescription");
				String restType = rs.getString("restType");
				String ownerUserName = rs.getString("ownerUserName");	
				
				rest.setRestName(restName);
				rest.setRestAddress(restAddress);
				rest.setOpenHours(restOpenHours);
				rest.setRestDescrip(restDescription);
				rest.setRestType(restType);
				rest.setOwnerUserName(ownerUserName);
				
			}
			
			//closes connections
			conn.close();
			rs.close();
			getRestNames.close();
			
				
		
			}
		
		catch(Exception e) {
			
			e.printStackTrace();
		}
		
	}
	
	
	
	/**
	 * <br><br>The get method here is used when a button is pressed on the web page<br>
	 * which is a form, the form then takes the buttonPressed from modifyDishes<br>
	 * and depending on which button is pressed, either deletes or updates changes made<br>
	 * to the current restaurant.<br>
	 */
	
		@Override
		public void doGet(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException {
			
					
			String buttonPressed = request.getParameter("buttonPressed");
		

			if (buttonPressed.equals("Delete")) {

				DeleteRest(request, response);
			}
			else if (buttonPressed.equals("Update")){

				UpdateRest(request, response);
			}
			
			else {
				System.out.println("Problem with button.");
			}
			
			
		
			
			
	}
		
		/**
		 * <br><br>This class deals with the delete button, it simply performs an<br>
		 * SQL statement to remove the selected restaurant from the database<br>
		 * and returns to the previous web page.<br>
		 */
		
		public void DeleteRest(HttpServletRequest request,
				HttpServletResponse response) {
		    	
			PreparedStatement modifyRest = null;
				
		    try {			
				
				conn = connectionManager.getConnection();
							
				String deleteQuery = "DELETE from RESTAURANT where restName = ?";
				//Prepare
				modifyRest = conn.prepareStatement(deleteQuery);
				//Assign
				modifyRest.setString(1, selectedRest);
				//Execute
				modifyRest.executeUpdate();
				
				System.out.println("Delete completed!");
				
				//Delete entry in the stats table at the same time.
				deleteQuery = "DELETE from statsrestaurants where restName = ?";
				//Prepare
				modifyRest = conn.prepareStatement(deleteQuery);
				//Assign
				modifyRest.setString(1, selectedRest);
				//Execute
				modifyRest.executeUpdate();				
				
				conn.close();
				modifyRest.close();				
				
				RequestDispatcher rd = request.getRequestDispatcher("AdminRestServlet");
				rd.forward(request, response);
				
				
			}
			
			catch(Exception e) {
				e.printStackTrace();
			}
		    
		   
			
		}
		
		
		/**
		 * <br><br>This class deals with the update function, it takes all the attributes from the previous<br>
		 * webpage (admin dish) and then updates the values in the database<br>
		 * irregardless of if they have changed or not.<br>
		 */
		
		public void UpdateRest(HttpServletRequest request,
				HttpServletResponse response) {		
			
			
			PreparedStatement modifyRest = null;
			
			//Values taken from the previous page.
			String updateRestName = request.getParameter("restName");
			String updateRestAddress = request.getParameter("restAddress");
			String updateOpenHours = request.getParameter("restOpeningHours");		
			String updateRestDescrip = request.getParameter("restDescription");
			String updateRestType = request.getParameter("restType");			
			

			try {			
				
				
				conn = connectionManager.getConnection();		
				
				//Query
				String updateRestString = "UPDATE restaurant set restName = ?, restAddress = ?, restOpeningHours = ?, restDescription = ?, restType = ? where RestName = ?";
				//Assign the values
				modifyRest = conn.prepareStatement(updateRestString);
				modifyRest.setString(1, updateRestName);
				modifyRest.setString(2, updateRestAddress);
				modifyRest.setString(3, updateOpenHours);
				modifyRest.setString(4, updateRestDescrip);
				modifyRest.setString(5, updateRestType);
				modifyRest.setString(6, selectedRest);
				modifyRest.executeUpdate();
				
				System.out.println("Update completed!");
				
				conn.close();
				modifyRest.close();	
				//Dishpatches the user back to the servlet
				RequestDispatcher rd = request.getRequestDispatcher("AdminRestServlet");
				rd.forward(request, response);
				
				
			}
			
			catch(Exception e) {
				e.printStackTrace();
			}
		}


}
