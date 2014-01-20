<!-- Author: John, This page deals with administrating the restaurants, and returns / shows
	restaurants that the owners can modify via simple objet manipulation
	via the session. -->

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
	 <br />
		<!-- Request a user to select an option below, also populates a drop down menu
			 to show which restaurants a user can modify -->	
		Please select one of the options.
		<FORM name=updateData action=AdminModifyRest method = POST>	
		
		<select name = "restSelectedName">
 				 
				<c:forEach var="rest" items="${restObj}">
				<Option> <c:out value="${rest.restName}"></c:out></Option>
				</c:forEach>
				
		</select>
		
		<br>	
			<!-- Button to allow a user to modify a restaurant -->
			<input type = submit value = "Modify Restaurant">
			
		</FORM>
		<!-- Creates another form and button to allow the user to create new restaurants. -->
		<FORM name =createNew action=CreateNewRest.jsp method = POST>
			
			<input type = submit value = "Create Restaurants">
			
		</FORM>							
		
				
		

		<br /> <br /> <br />
		<div class=footer>Copyright Group 9, Don't touch!</div>
	</div>
</body>
</html>