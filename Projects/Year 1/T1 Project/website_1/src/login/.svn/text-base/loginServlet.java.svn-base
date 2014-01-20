package login;

import java.io.IOException;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author <b>Patrik Backstrom</b>
 * 
 * <br><br>NOTE: THIS IS ONLY IMPLEMENTED BY Patrik.<br>
 * THE BASIC CODE IS FROM A UNKNOWN AUTHOR AND IS ONLY<br>
 * IMPLEMENTED AND MODIFIED BY Patrik.<br>
 * <br>
 * ALL IMPLEMENTATION AND MODIFICATION IS FOUND IN THE<br> 
 * CHANGELOG.<br>
 * <br>
 * I WILL EXPLAIN THE WHOLE LOGIN PACKAGE IN THIS COMMENT.<br>
 * <br>
 * FIRST, loginServlet INSTANTIATES A NEW loginBean AND SAVES<br>
 * IT AS <code>user</code> AND SETS THE CURRENT USERNAME AND PASSWORD TO<br>
 * WHATEVER IS STATED IN CORRESPONDING TEXT FIELDS ON THE <br>
 * WEBSITE. <br>
 * <br>
 * SECONDLY, <code>loginDAO</code> IS CALLED WITH <code>user</code> AS AN ARGUMENT.<br>
 * loginDAO QUERYS THE DATABASE TO LOOK FOR A TUPLE CONTAINING<br>
 * USERNAME AND PASSWORD AS ATTRIBUTES. IF THAT TUPLE EXISTS<br>
 * THE THE BOOLEAN <code>userExists</code> IS TRUE THEN THE BOOLEAN <code>valid</code><br>
 * IN <code>loginBean</code> IS ALSO SET TO <code>true</code>. <code>loginDAO</code> ALSO USES THE<br>
 * SETTERS IN <code>loginBean</code> TO SET ALL RELATIVE DATA FROM THE <br>
 * RESULTSET AND THE CONTROL IS RETURNED TO THIS SERVLET.<br>
 * <br>
 * THIRD STEP TAKES PLACE IN THIS SERVLET.<br>
 * IF <code>valid</code> IN <code>loginBean</code> IS <code>true</code> THEN A NEW SESSION IS<br>
 * CREATED AND THE GETTERS IN <code>loginBean</code> IS USED TO STORE<br>
 * ALL USER DATA AS ATTRIBUTES IN THE CURRENT SESSION.<br>
 * IF <code>valid</code> IS SET TO <code>false</code> THEN THE USER IS REDIRECTED TO<br>
 * loginFailed.jsp.<br>
 * <br>
 * LASTLY, THE CURRENT DATE AND TIME IS INSTANTIATED, REFORMATTED<br>
 * AND SENT TO THE METHOD <code>addToUserTuple(String date)</code> AND THE<br> 
 * ATTRIBUTE <code>ownerLastActivity</code> IN THE DATABASE IS UPDATED TO<br>
 * CORRECT DATE AND TIME.<br>
 */

@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd, HH:mm:ss");

	public loginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
            loginBean user = new loginBean();
            user.setOwnerUsername(request.getParameter("username"));
            user.setOwnerPassword(request.getParameter("password"));
            user = loginDAO.login(user);
            if(user.isValid()) {
                HttpSession session = request.getSession(true);
                session.setAttribute("currentSessionUser",user);
                session.setAttribute("username", loginBean.getOwnerUsername());
                session.setAttribute("firstName", user.getOwnerFirstName());
                session.setAttribute("lastName", user.getOwnerLastName());
                session.setAttribute("accountLevel", user.getOwnerAccountLevel());
                session.setAttribute("lastActivity", user.getOwnerLastActivity());
                session.setAttribute("sessionID", session.getId());
                session.setAttribute("ownerPhoneNumer", user.getOwnerPhoneNumber());
                session.setAttribute("address", user.getOwnerAddress());
                session.setMaxInactiveInterval(1337);
                
                Date date = new Date();
                String dateF = dateFormat.format(date);             
                session.setAttribute("loginTime", dateF);
                
                addToUserTuple(dateF);
                
                response.sendRedirect("administer.jsp");
            } else
                response.sendRedirect("loginFailed.jsp");
        } catch (Throwable exc) {
            System.out.println(exc);
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

	
	private void addToUserTuple(String date) throws SQLException {

		Connection currentCon = null;
		String dateString = date;
		String username = loginBean.getOwnerUsername();
		Statement stmt = null;
		String updateActivityQuery = "UPDATE owners SET ownerLastActivity='"+dateString+"' WHERE ownerUsername='"+username+"'";
		
		try {
			currentCon = connectionManager.getConnection();
			stmt = currentCon.createStatement();
			stmt.executeUpdate(updateActivityQuery);
		} catch (Exception e) {
			
		} finally {
			stmt.close();
			currentCon.close();
		}
	}
}