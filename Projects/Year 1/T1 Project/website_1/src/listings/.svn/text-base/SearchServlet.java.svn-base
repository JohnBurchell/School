package listings;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Stats.searchData;

import login.connectionManager;


/**
 * @author <b>John</b>
 * <br><br>This servlet deals with the search functions.<br>
 */

public class SearchServlet extends HttpServlet{	
	
	
	private static final long serialVersionUID = 1L;
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	
	Collection<DishBean> myBeans = new ArrayList<DishBean>();	
	
	/**
	 * <br><br>This post method determins, based on which radio button is pressed,<br>
	 * What method to call next. If REstaurant, it searches in the restaurant method<br>
	 * and if Dishes, it searches in the dishes method.<br>
	 */
	
	@Override
	public void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException  {
		
		
		//Clear current beans, prevents stackup of results
		myBeans.clear();
		
		String searchType = request.getParameter("searchType");
		String searchText = request.getParameter("searchText");
		
		if (searchType.equals("Dish")) {
			
			searchDishes(searchText, request, response);			
		}
		
		else if (searchType.equals("Restaurant")) {
			
			searchRestaurants(searchText, request, response);
		}
		
		else 			
			System.out.println("Nothing searched for");
		
		
		
	}
	
	
	/**
	 * <br><br>This method searches for dishes in the database, returning any that have a <br>
	 * like match the string that was input from the user.<br>
	 */
	
	public void searchDishes(String searchText, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
			
		PreparedStatement searchDishName = null;
		
		try {					
			
			//Query the database for the information that the user would like to search for.
			conn = connectionManager.getConnection();
			
			String dishSearch = "select * from dishes where dishName like ?";			
			
			searchDishName = conn.prepareStatement(dishSearch);
			searchDishName.setString(1, "%" + searchText + "%");
			rs = searchDishName.executeQuery();		
			
			String dishName = "";
			
			//While there is a result
			while (rs.next()) {				
				
				dishName = rs.getString("DishName");
				String dishId = rs.getString("DishID");
				String restName = rs.getString("RestName");
				String dishPrice = rs.getString("DishPrice");
				String dishType = rs.getString("DishType");
				String dishDescription = rs.getString("DishDescription");
				String dishContains = rs.getString("DishContains");
				String dishCuisine = rs.getString("DishCuisine");
				
				DishBean dish = new DishBean();
				
				dish.setDishName(dishName);
				dish.setDishId(dishId);
				dish.setRestName(restName);
				dish.setDishPrice(dishPrice);
				dish.setDishType(dishType);
				dish.setDishDesc(dishDescription);
				dish.setDishContains(dishContains);
				dish.setDishCuisine(dishCuisine);
				
				//Add to the object
				myBeans.add(dish);				
				
			} 
			
			//This method increases the search count on the stats page.
			searchData.increaseSearchCountDish(searchText);
			
			//Closes conections etcs
			conn.close();
			rs.close();
			searchDishName.close();
			
			//Returns the object on the session data.
			request.setAttribute("dishObj", myBeans);
			RequestDispatcher rd = request.getRequestDispatcher("searchResults.jsp");
			rd.forward(request, response);
			
		}
		
		catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		
		
		
	}
	
	/**
	 * <br><br>This method deals with the search of restaurants, in the same manner that the previous<br>
	 * deals with dishes. It searches based on user input and then returns any restaurants<br>
	 * that match that string entered. It also then updates the restaurants searched for in the stats<br>
	 * page.<br>
	 */
	
	public void searchRestaurants(String searchText, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		PreparedStatement searchRestName = null;
		
		try {			
			
			//SQL query and connection getting etc.
			conn = connectionManager.getConnection();
			
			String restSearch = "select * from restaurant where restName like ?";			
			
			searchRestName = conn.prepareStatement(restSearch);
			searchRestName.setString(1, "%" + searchText + "%");
			rs = searchRestName.executeQuery();		
			
			Collection<RestBean> myBeans = new ArrayList<RestBean>();	
			
		while (rs.next()) {				
			
			//While there is a restaurant present in the results it is added
			//to the collection
			
			String restName = rs.getString("restName");
			String restAddress = rs.getString("restAddress");
			String restOpenHours = rs.getString("restOpeningHours");
			String restDescription = rs.getString("restDescription");
			String restType = rs.getString("restType");			
			
			RestBean rest = new RestBean();
						
			rest.setRestName(restName);
			rest.setRestAddress(restAddress);
			rest.setOpenHours(restOpenHours);
			rest.setRestDescrip(restDescription);
			rest.setRestType(restType);				
			
			myBeans.add(rest);
			//This increases the amount of searches made against that name, this method is in the
			//SearchData class.
			searchData.increaseSearchCountRest(restName);
			}

		conn.close();
		rs.close();		

		request.setAttribute("restObj", myBeans);
		RequestDispatcher rd = request.getRequestDispatcher("searchResults.jsp");
		rd.forward(request, response);

		}
		
		catch (Exception e) {
			System.out.println("error probably with the SQL");
			e.printStackTrace();
		}
		
	}
		
}
