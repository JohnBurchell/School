<!-- Author John: This page creates new users by a html form, it then sends the inputs
     From the user to the appropiate servlet where they are input into the database. -->

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
		
	<!-- Input form for the user to create new dishes. -->		

	Enter the details below to create a new dish.	
	
	<form name="User Input" action="CreateNewUser" method="POST">	
	Owner First Name: <input type="text" name="ownerFirstName"><br>
	Owner Last Name: <input type="text" name="ownerLastName"><br>
	Owner UserName: <input type="text" name="ownerUserName"><br>
	Owner Password: <input type="password" name="ownerPassword"><br>
	Owner Address: <br>
	<textarea rows="10" cols="30" name="ownerAddress">    
	</textarea><br>
	Owner Phone Number: <input type="text" name="ownerPhoneNumber"><br>
	Owner Account Level: <input type="text" name="ownerAccountLevel"><br>	
	
	<input type="submit" value="Create New User">
	
	</form>
			<div class=footer>Copyright Group 9, Don't touch!</div>
	
	</div>
</body>
</html>