<!-- 
@AUTHOR Patrik.
A SUBPAGE LISTING ALL DISHES IN THE DATABASE.

FUTURE CHANGES: LIST DISHES IN CATEGORIES (A-C, D-F ETC).
 -->
 
 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="main.css" />
<link rel="stylesheet" type="text/css" href="styling.css" />

<title>Group 9, RDSS</title>

<script type="text/javascript">
<!--
$('#search').keyup(function() {
    var val = $.trim($(this).val()).replace(/ +/g, ' ').toLowerCase();
    
    $rows.show().filter(function() {
        var text = $(this).text().replace(/\s+/g, ' ').toLowerCase();
        return !~text.indexOf(val);
    }).hide();
});
//-->
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
			<li><a href="dishServlet" class=current>Browse	dishes</a></li>
			<li><a href="restaurantServlet">Restaurants</a></li>
			<li><a href="administer.jsp">Administer</a></li>
		</ul>
	</div>
	
	<br>
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
		<center>		
				<!-- 
				@AUTHOR Patrik.
				GENERATES A LIST OF CATEGORIES TO SORT DISHES ACCORDING TO
				 -->
		<table border=0 width=658>
			<tr>
				<td width=220>
				<FORM name=dishSortBy action=dishServlet method=GET>
					Order by:
						<select name=dishSortByDD>
							<option value="RestName">Restaurant</option>
							<option value="DishName">Name</option>
							<option value="DishCuisine">Cuisine</option>
							<option value="DishPrice">Price</option>
							<option value="DishContains">Main ingredient</option>
							<option value="DishType">Type</option>
						</select>
					<input type=submit value=Sort>
				</FORM>
				</td>
				<td>
				<form action=dishServlet method=GET>
					Filter: <input name=dishFilter type="text" id="search" placeholder="Filter">
				</form>
				</td>
			</tr>
		</table>
		</center>
<br/><br/>
		
		<!-- 
		@AUTHOR Patrik.
		SEE dishServlet FOR MORE INFORMATION
		FOLLOWING JSTL-SCRIPT GENERATES DISH TABLES GENERATED FROM A RESULT SET CONVERTED
		TO A RESULT OBJECT IN dishServlet.
		 -->
		<center>
		<c:forEach var="row" items="${dishSearch.rows}">
			
			<table class=tableDotted>
				<tr class=tableHeader>
					<td width=558 colspan=2><font size=2><c:out value="${row.RestName}"/> <b><c:out value="${row.DishName}"/></b></font></td>
					<td width=130><font size=2><c:out value="${row.DishType}"/></font></td>
					<td width=100><font size=2><b><c:out value="${row.DishPrice}"/> SEK</b></font></td>
				</tr>
				<tr>
					<td width=658 colspan=4><i><c:out value="${row.DishDescription}"/></i></td>
				</tr>
				<tr>
					<td width=80><font color=#cc6600><i><c:out value="${row.DishCuisine}"/></i></font></td>
					<td width=578 colspan=3><b><c:out value="${row.DishContains}"/></b></td>
				</tr>
			</table>
			<br/>
		</c:forEach>
		</center>
		
	<br>
	<br>
		<div class=footer>super duper ultra fancy stuff like disclaimer
			and copyright and shit...</div>
	</div>
</body>
</html>