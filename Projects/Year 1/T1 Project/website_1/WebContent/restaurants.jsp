<!-- 
AUTHOR: Patrik.
A SUBPAGE LISTING ALL RESTAURANTS IN THE DATABASE.
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
<script src="http://code.jquery.com/jquery-latest.min.js"></script>

<title>Group 9, RDSS</title>


<!-- 
@AUTHOR Patrik.
FOLLOWING JAVASCRIPT RECIEVS A NAME DEPENDING ON WHAT RESTAURANT YOU CLICK.
IT CALLS A SERVLET THAT QUERYS THE DATABASE FOR INFO ABOUT THAT RESTAURANT
AND SENDS BACK A STRING OF HTML TAGS PLUS INFO FROM THE DATABASE
AND SETS THE TEXT OF popUpDiv.
 -->
<script type="text/javascript">
<!--
function cellContent(tblName) {
	
	var colArray = new Array(7);
	var Stringen = "";
	
	var refTab=document.getElementById(tblName);
	for ( var i = 0; i<refTab.rows.length; i++ ) { 
	  var row = refTab.rows.item(i); 
	  for ( var j = 0; j<row.cells.length; j++ ) { 
	    var col = row.cells.item(j);
	    colArray[j] = col.firstChild.innerHTML;
	    Stringen += col.textContent + "#";
	  }
	}
	
	$.get('divInfoServlet', {divString : Stringen}, function(data) {
		document.getElementById("popUpDiv").innerHTML = data;
	});
 }
//-->
</script>

<!-- 
@AUTHOR Patrik.
THIS JAVASCRIPT IS CALLED AT THE SAME TIME AS ABOVE cellContent(tblName)
AND MOVES THE INTERFACE AWAY AND SLIDES IN THE DIVIDER SHOWING RESTAURANT
INFO AND REVIEWS.
 -->
<script type="text/javascript">
<!--
	function popup(windowname) {
		toggle(windowname);	
	}

	function toggle(div_id) {
		var elPop = document.getElementById(div_id);
		var elContent = document.getElementById('content');
		var elCover = document.getElementById('cover');
		var elBody = document.getElementById('body');
		var elTopLine = document.getElementById('topLine');
		
		if ( elCover.style.opacity == 0 ) {
			elPop.style.opacity = 1;
			elPop.style.left = '50%';
			elCover.style.height = '100%';
			elCover.style.width = '100%';
			elCover.style.left = '0%';
			elCover.style.top = '0%';
			elCover.style.opacity = 0.65;
			elContent.style.left = '800px';
			elBody.style.overflow = 'hidden';
			elTopLine.style.top = '-35px';
		}
		else {
			elPop.style.opacity = 0;
			elPop.style.left = '-50%';
			elCover.style.height = '0%';
			elCover.style.width = '0%';
			elCover.style.left = '50%';
			elCover.style.top = '50%';
			elCover.style.opacity = 0;
			elContent.style.left = '0px';	
			elBody.style.overflow = 'visible';
			elTopLine.style.top = '0px';
		}
	}
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

<div id="cover"></div>
<div id="popUpDiv"></div>

	<div id=content>
	<div class=menu>
		<ul>
			<li><a href="index.jsp">Home</a></li>
			<li><a href="dishServlet">Browse dishes</a></li>
			<li><a href="restaurantServlet" class=current>Restaurants</a></li>
			<li><a href="administer.jsp">Administer</a></li>
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
		<center>
		<table border=0 width=658>
			<tr>
				<td>
				<!-- SEE COMMENTS IN restaurantServlet FOR CLARIFICATION -->
				<FORM name=restSortBy action=restaurantServlet method=GET>
					Order by:
						<select name=restSortByDD>
							<option value="RestName">Name</option>
							<option value="RestAddress">Address</option>
							<option value="RestType">Cuisine</option>
						</select>
					<input type=submit value=Sort>	
				</FORM>
				</td>
			</tr>
		</table>
		</center>
		<br/>
		<br/>
		
		<!-- 
		CREATES A COUNTER WITH JSTL-SCRIPT TO GIVE EVERY TABLE A UNIQUE NAME
		RECIEVS INFORMATION ABOUT EVERY RESTAURANT FROM A RESULT OBJECT TRANSFORMED
		FROM A RESULT SET CREATED IN restaurantServlet. 
		 -->
		 
		<c:set var="counter" value="1" />
		<c:forEach var="row" items='${sessionScope["restaurantSearch"].rows}'>
			<center>
				<table width="658" class=tableDotted onMouseDown="cellContent('restTable${counter}')" onClick="popup('popUpDiv')" id="restTable${counter}">
					<tr height=25 class=tableHeader>
						<td width=250 colspan=3><span><c:out value="${row.restName}" /></span></td>
						<td width=408 colspan=2><span><c:out value="${row.restAddress}" /></span></td>
					</tr>
					<tr valign=top>
						<td width=250 colspan=3><span><b>Phone: </b><c:out value="${row.restNumber}" /></span></td>
						<td width=408 colspan=2 rowspan=2><span><b>Open: </b><c:out value="${row.restOpeningHours}" /></span></td>
					</tr>
					<tr valign=top>
						<td width=100><span><b>Cuisine: </b><c:out value="${row.restType}" /></span></td>
						<td width=150 colspan=2><span><b>Score: </b><fmt:formatNumber pattern="#.#"	value="${row.restScore/row.restVotes}" /> <font color=#cc6600><i>(<c:out value="${row.restVotes}" /> votes)</i></font></span></td>
					</tr>
					<tr height=10 width=100%></tr>
					<tr valign=top>
						<td colspan=5><span><i><b>Description: </b>
							<c:out value="${row.restDescription}" /></i></span></td>
					</tr>
					<tr height=10 width=100%></tr>
				</table>
				<br />
			</center>
			<c:set var="counter" value='${(counter+1)}' />
		</c:forEach>
		



		<div class=footer>super duper ultra fancy stuff like disclaimer
			and copyright and shit...</div>
	</div>
</body>
</html>