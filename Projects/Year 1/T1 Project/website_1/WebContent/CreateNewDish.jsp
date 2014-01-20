<!-- Author: John, This page is for creating new dishes, and it contains a form which users can fill in
	 to do so, the values are taken by the servlet to be used in the creation of a new dish.
	 This is achieved by a simple form and capture of user input. -->
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

<title>Create New Dish</title>
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
	
	<!-- input form is below. -->
	Enter the details below to create a new dish.	
	
	<form name="Dish Input" action="CreateNewDish" method="POST">	
	Dish Name: <input type="text" name="dishName"><br>
	Dish Type: <input type="text" name="dishType"><br>
	Dish Price: <input type="text" name="dishPrice"><br>
	Dish Description: <br>
	<textarea rows="10" cols="30" name="dishDescription">
	Please enter a description.
	</textarea><br>
	Dish Contains: <input type="text" name="dishContains"><br>
	Dish Cuisine: <input type="text" name="dishCuisine"><br>
	
	<input type="submit" value="Create New Dish">
	</form>
	
			<div class=footer>Copyright Group 9, Don't touch!</div>
	
	
	</div>
	
</body>
</html>