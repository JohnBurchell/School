<!-- Author: John, this page is used to modify the users, their data being sent and taken from
	servlets. -->
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

<title>Modify User</title>
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

	<!-- Form which gathers user inputs or outputs the data from the session data
	to be modified. -->

	<form name="UserName Input" action="AdminModifyUser" method="Get">	
	
				<c:forEach var="user" items="${currentOwner}">
				First Name
				<input type="text" name="ownerFirstName" value="<c:out value="${user.ownerFirstName}"></c:out>"><br>
				Last Name
				<input type="text" name="ownerLastName" value="<c:out value="${user.ownerLastName}"></c:out>"><br>
				
				Owner Address<br>								
					<textarea rows="10" cols="30" name="ownerAddress">
					<c:out value="${user.ownerAddress}"></c:out>
					</textarea><br>
				Owner Phone Number
				<input type="text" name="ownerPhoneNumber" value="<c:out value="${user.ownerPhoneNumber}"></c:out>"><br>
				Owner Account Level<br>
				<input type="text" name="ownerAccountLevel" value="<c:out value="${user.ownerAccountLevel}"></c:out>"><br>					
		
				<!-- Two buttons here decide what happens with the data, if delete is selected then the current user is deleted
				whereas, if update is clicked then the user is updated.-->
				</c:forEach>
				<input type="submit" name="buttonPressed" value="Update Owner" onClick="return confirm('Are you sure you want to update this User?')">
				<input type="submit" name="buttonPressed" value="Delete Owner" onClick="return confirm('Are you sure you want to delete this User?')">	
	</Form>
	
	<div class=footer>Copyright Group 9, Don't touch!</div>
	
	</div>
</body>
</html>