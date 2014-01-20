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
 * <br><br>This servlet deals with the users. It returns an collection object of the userbean type, and puts it<br>
 * into the session. It queries the server for all the current logged in users<br>
 * relevant usernames and returns them back to the webpage.<br>
 */

@WebServlet("/AdminUserServlet")
public class AdminUserServlet extends HttpServlet {
	
	
	private static final long serialVersionUID = 1L;
	
	Connection conn = null;
	PreparedStatement Pstatement = null;
	ResultSet rs = null;
	
	String currentOwner, currentOwnerLevel, url;	
	
	/**
	 * <br><br>The get request simply returns all users that the current user (determined by username)<br>
	 * has access to, which in most cases is usually one, however can be multiple.<br>
	 */
	
	@Override
	public void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		
		try {				
			
			HttpSession session = request.getSession(true);
						
		
			currentOwner = (String) session.getAttribute("username");				
			
			String UserQueryString = "select * from owners where ownerUserName = ?";
			//Prepare Statement
			conn = connectionManager.getConnection();	
			Pstatement = conn.prepareStatement(UserQueryString);
			//Assign Variables
			Pstatement.setString(1, currentOwner);
			//Execute
			rs = Pstatement.executeQuery();				
			
			Collection<AdminBean> myBeans = new ArrayList<AdminBean>();	
			
		while (rs.next()) {				
			
			String ownerFirstName = rs.getString("ownerFirstName");
			System.out.println(ownerFirstName);
			String ownerUserName = rs.getString("ownerUserName");
			String ownerLastName = rs.getString("OwnerLastName");		
			String ownerAccountLevel = rs.getString("ownerAccountLevel");
			
			AdminBean user = new AdminBean();
						
			user.setOwnerFirstName(ownerFirstName);
			user.setOwnerLastName(ownerLastName);
			user.setOwnerUserName(ownerUserName);
			user.setOwnerAccountLevel(ownerAccountLevel);		
			
			myBeans.add(user);
			
			}

		conn.close();
		rs.close();

		request.setAttribute("userObj", myBeans);
		RequestDispatcher rd = request.getRequestDispatcher("adminUser.jsp");
		rd.forward(request, response);

		}
		
		catch (Exception e) {
			System.out.println("error probably with the SQL");
			e.printStackTrace();
		}
		
	}
	
}
	
	

