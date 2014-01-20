<!-- Author: John, This page deals with the modification of restaurants, users may delete or update
	 restaurants here if they so please. It sends the filled in form to a servlet where, depending on
	 the button pressed, is either deleted or updated in the database.-->
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

<title>Modify Restaurant</title>
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

	<!-- Form which takes user inputs to save in the database.
	The data of is taken from the servlet and displayed in appropiate text areas for modification -->
	<form name="Rest Input" action="AdminModifyRest" method="Get">	
	
	
				<c:forEach var="rest" items="${currentRest}">
				Restaurant Name
				<input type="text" name="restName" value="<c:out value="${rest.restName}"></c:out>"><br>
				Restaurant Address
				<input type="text" name="restAddress" value="<c:out value="${rest.restAddress}"></c:out>"><br>
				Restaurant Opening Hours
				<input type="text" name="restOpeningHours" value="<c:out value="${rest.restOpenHours}"></c:out>"><br>
				Restaurant Description<br>								
					<textarea rows="10" cols="30" name="restDescription">
					<c:out value="${rest.restDescrip}"></c:out>
					</textarea><br>
				Restaurant Type
				<input type="text" name="restType" value="<c:out value="${rest.restType}"></c:out>"><br>
				
				</c:forEach>
				
				<!-- Buttons here determine what happens to the restaurant, it is either deleted or
				updated depending on which is pressed -->
				
				<input type="submit" name="buttonPressed" value="Update" onClick="return confirm('Are you sure you want to update this Restaurant?')">
				<input type="submit" name="buttonPressed" value="Delete" onClick="return confirm('Are you sure you want to delete this Restaurant?')">	
	</Form>
	
		<div class=footer>Copyright Group 9, Don't touch!</div>
	
	</div>
</body>
</html>