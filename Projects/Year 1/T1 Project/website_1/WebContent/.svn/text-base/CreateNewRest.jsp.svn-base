<!-- Author: John, this page creates new restaurants with user input forms. The inputs are sent to
	 A java servlet where they are then queried into the server. -->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!-- Small sript which verifies the user is logged in. -->
	
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

<title>Create New Restaurant</title>
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
		<br /> <br />		
		
	<!-- Restaurant creation form, user input here is sent to CreateNewRest -->

	Enter the details below to create a new restaurant.	
	
	<form name="Rest Input" action="CreateNewRest" method="POST">	
	Restaurant Name: <input type="text" name="restName"><br>
	Restaurant Address: <input type="text" name="restAddress"><br>
	Restaurant Opening Hours: <input type="text" name="restOpeningHours"><br>
	Restaurant Description: <br>
	<textarea rows="10" cols="30" name="restDescription">
Please enter a description.
	</textarea><br>
	Restaurant Type: <input type="text" name="restType"><br>
	Restaurant Number: <input type ='text' name = restNumber>	
	
	<input type="submit" value="Create new Restaurant">
	</form>
	
	<div class=footer>Copyright Group 9, Don't touch!</div>	
	
	</div>
</body>
</html>