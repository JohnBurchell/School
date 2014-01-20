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

import login.connectionManager;

/**
 * @author <b>John</b>
 * <br><br>This servlet deals with dishes. It allows a user to modify, delete and update dishes selected from<br>
 * the previous webpage (modify dishes). users simply make changes to the dishes in a web form from<br>
 * the appropiate jsp page, where the values are taken from the page and input into the database as new,<br>
 * update dishes, or even removed.<br>
 */

@WebServlet("/AdminDishModify")
public class AdminDishModify extends HttpServlet {
	
	
	private static final long serialVersionUID = 1L;
	
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	DishBean dish = new DishBean();
	Collection<DishBean> myBeans = new ArrayList<DishBean>();
	String dishId;
	
	
	/**
	 * <br><br>The post method here is used when a post call is made by the form to modify a selected dish.<br>
	 * The dish name is grabbed from the form and then input into another method populateDishInfo(string)<br>
	 * where the dish information is populated. Once populated the servlet returns back to the modify dishes<br>
	 * page.<br>
	 */
	
@Override
public void doPost(HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {
	
	
	myBeans.clear();		
	String selectedDish = request.getParameter("dishSelectedName");	
	populateDishInfo(selectedDish);	    
    myBeans.add(dish);    
	request.setAttribute("currentDish", myBeans);	
	RequestDispatcher rd = request.getRequestDispatcher("ModifyDish.jsp");
	rd.forward(request, response);
      
	}

/**
 * <br><br>This method populates the dishes information into the dishes bean<br>
 * This is done by querying the database based on the selected dishe from the modify<br>
 * dishes page. This is passed into the class and is then used in the query<br>
 * The query returns all dish information based on the selected dish and adds<br>
 * it to a new dish bean object, which is then stored in a collection of dishbean type<br>
 * which is then added to the session scope to be used / displayed in another web page.<br>
 **/

	public void populateDishInfo(String selectedDish) {
		
		
		PreparedStatement getDishes = null;

		try {			
		
			conn = connectionManager.getConnection();			
			
			String queryDishes = "select * from dishes where dishName = ?";
			
			getDishes = conn.prepareStatement(queryDishes);
			getDishes.setString(1, selectedDish);
			rs = getDishes.executeQuery();		
			
			if (rs.next()) {
				
				String dishName = rs.getString("DishName");
				dishId = rs.getString("DishID");
				String restName = rs.getString("RestName");
				String dishPrice = rs.getString("DishPrice");
				String dishType = rs.getString("DishType");
				String dishDescription = rs.getString("DishDescription");
				String dishContains = rs.getString("DishContains");
				String dishCuisine = rs.getString("DishCuisine");				
				
				dish.setDishName(dishName);
				dish.setDishId(dishId);
				dish.setRestName(restName);
				dish.setDishPrice(dishPrice);
				dish.setDishType(dishType);
				dish.setDishDesc(dishDescription);
				dish.setDishContains(dishContains);
				dish.setDishCuisine(dishCuisine);
				
			}
			conn.close();
			rs.close();
			
				
		
			}
		catch(Exception e) {
			
			e.printStackTrace();
		}
		
	}	
	
	
	/**
	 * <br><br>The get method is called when a particular button is pressed in the modify page<br>
	 * If delete is pressed, then the deleteDish method is called, the same applies to update<br>
	 */
	
		@Override
		public void doGet(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException {
			
			
						
			String buttonPressed = request.getParameter("buttonPressed");				
			
			if (buttonPressed.equals("DeleteDish")) {
				
				DeleteDish(request, response);
			}
			else if (buttonPressed.equals("UpdateDish")){
			
				UpdateDish(request, response);
			}
			
			else {
				System.out.println("Problem with button.");
			}
			
			
			
	}
		
		/**
		 * <br><br>The dish is deleted depending upon selection. A simple delete statement is sent<br>
		 * to the database to remove the dish based on it's ID. The id is obtained from the dishBean<br>
		 * which stores all the info about the dish.<br>
		 */
		
		public void DeleteDish(HttpServletRequest request,
				HttpServletResponse response) {			
			
		    
			PreparedStatement deleteDishes = null;
			
		    try {			
				
				conn = connectionManager.getConnection();
				
				String deleteDishString = "DELETE from DISHES where dishId = ?";
				
				//Create statement
				deleteDishes = conn.prepareStatement(deleteDishString);
				//Set variables
				deleteDishes.setString(1, dishId);
				//Execute delete
				deleteDishes.executeUpdate();				
				
				System.out.println("Delete completed!");				
				
				RequestDispatcher rd = request.getRequestDispatcher("AdminDishServlet");
				rd.forward(request, response);
				
				
			}
			
			catch(Exception e) {
				e.printStackTrace();
			}
			
		}
		
		
		/**
		 * <br><br>This updates the dish with all current information in the dish web page.<br>
		 * It simply sends an update query to the database which is then executed and<br>
		 * updates everything in the database basesd on what is in the web page.<br>
		 */
		
		public void UpdateDish(HttpServletRequest request,
				HttpServletResponse response) {	
			
			
			PreparedStatement updateDishes = null;
		
			String updateDishName = request.getParameter("dishName");
			String updateDishPrice = request.getParameter("dishPrice");
			String updateDishType = request.getParameter("dishType");		
			String updateDishDescription = request.getParameter("dishDescription");
			String updateDishCuisine = request.getParameter("dishCuisine");		
			String updateDishContains = request.getParameter("dishContains");
			

			try {			
			
				conn = connectionManager.getConnection();			
			
				String updateString = "UPDATE dishes set dishName = ?, dishPrice = ?, dishType = ?, dishDescription = ?, dishCuisine = ?," +
						"dishContains = ? where dishId = ? ";
				
				//Prepare statement
				updateDishes = conn.prepareStatement(updateString);
				//Set variables
				updateDishes.setString(1, updateDishName);
				updateDishes.setString(2, updateDishPrice);
				updateDishes.setString(3, updateDishType);
				updateDishes.setString(4, updateDishDescription);
				updateDishes.setString(5, updateDishCuisine);
				updateDishes.setString(6, updateDishContains);	
				updateDishes.setString(7, dishId);
				//Do update
				updateDishes.executeUpdate();
				
				System.out.println("Update completed!");
				
				
				RequestDispatcher rd = request.getRequestDispatcher("AdminDishServlet");
				rd.forward(request, response);
				
				
			}
			
			catch(Exception e) {
				e.printStackTrace();
			}
		}


}
