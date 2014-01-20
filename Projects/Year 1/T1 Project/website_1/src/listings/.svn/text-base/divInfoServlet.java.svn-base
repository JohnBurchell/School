package listings;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author <b>Patrik Backstrom</b>
 * 
 * <br><br>THIS SERVLET GENERATES THE HTML CODE FOR THE DIVIDER THAT<br>
 * APPEARS WHEN YOU CLICK ON A RESTAURANT.<br>
 * WHEN A RESTAURANT IS CLICKED A JAVASCRIPT IN restaurants.jsp<br>
 * CALLS THIS SERVLET BEFORE ANY CSS-EFFECTS APPEAR.<br>
 * <br>
 * IT QUERYS THE DATABASE FOR INFORMATION ABOUT THE RESTAURANT<br>
 * AND ALSO ALL REVIEWS AVAILABLE FOR THAT RESTAURANT AND <br>
 * RETURNS A LONG STRING OF HTML CODE TO THE JAVASCRIPT THAT<br>
 * PUTS THE CODE IN THE DIVIDER.<br>
 * <br>
 * FURTHER DOWN I EXPLAIN WHAT IS PUT IN THE STRING AND IN WHAT<br>
 * ORDER.<br>
 */

@WebServlet("/divInfoServlet")
public class divInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public divInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
    Connection connection = null;
    Statement stmt = null;
    ResultSet rsRest = null;
    ResultSet rsReviews = null;
    
    public static String currentRest = "";
    
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String divString = request.getParameter("divString");
		
		String[] tableInfo = divString.split("#");
		currentRest = tableInfo[0];
		
		String restQuery = "SELECT * FROM restaurant WHERE RestName=\"" + tableInfo[0] + "\"";
		String reviewQuery = "SELECT ReviewComments FROM reviews WHERE RestName=\"" + tableInfo[0] + "\"";
		String restName = "", restAddress = "", openingHours = "", description = "", score = "", votes = "", cuisine = "", number = "", reviews = "";
		double scoreDouble = 0;
		
		try {
			connection = login.connectionManager.getConnection();
			stmt = connection.createStatement();
		
			// Store all values from result sets
			rsRest = stmt.executeQuery(restQuery);
			if (rsRest.next()) {
				restName = rsRest.getString("RestName");
				restAddress = rsRest.getString("RestAddress");
				openingHours = rsRest.getString("RestOpeningHours");
				description = rsRest.getString("RestDescription");
				score = rsRest.getString("RestScore");
				votes = rsRest.getString("RestVotes");
				cuisine = rsRest.getString("RestType");
				number = rsRest.getString("RestNumber");
				scoreDouble = Double.parseDouble(score)/Double.parseDouble(votes);
			}
						
			rsReviews = stmt.executeQuery(reviewQuery);
			
			int i = 1;
			while (rsReviews.next()) {
				reviews += "<br><i><b>" + i + ": </b>" + rsReviews.getString("ReviewComments") + "</i><br>";
				i++;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("asdasd");
		}
		
		
		
		String data = "";
		
		/*
		 * THE FIRST ADDITION TO THE STRING data CREATES THE TABLE THAT
		 * ADDS A ROW CONTAINING THE CLOSE BUTTON IN THE UPPER RIGHT CORNER.
		 */
		
		data += "<table width=800px border=0>" +
				"<tr height=20>" +
				"<td align=right><a href=\"#\" onclick=\"popup('popUpDiv')\"><img src=\"images/close_div.png\" style=\"padding-bottom: 10px;\" /></a></td>" +
				"</tr>" +
				"</table>";
		
		/*
		 * THE SECOND ADDITION TO data GENERATES A NEW TABLE CONTAINING 
		 * THE MAP, INFORMATION AND REVIEWS.
		 */
		
		// ADD REVIEWS TO THE RIGHT SIDE
		data += "<table width=400px border=0 style=\"display: inline-block; vertical-align: top;\"><tr><td width=390px>" +
				"<form action=addReview><textarea name=reviewComment rows=2 cols=30 placeholder=\"Type your review!\"></textarea><br><input type=\"reset\" name=Clear><input type=submit name=Send></form><br>" +
				"<b>Reviews for " + tableInfo[0] + ":</b><br>" + reviews;
		data += "</td></tr></table>";
		
		// ADD RESTAURANT INFORMATION TO THE LEFT SIDE
		data += "<table width=400px border=0 style=\"display: inline-block;\"><tr height=200px><td width=400><img src=\"images/" + tableInfo[0] + ".jpg\" width=390 height=190 style=\"border: 5px solid #FFF; -webkit-box-shadow: 0px 0px 6px 0px #000000; box-shadow: 0px 0px 6px 0px #000000;\"></td></tr>";
			data += "<tr><td>" +
					"<br/><b>Restaurant: </b>" + restName +
					"<br/><b>Address: </b>" + restAddress +
					"<br/><b>Phone number: </b>" + number +
					"<br/><b>Cuisine: </b>" + cuisine +
					"<br/><b>Opening hours: </b>" + openingHours +
					"<br/><b>Score: </b>" + scoreDouble +
					"<br/><b>Votes: </b>" + votes +
					"<br/><b>Description: </b>" + description +
					"</td></tr>";
		data += "</table>";
		
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(data);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
