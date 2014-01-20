package Stats;
import com.google.visualization.datasource.DataSourceServlet;
import com.google.visualization.datasource.base.TypeMismatchException;
import com.google.visualization.datasource.datatable.ColumnDescription;
import com.google.visualization.datasource.datatable.DataTable;
import com.google.visualization.datasource.datatable.value.ValueType;
import com.google.visualization.datasource.query.Query;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import login.connectionManager;

/**
 * @author <b>John</b>
 * <br><br>This deals with preparing the data to be sent to the charts, it's heavily influenced from the<br>
 * tutorials available with the google visualisation package.<br>
 */

public class dishSearchChart extends DataSourceServlet {
	

	private static final long serialVersionUID = 1L;
	
	//Create connections etc.
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	String[] stringHolder;
	Integer[] intsHolder;
	
	

	/**
	 * This is an already defined method in the google packages, hence it's override statement.<br>
	 * It creates a new Datatable, arraylist of collumnDescription type and stores details.<br>
	 * It is used to create a table and populate it with data. I have added some more methods that add the<br>
	 * data and return it back to this method so that it can be inserted into the <br>
	 * new chart data.<br>
	 */
	
  @Override
  public DataTable generateDataTable(Query query, HttpServletRequest request) {
    // Create a data table.
    DataTable data = new DataTable();
    ArrayList<ColumnDescription> cd = new ArrayList<ColumnDescription>();
    
    //Collumns, these are the headers under which the data is stored, as the pie chart
    //I wish to use only has 2 collumns, i only create 2 here.
    cd.add(new ColumnDescription("Name", ValueType.TEXT, "Searched Dish Name"));  
    cd.add(new ColumnDescription("Searches", ValueType.NUMBER, "Search Number"));
   
    //Collumns are added to the data table.
    data.addColumns(cd);

    // Fill the data table.
    try {    	
    	
    	//Gets the data from another method.
    	getDataForTable();    	
    	
    	//Loop through how many there are and add them accordingly from the arrays.
    	for (int i = 0; i < stringHolder.length; i++) {    	
    			
    		data.addRowFromValues(stringHolder[i], intsHolder[i]);    
    		
    	}    	    	
      
    } catch (TypeMismatchException e) {
    	
      System.out.println("Invalid type!");
    }
    
    return data;
  }
  
  /**
   * This method deals with getting the data for the tables,<br>
   * It queries the statsDishes table in the database and returns the values required<br>
   * in two array lists of their respective types, ints and strings. <br>
   * <br>
   * The lists are then iterated through with a 2 for loops and are added to the data / collumns<br>
   * etc as above.<br>
   */
  
  public void getDataForTable() {
	  
	  
	  //Array lists of their respective types to store the data.
		ArrayList<String> strings = new ArrayList<String>();
		ArrayList<Integer> ints = new ArrayList<Integer>();
	  
	  try {			
			
		  //Connections are made and queries performed.
			conn = connectionManager.getConnection();
			stmt = conn.createStatement();
			String QueryString = "select * from statsdishes";
			rs = stmt.executeQuery(QueryString);		
			
			while (rs.next()) {
				
				String restName = rs.getString("dishsearch");
				int numOfSearches = rs.getInt("numOfSearches");
				
				strings.add(restName);
				ints.add(numOfSearches);
				
				
			}
			
			//the arrays defined at the start are set to the size of the array lists
			//that the returned strings and ints populated.
			stringHolder = new String[strings.size()];
	    	intsHolder = new Integer[ints.size()];
	    	
	    	//counters are used to keep track of how far in the arrays the program is.
	    	int counter1 = 0;
	    	int counter2 = 0;
	    	
	    	//loop through them with for:each loops and add them, with the correct index
	    	//into the stringHolder array.
	    	for (String s : strings) {
	    		
	    		stringHolder[counter1] = s;
	    		counter1++;
	    	}
	    	
	    	for (Integer i : ints) {  
	    		
	    		intsHolder[counter2] = i;	    	
	    		counter2++;
	    	
	    	} 
			
			conn.close();
			rs.close();
			
				
		
			}
		catch(Exception e) {
			
			e.printStackTrace();
		}
		
	}	
  
  /**
   * <br><br>required by the google documentation<br>
   */
  
  @Override 
  protected boolean isRestrictedAccessMode() { 
    return false; 
  }
  }

