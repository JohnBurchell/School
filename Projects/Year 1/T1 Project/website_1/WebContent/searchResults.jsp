 <!-- This page deals with the search results, and displays them appropiatley." -->  
 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Search for Dishes and Restaurants</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="main.css" />
<link rel="stylesheet" type="text/css" href="styling.css" />

<!-- Small sript which verifies the user is logged in. -->
<%
if (request.getSession(true).getAttribute("currentSessionUser") == null) {
response.sendRedirect("login.jsp");
}
%>

<script>

//This function checks that the search text is not empty, as an exmpty string will break the 
//servlet, it will not allow a user to serach for nothing. This is done by checking the form and
//The input box to make sure that there's something in it, by making sure it's not null.
function validateForm()	{	

	var x=document.forms["UserInput"]["searchText"].value;
	
	if (x==null || x=="")  {
	
 	 alert("You must search for something!");
  	 return false;
  
  }
}
</script>
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
			<li><a href="administer.jsp">Administer</a></li>
		</ul>
	</div>
		<br /> <br />		
	
	<br>
	
	Welcome!
	<!-- This is where the user is able to search for restaurants and dishes. -->
	
	Please use this page to search for dishes by name or to search for restaurants themselves!
	
	<br>
	
	<!-- Simple form created with radio buttons, depending on which is selected determines the search
	on the data in the servelt. Dish is set to be clicked by default. -->
	
	<form name="UserInput" action="SearchServlet"  onSubmit="return validateForm()" method="POST">	

	  <input type="radio" name="searchType" value="Dish" checked /> Dish
	  <input type="radio" name="searchType" value="Restaurant" /> Restaurant
	<br> <br>
	  Please enter a dish name or a restaurant.: <input type="text" name="searchText"><br>	
	
	<input type="submit" value="Search">	
	</form>
		
	<br>	
	<!-- This checks if the search returned is empty or not. It checks if either a restaurant
	or dish object has been returned, if one has, then the search was succesfull, otherwise it was not. -->
	<c:if test="${(not empty dishObj) || (not empty restObj)}">		
		<c:forEach var="dish" items="${dishObj}">
			<center>
			<table class=tableDotted>
				<tr class=tableHeader>
				
				<!-- The data from the search is returned and printed to screen following the style used in
				other areas of the website. -->
					<td width=558 colspan=2> <b><c:out value="${dish.dishName}"/></b></td>
					<td width=130><font size=2><c:out value="${dish.dishType}"/></font></td>
					<td width=100><font size=2><b><c:out value="${dish.dishPrice}"/> SEK</b></font></td>
				</tr>
				<tr>
					<td width=658 colspan=4><i><c:out value="${dish.dishDesc}"/></i></td>
				</tr>
				<tr>
					<td width=80><font color=#cc6600><i><c:out value="${dish.dishCuisine}"/></i></font></td>
					<td width=578 colspan=3><b><c:out value="${dish.dishContains}"/></b></td>
				</tr>
			</table>
			<br/>
			</center>
		</c:forEach>
		</c:if>
			<!-- This prints out restaurant objects if they exist. -->
		<c:forEach var="rest" items='${restObj}'>
			<center>
				<table width="658" class=tableDotted>
					<tr height=25 class=tableHeader>
						<td width=250 colspan=3><span><c:out value="${rest.restName}" /></span></td>
						<td width=408 colspan=2><span><c:out value="${rest.restAddress}" /></span></td>
					</tr>
					<tr valign=top>
						<td width=250 colspan=3><span><b>Phone: </b><c:out value="${rest.restNumber}" /></span></td>
						<td width=408 colspan=2 rowspan=2><span><b>Open: </b><c:out value="${rest.restOpenHours}" /></span></td>
					</tr>
					<tr valign=top>
						<td width=100><span><b>Cuisine: </b><c:out value="${rest.restType}" /></span></td>
					</tr>
					<tr height=10 width=100></tr>
					<tr valign=top>
						<td colspan=5><span><i><b>Description: </b>
							<c:out value="${rest.restDescrip}" /></i></span></td>
					</tr>
					<tr height=10 width=100></tr>
				</table>
				<br />
			</center>		
		</c:forEach>	
		<!-- Again, this is a check if both objects are empty then a search must have been returned negative
		and thus the user is informed likewise. -->
	<c:if test="${(empty dishObj) && (empty restObj)}">
	No search results returned, please try another dish or restaurant name!
	</c:if>
	<br/>
	<br/>
	
	<div class=footer>Copyright Group 9, Don't touch!</div>
	
	</div>
	
	
	
</body>
</html>