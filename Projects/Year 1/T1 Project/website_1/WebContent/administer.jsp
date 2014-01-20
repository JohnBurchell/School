<!-- 
AUTHOR: Patrik.
A PAGE ONLY VISIBLE FOR AUTHENTICATED USERS.
HERE THEY CAN CHANGE RESTAURANT DETAILS ETC.
 -->
 
 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@  taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>     
		
<!-- SMALL SCRIPTLET TO VERIFY IF CURRENT USER HAS AN AUTHENTICATED SESSION -->
<%
if (request.getSession(true).getAttribute("currentSessionUser") == null) {
response.sendRedirect("login.jsp");
}
%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="main.css" />
<link rel="stylesheet" type="text/css" href="styling.css" />

<title>Group 9, RDSS</title>

</head>

<body id=body>
	<div id=topLine>
		<div id=loginScreen align=right>
			<FORM name="loginForm" action="loginServlet" method="GET">
				User:&nbsp;<input type=text name=username>&nbsp;
				Password:&nbsp;<input type=password name=password>&nbsp; <input
					type="submit" value="Login">
			</FORM>
		</div>
	</div>
	
	<div id=content>
	<div class=menu>
		<ul>
			<li><a href="index.jsp">Home</a></li>
			<li><a href="dishes.jsp">Browse	dishes</a></li>
			<li><a href="restaurantServlet">Restaurants</a></li>
			<li><a href="administer.jsp" class=current>Administer</a></li>
		</ul>
	</div>
		<br />
	<table width=658 height=30>
	<tr>
		<td align=right>
			<form name="UserInput" action="SearchServlet"  onSubmit="return validateForm()" method="POST">
	  			<input type="radio" name="searchType" value="Dish" checked /> Dish
	  			<input type="radio" name="searchType" value="Restaurant" /> Restaurant 
	  			<input type="text" name="searchText">
				<input type="submit" value="Search">	
			</form>
		</td>
	</tr>
	</table>
	<br />
		<!-- 
		AUTHOR: Patrik.
		A TABLE CONTAINING INFORMATION ABOUT CURRENT USER.
		DETAILED EXPLANATIONS IS FOUND IN loginServlet AND ALL JAVA CLASS FILES
		IT USES.
		 -->
		 <center>
		<table width=100% class=tableDottedNoEffect>
			<tr width=600>
				<td width=300 valign=top>Welcome <b><c:out value='${sessionScope["firstName"]}'/> <c:out value='${sessionScope["lastName"]}'/></b>
				<br/>Session ID: <b><c:out value='${sessionScope["sessionID"]}'/></b> <br /><a href=logoutServlet><font color=#cc6600>Log out</font></a></td>
				<td width=190 valign=top><a href="#" class=tooltip><b><font color=#000>Logged in: </font></b>
				<span>
					<img class="callout" src="images/callout-tooltips.gif" />
        			<strong>Logged in:</strong><br />
        			You logged in <c:out value='${sessionScope["loginTime"]}'/>.<br/>
        			You will automatically be logged out after 30 minutes of inactivity.
    			</span>
    			</a><c:out value='${sessionScope["loginTime"]}'/><br/>
				<b>Last login: </b><c:out value='${sessionScope["lastActivity"]}'/></td>
				<td width=110 valign=top><b>User category: </b><c:out value='${sessionScope["accountLevel"]}'/></td>
			</tr>
		</table>
		</center>
		<br />

		<ul>
			<li>
			<!-- John: Added the Customer Searches link and changed all the original links
					   To servlets, so that the customers data is loaded without the need for
					   the user to click a button to do so. -->
				<a href="AdminDishServlet" class=tooltip>Administer dishes
				<span>
					<img class="callout" src="images/callout-tooltips.gif" />
        			<strong>Administer dishes:</strong><br />
        			Click here to administer your dishes!
        			Change information like price, name, description etc or add and delete dishes.
    			</span>
    			</a>
    		</li>
			<li><a href="AdminRestServlet" class=tooltip>Administer restaurant details
				<span>
					<img class="callout" src="images/callout-tooltips.gif" />
        			<strong>Administer restaurant:</strong><br />
        			Click here to administer your restaurant details!
        			Change address, telephone number etc.
    			</span>
    			</a>
    		</li>
			<li><a href="AdminUserServlet" class=tooltip>Change owner details and password
				<span>
					<img class="callout" src="images/callout-tooltips.gif" />
        			<strong>Owner details:</strong><br />
        			Click here to administer your owner details!
        			Change name, address, telephone number, username, password etc.
    			</span>
    			</a>
    		</li>
    		
			<li><a href="statsPage.jsp" class=tooltip> Customer searches 
			<span>
				<img class = "callout" src ="images/callout-tooltips.gif" />
				<strong>Customer Searches:</strong><br />
				Click here to see statistics!
			</span>
			</a>
			</li>
		</ul>
		<br /> <br /> <br />



		<div class=footer>super duper ultra fancy stuff like disclaimer
			and copyright and shit...</div>
	</div>
</body>
</html>