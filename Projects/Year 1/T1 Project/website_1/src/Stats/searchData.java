package Stats;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import login.connectionManager;

/**
 * @author <b>John</b>
 * <br><br>This is the search function which searches through the data.<br>
 * This method is called in other classes and isn't run directly on it's own.<br>
 */

public class searchData { 
	
	
	static Connection conn = null;	
	static ResultSet rs = null;	
	
	public searchData(){
		
	}
	
	/**
	 * This method is used to increase the search count for dishes that have been searched for.<br>
	 * It creates a few SQL statements which are executed to obtain firstly, the restaurant name<br>
	 * based on the search, and secondly updating that restaurant so that the search count<br>
	 * is incremented by one.<br>
	 */
	
	public static void increaseSearchCountRest(String searchedName) {
		
		
		PreparedStatement searchName = null;
		PreparedStatement updateSearches = null;		
		conn = connectionManager.getConnection();
		
		try {
			
			//SQL query
			String getSearchedName = "select * from statsrestaurants where restName like ?";			
			
			//Make connections
			searchName = conn.prepareStatement(getSearchedName);
			//Assign variables
			searchName.setString(1, searchedName);
			//Create result set
			rs = searchName.executeQuery();			
			
			//If the result set contains a response:
			if (rs.next()) {				
				
				//Gets the current score
				int currentScore = rs.getInt("numOfSearches");
				//Increments it
				currentScore++;
				
				//updates the count based on the new current score.
				String updateSearchCount = "UPDATE statsrestaurants set numOfSearches = ? where restName like ? ";
				
				//Creates statement
				updateSearches = conn.prepareStatement(updateSearchCount);
				//Adds first variable
				updateSearches.setInt(1, currentScore);
				//Adds second variable
				updateSearches.setString(2, "%" + searchedName + "%");
				//Executes the update
				updateSearches.executeUpdate();
			
			}
		} 
		
		catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	/**
	 * This method deals with increasing the dish search count.<br>
	 * <br>
	 * It is done by inserting a dish into the database based on the search string, so that users can search<br>
	 * for anything even if it doesn't exist. It uses insert ignore to insert a dish.<br>
	 * <br>
	 * The dishes count is then updated if it already exists, otherwise it becomes 1.<br>
	 */
	
	public static void increaseSearchCountDish(String searchedName) {
		
		
		//Connetions and statements created.
		conn = connectionManager.getConnection();
		PreparedStatement checkExists = null;
		PreparedStatement dishName = null;
		PreparedStatement updateSearches = null;
		
		try {
			
			//Insert ignore inserts a row if it doesn't already exist, and ignores any errors caused by trying to add a row that already does exist.
			String createNewDish = "INSERT IGNORE INTO statsdishes SET DishScoreID = NULL, DishSearch = ?, NumOfSearches = 0";	
			
			//Creates statement
			checkExists = conn.prepareStatement(createNewDish);
			//Assigns variable
			checkExists.setString(1, searchedName);
			//Executes
			checkExists.executeUpdate();
			//New search string
			String getSearchedDish = "select * from statsdishes where dishSearch like ?";
			//Adds connection
			dishName = conn.prepareStatement(getSearchedDish);
			//Sets variable
			dishName.setString(1, searchedName);
			//executes
			rs = dishName.executeQuery();		
			
			//If there is a resposne:
			if (rs.next()) {
				
				//Get the current score as there must already be a search with the name
				int currentScore = rs.getInt("numOfSearches");
				//increment
				currentScore++;				
				
				//run update with new score
				String updateSearchCount = "UPDATE statsDishes set numOfSearches = ? where dishSearch like ? ";
				
				//create statement
				updateSearches = conn.prepareStatement(updateSearchCount);
				//set variables
				updateSearches.setInt(1, currentScore);
				//add wildcards around the variable set
				updateSearches.setString(2, "%" + searchedName + "%");
				//Execute
				updateSearches.executeUpdate();
				
				}
			}
		
			catch (SQLException e) {
			
			e.printStackTrace();
		}
	}

}
