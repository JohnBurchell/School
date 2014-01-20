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
import javax.servlet.http.HttpSession;

import login.connectionManager;

/**
 * @author <b>John</b>
 * <br><br>This servlet is used to create a new dish and is supplied data by the createDish jsp page.<br>
 * <br>
 * The doPost method handles getting the information from the input forms on the create dish page<br>
 * and then it assigns these to strings, with these strings i insert them into the query statement<br>
 * and create a new dish<br>
 * <br>
 * To find the restaurant the dish belongs to, another method is called which returns the restaurant name.<br>
 */

@WebServlet("/CreateNewDish")
public class CreateNewDish extends HttpServlet {
		
	private static final long serialVersionUID = 1L;
		
		Connection conn = null;		
		ResultSet rs = null;		
		
	@Override
	public void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		
		String restName = getRestName(request);		
		
		String dishName = request.getParameter("dishName");
		String dishPrice = request.getParameter("dishPrice");
		String dishType = request.getParameter("dishType");		
		String dishDescription = request.getParameter("dishDescription");
		String dishCuisine = request.getParameter("dishCuisine");		
		String dishContains = request.getParameter("dishContains");

		
		try { 
			
			//SQL statement and input of values
			//For creation of new dish
			PreparedStatement createNewDish = null;			
			conn = connectionManager.getConnection();			
			
			String createNewDishString = "insert into dishes (dishId, dishName, dishPrice, dishType, dishDescription, dishCuisine, dishContains, restName) " 
					+ " values (null, ?, ?, ?, ?, ?, ?, ?)";
			
			createNewDish = conn.prepareStatement(createNewDishString);
			createNewDish.setString(1, dishName);
			createNewDish.setString(2, dishPrice);
			createNewDish.setString(3, dishType);
			createNewDish.setString(4, dishDescription);
			createNewDish.setString(5, dishCuisine);
			createNewDish.setString(6, dishContains);
			createNewDish.setString(7, restName);
			createNewDish.executeUpdate();
			
			System.out.println("Creation completed!");
			createNewDish.close();
			conn.close();
			
		}
		
		
		
		
		catch(Exception e) {
			System.out.println("Errors abound!");
			e.printStackTrace();
		}	    
	
		RequestDispatcher rd = request.getRequestDispatcher("AdminDishServlet");
		rd.forward(request, response);
	      
		}
	
	/**
	 * This method finds the restaurant that the dish belongs to and returns a string<br>
	 * of that restaurants name.<br>
	 * <br>
	 * It returns that restaurant name as a string.<br>
	 */
	
	public String getRestName(HttpServletRequest request) {
		
		
		HttpSession session = request.getSession(true);
		
		String owner = (String) session.getAttribute("username");
		String restName;
		
		try {
			
			PreparedStatement getRestaurant = null;
			
			conn = connectionManager.getConnection();
			
			String getRestNameString = "select RestName from restaurant where ownerUserName = ?";	
			
			getRestaurant = conn.prepareStatement(getRestNameString);
			getRestaurant.setString(1, owner);
			rs = getRestaurant.executeQuery();
			
			System.out.println("query executed");
			
			
	
			if (rs.next()) {				
				restName = rs.getString("RestName");
				return restName;
			}
		
			else {
			
				System.out.println("Nothing in the result set.");
				}
			
			conn.close();
			getRestaurant.close();
			
			}
		
		
		catch (Exception e) {
			System.out.println("error with SQL most likely");
			e.printStackTrace();
		}
		
		return restName = "";
	}
	
	

}
