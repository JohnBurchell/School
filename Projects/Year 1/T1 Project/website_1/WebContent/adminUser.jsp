<!-- Author: John, this page helps the user administer their current details
	 Didn't get time to sort the page so that we can show content only to super users
	 etc so all available functions are here. -->
	 
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
		
		<!-- This creates a form by which the user is sent to the admin modify user servlet
		 	 From here they choose which user to modify or to create a new user. -->
	
		Please select one of the following options.	
				
		<FORM name=updateData action=AdminModifyUser method = POST>	
		
		<select name = "userSelectedName">
 				 
				<c:forEach var="user" items="${userObj}">
				<Option> <c:out value="${user.ownerUserName}"></c:out></Option>
				</c:forEach>
				
		</select>	
		
		<br>	
		
			<input type = submit value = "Modify User">
			
		</FORM>		
		
		<!-- New user creation form here! -->
		
		<FORM name =createNewUser action=CreateNewUser.jsp method = POST>
			
			<input type = submit value = "Create New User">
			
		</FORM>											
							
		<p>
	
		
		<div class=footer>Copyright Group 9, don't touch!</div>
	</div>
</body>
</html>