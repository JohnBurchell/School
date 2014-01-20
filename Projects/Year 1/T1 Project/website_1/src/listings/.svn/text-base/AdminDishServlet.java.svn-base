package listings;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
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
 * <br><br>This servlet deals with the Dishes. It returns an collection object of the dishBean type, and puts it<br>
 * into the session profile. It queries the server for all the current logged in users<br>
 * dishes (username) and returns them back to the webpage.<br>
 */

@WebServlet("/AdminDishServlet")
public class AdminDishServlet extends HttpServlet {
	
	
	private static final long serialVersionUID = 1L;
	
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	
	String currentRestaurant, currentOwner, url;	
	
	/**
	 * <br><br>The get request simply returns all restaurants that the current user (determined by username)<br>
	 * has access to, which in most cases is usually one, however can be multiple.<br>
	 */
	
	@Override
	public void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
			
		Collection<DishBean> myBeans = new ArrayList<DishBean>();	
		
		try {			
			
			//Create prepared statements
			PreparedStatement getQueries = null;
			
			
			//Get data from session
			HttpSession session = request.getSession(true);			
			currentOwner = (String) session.getAttribute("username");
			
			String getRests = "select RestName from restaurant where ownerUserName = ?";
			//Get connection values etc.
			conn = connectionManager.getConnection();
			getQueries = conn.prepareStatement(getRests);
			//Insert query value
			getQueries.setString(1, currentOwner);
			//execute
			rs = getQueries.executeQuery();
					
		
			if (rs.next()) {				
				currentRestaurant = rs.getString("RestName");
			
			}
			
			else {
				
				System.out.println("Nothing in the result set.");
			}
			
			//New query
			String dishQuery = "Select * from dishes where restName like ?";
			//Prepare query
			getQueries = conn.prepareStatement(dishQuery);
			//Assign Variable
			getQueries.setString(1, "%" + currentRestaurant + "%");	
			//Execute
			rs = getQueries.executeQuery();
			
			//While the result set contains something
		while (rs.next()) {				
			
			//Set the strings to the values returned in the sql
			String dishName = rs.getString("DishName");
			String dishId = rs.getString("DishID");
			String restName = rs.getString("RestName");
			String dishPrice = rs.getString("DishPrice");
			String dishType = rs.getString("DishType");
			String dishDescription = rs.getString("DishDescription");
			String dishContains = rs.getString("DishContains");
			String dishCuisine = rs.getString("DishCuisine");
			
			DishBean dish = new DishBean();
						
			//Use setters to put them into the new bean object
			dish.setDishName(dishName);
			dish.setDishId(dishId);
			dish.setRestName(restName);
			dish.setDishPrice(dishPrice);
			dish.setDishType(dishType);
			dish.setDishDesc(dishDescription);
			dish.setDishContains(dishContains);
			dish.setDishCuisine(dishCuisine);
			
			//Add to the collection
			myBeans.add(dish);
			
			}
		
		//close things
		conn.close();
		rs.close();		
		
		//Add object to session and forward to new page.
		request.setAttribute("dishObj", myBeans);
		RequestDispatcher rd = request.getRequestDispatcher("adminDishes.jsp");
		rd.forward(request, response);

		}
		
		catch (Exception e) {
			System.out.println("error probably with the SQL");
			e.printStackTrace();
		}
		
	}
	
	/**
	 * <br><br>Bad design here, this resolves back to the doGet because i was using post.<br>
	 * I could simply change it to get manually but for now, it's not an issue,<br>
	 * especialy as post is more secure than get.<br>
	 */
	
	@Override
	public void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		
		//Bad design i know, just better to use POST than get however.
		doGet(request, response);
	}
}
	
	

