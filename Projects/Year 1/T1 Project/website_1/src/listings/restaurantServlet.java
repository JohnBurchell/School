package listings;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.sql.Result;

import org.apache.taglibs.standard.tag.common.sql.ResultImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * @author <b>Patrik Backstrom</b>
 * 
 * <br><br>WHENEVER restaurants.jsp IS LOADED THIS SERVLET IS GIVEN CONTROL.<br>
 * <br>
 * THE FIRST <code>try</code> IN <code>doGet</code> METHOD LISTENS FOR ANY CHANGES IN THE<br>
 * 'ORDER BY' DROPDOWN LIST. IF ANY VALUE I FETCHED FROM THE DROPDOWN<br>
 * LIST THAT VALUE IS USED TO ORDER THE GENERATED DISH LIST.<br>
 * <br>
 * THE SECOND <code>try</code> ESTABLISH A CONNECTION TO THE DATABASE AND QUERYS<br>
 * THE DATABASE FOR ALL RESTAURANTS WITH THE _DEFAULT_ ORDER IF NOTHING<br>
 * ELSE IS FETCHED FROM THE DROPDOWN LIST. THEN THE RESULTSET IS <br>
 * CONVERTED TO A RESULT _OBJECT_ (A RESULTSET CAN NOT BE STORED IN<br>
 * A SESSION). THE OBJECT IS SAVED IN THE CURRENT SESSION AND IS THEN<br>
 * VISIBLE FOR THE JSTL LOOP ON restaurants.jsp.<br>
 */

@WebServlet("/restaurantServlet")
public class restaurantServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	public restaurantServlet() {
		super();
		// TODO Auto-generated constructor stub

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	Connection connection = null;
	Statement stmt = null;
	ResultSet resultset = null;
	Result restaurantObject = null;


	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String sortBy = "RestName";
		
		try {
			sortBy = request.getParameter("restSortByDD");
		} catch (Exception e) {
			
		}
		
		String restQuery = "SELECT * FROM restaurant ORDER BY " + sortBy + " ASC";
		
		try {
			connection = login.connectionManager.getConnection();
			stmt = connection.createStatement();
			resultset = stmt.executeQuery(restQuery);

			restaurantObject = toResult(resultset);

		} catch (SQLException se) {
			System.out.println("failed sql");
			se.getStackTrace();
		}
		try {
			stmt.close();
			connection.close();
		} catch (SQLException sqle) {
			
		}
		HttpSession sessionGuest = request.getSession(true);
		sessionGuest.setAttribute("restaurantSearch", restaurantObject);
		RequestDispatcher rd = request.getRequestDispatcher("restaurants.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		

	}

	/*
	 * METHOD TO CONVERT A RESULTSET TO A RESULT OBJECT.
	 */
	
	public static Result toResult(ResultSet rs) {
		try {
			return new ResultImpl(rs, -1, -1);
		} catch (SQLException ex) {
			return null;
		}
	}
}
