<!-- Author : John - This page prints out data about dishes from the adminDishes servlet -->

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
		
		
	
		Please select one of the options.
		<FORM name=updateData action=AdminDishModify method = POST>	
		<!-- Creates a form which resolves back to adminDish Modify using a post Method. -->
		<select name = "dishSelectedName">
		
 				 <!-- Loops over the elements in the object which is stored in the session
 				 	  This then printed per dish name. -->
 				 	  
				<c:forEach var="dish" items="${dishObj}">
				<Option> <c:out value="${dish.dishName}"></c:out></Option>				
				</c:forEach>
				
		</select>	
		<br>	
		
			<input type = submit value = "Modify Dish">
			
		</FORM>			
		
		<!-- Creates a new form which resolves to the create new dish page, where a user
			can create a new page. -->
			
		<FORM name =createNew action=CreateNewDish.jsp method = POST>
			
			<input type = submit value = Create>
			
		</FORM>						
							
		<p>
	
		
		<div class=footer>Copyright Group 9, Don't touch!</div>

	</div>
</body>
</html>