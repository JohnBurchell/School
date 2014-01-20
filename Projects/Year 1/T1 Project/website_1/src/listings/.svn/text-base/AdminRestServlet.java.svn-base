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
import javax.servlet.http.HttpSession;


import login.connectionManager;

/**
 * @author <b>John</b>
 * <br><br>This servlet deals with the restaurant. It returns an collection object of the restBean type, and puts it<br>
 * into the session profile. It queries the server for all the current logged in users<br>
 * restaurant (username) and returns them back to the webpage.<br>
 */

@WebServlet("/AdminRestServlet")
public class AdminRestServlet extends HttpServlet {
	
	
	private static final long serialVersionUID = 1L;
	
	Connection conn = null;
	PreparedStatement getRests = null;
	ResultSet rs = null;
	
	String currentRestaurant, currentOwner, url;	
	
	/**
	 * <br><br>The get request simply returns all restaurants that the current user (determined by username)<br>
	 * has access to, which in most cases is usually one, however can be multiple.<br>
	 */
	
	@Override
	public void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		
		try {				
			
			HttpSession session = request.getSession(true);
			conn = connectionManager.getConnection();			
		
			currentOwner = (String) session.getAttribute("username");
			
			String RestQueryString = "select * from restaurant where ownerUserName = ?";	
			
			getRests = conn.prepareStatement(RestQueryString);
			getRests.setString(1, currentOwner);
			
			rs = getRests.executeQuery();		
			
			Collection<RestBean> myBeans = new ArrayList<RestBean>();	
			
			while (rs.next()) {						
			
				String restName = rs.getString("restName");
				String restAddress = rs.getString("restAddress");
				String restOpenHours = rs.getString("restOpeningHours");
				String restDescription = rs.getString("restDescription");
				String restType = rs.getString("restType");
				String ownerUserName = rs.getString("ownerUserName");
			
				RestBean rest = new RestBean();
						
				rest.setRestName(restName);
				rest.setRestAddress(restAddress);
				rest.setOpenHours(restOpenHours);
				rest.setRestDescrip(restDescription);
				rest.setRestType(restType);
				rest.setOwnerUserName(ownerUserName);				
			
				myBeans.add(rest);
			
			}

		conn.close();
		rs.close();	
		getRests.close();

		request.setAttribute("restObj", myBeans);
		RequestDispatcher rd = request.getRequestDispatcher("adminRestaurant.jsp");
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
		
	
		doGet(request, response);		
	
	}
}
	
	

