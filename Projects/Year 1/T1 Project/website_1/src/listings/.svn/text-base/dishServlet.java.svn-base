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
 * <br><br>THIS SERVLET IS CALLED EVERYTIME dishes.jsp IS LOADED.<br>
 * IT IS A LOT SIMILAR TO restaurantServlet BUT DIFFERS IN<br>
 * ONE THING. BEFORE LOOKING FOR ANY CHANGES IN THE 'ORDER BY'<br>
 * DROPDOWN LIST IT LOOKS IF ANYTHING IS TYPED IN THE 'Filter'<br>
 * TEXT FIELD ON dishes.jsp.<br>
 * <br>
 * THE DEFUALT DISH LIST IS GENERATED IF THE FILTER IS EMPTY<br>
 * AND THE RESULTSET IS THEN CONVERTED TO A RESULT OBJECT TO<br>
 * COOP WITH JSTL ON THE JSP PAGE.<br>
 * <br>
 * IF ANYTHING IS TYPED IN THE FILTER THE RESULTSET RETURNED<br>
 * WILL INSTEAD ONLY HOLD DISHES THAT HAS ANYTHING RELATED TO<br>
 * THE TEXT IN THE FILTER.<br>
 */

@WebServlet("/dishServlet")
public class dishServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	public dishServlet() {
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
	Result dishObject = null;


	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String sortBy = "RestName";
		String filter = request.getParameter("dishFilter");
		
		try {
			sortBy = request.getParameter("dishSortByDD");
		} catch (Exception e) {
			
		}
		
		connection = login.connectionManager.getConnection();
		
		if (filter == null) {
			String restQuery = "SELECT * FROM dishes ORDER BY " + sortBy + " ASC";			
			
			try {
				stmt = connection.createStatement();
				resultset = stmt.executeQuery(restQuery);
				
				dishObject = toResult(resultset);
				
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
			sessionGuest.setAttribute("dishSearch", dishObject);
			RequestDispatcher rd = request.getRequestDispatcher("dishes.jsp");
			rd.forward(request, response);
			
		} else {
			String restQuery = "SELECT * FROM dishes WHERE dishName LIKE '%" + filter + "%' OR dishType LIKE '%" + filter + "%' OR dishCuisine LIKE '%" + filter + "%' OR dishContains LIKE '%" + filter + "%' OR dishDescription LIKE '%" + filter + "%' ORDER BY " + sortBy + " ASC";
			
			try {
				stmt = connection.createStatement();
				resultset = stmt.executeQuery(restQuery);

				dishObject = toResult(resultset);

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
			sessionGuest.setAttribute("dishSearch", dishObject);
			RequestDispatcher rd = request.getRequestDispatcher("dishes.jsp");
			rd.forward(request, response);  
		}
		
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
	 * CONVERTS THE RESULTSET TO A RESULT OBJECT.
	 */
	
	public static Result toResult(ResultSet rs) {
		try {
			return new ResultImpl(rs, -1, -1);
		} catch (SQLException ex) {
			return null;
		}
	}
}
