<!-- Author: John, This page deals with modifying dish data, The data from the dishes is
taken from adminDishServlet and sent back here, where it is printed from the session data whence it
is stored. -->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

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
<title>Modify Dish</title>
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


	<!-- The inputs, or outputs are shown here from the session object, using JSTL tags
		 It loops through everything in the object to show them on the page. -->

	<form name="Dish Input" action="AdminDishModify" method="Get">	
	
				<c:forEach var="dish" items="${currentDish}">
				Dish Name
				<input type="text" name="dishName" value="<c:out value="${dish.dishName}"></c:out>"><br>
				Dish Type
				<input type="text" name="dishType" value="<c:out value="${dish.dishType}"></c:out>"><br>
				Dish Price
				<input type="text" name="dishPrice" value="<c:out value="${dish.dishPrice}"></c:out>"><br>
				Dish Description<br>								
					<textarea rows="10" cols="30" name="dishDescription">
					<c:out value="${dish.dishDesc}"></c:out>
					</textarea><br>
				Dish Cuisine
				<input type="text" name="dishCuisine" value="<c:out value="${dish.dishCuisine}"></c:out>"><br>
				Dish Contains<br>								
					<textarea rows="10" cols="30" name="dishContains">
					<c:out value="${dish.dishContains}"></c:out>
					</textarea><br>
			<!-- Couple of buttons to decide what to do next, if the user wishes to save what they have input
			then they may do so, otherwise they can delete the dish entirely, there are 2 validation checks here to
			prevent the user from clicking the buttons by mistake. -->
				</c:forEach>
				<input type="submit" name="buttonPressed" value="UpdateDish" onClick="return confirm('Are you sure you want to update this dish?')">
				<input type="submit" name="buttonPressed" value="DeleteDish" onClick="return confirm('Are you sure you want to delete this dish?')">	
	</Form>
	
	<div class=footer>Copyright Group 9, Don't touch!</div>	
	
	</div>
</body>
</html>