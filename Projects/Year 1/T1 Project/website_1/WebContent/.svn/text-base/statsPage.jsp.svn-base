<!-- Author: John This page displays stats from the database about dishes and restaurants -->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<!-- Small sript which verifies the user is logged in. -->
<%
if (request.getSession(true).getAttribute("currentSessionUser") == null) {
response.sendRedirect("login.jsp");
}
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 
  <title>Restaurant Search statistics</title>
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">  
  <link rel="stylesheet" type="text/css" href="main.css" />
  <link rel="stylesheet" type="text/css" href="styling.css" />
  <script type="text/javascript" src="http://www.google.com/jsapi"></script>
  <script type="text/javascript">

  //Load the Visualization API and the ready-made Google table visualization
  google.load('visualization', '1', {'packages':['corechart']});

  // Set a callback to run when the API is loaded.
  google.setOnLoadCallback(initFirstChart);
  //Load second Chart
  google.setOnLoadCallback(initSecondChart);

  // Send the query to the data source.
  function initFirstChart() {

    // Specify the data source URL.
    var query = new google.visualization.Query('stats');

    // Send the query with a callback function.
    query.send(handleQueryResponseChartOne);
  }  

  // Handle the query response or print a message if there was an error.
  function handleQueryResponseChartOne(response) {
    if (response.isError()) {
      alert('Error in query: ' + response.getMessage() + ' ' + response.getDetailedMessage());
      return;
    }

    // Draw the visualization.
    var data = response.getDataTable();
    //Options are defined, title is defined here.
    var options = {title: 'Daily Search Results', };
    //Chart is assigned to a div.
    var chart = new google.visualization.PieChart(document.getElementById('chart_div1'));
    //Chart is drawn.
    chart.draw(data, options);
  }
  
  //Second Chart here!
  function initSecondChart() {

	    // Specify the data source URL.
	    var query = new google.visualization.Query('dishStats');

	    // Send the query with a callback function.
	    query.send(handleQueryResponseChartTwo);
	  }
  
  // Handle the query response.
  function handleQueryResponseChartTwo(response) {
    if (response.isError()) {
      alert('Error in query: ' + response.getMessage() + ' ' + response.getDetailedMessage());
      return;
    }

    // Draw the visualization.
    var data = response.getDataTable();
    var options = {title: 'Dish Search Results', };
    var chart = new google.visualization.PieChart(document.getElementById('chart_div2'));
    chart.draw(data, options);
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
			<li><a href="administer.jsp" class=current>Administer</a></li>
		</ul>
	</div>
	
	<!-- The divs below are the charts from above, they are simply defined here 
	and given some other attributes, such as size etc. -->
	  <h1>Restaurant search stats</h1>
 	  This pie chart shows which restaurants where searched for, and how many times.
      <div id="chart_div1" style="width: 500px; height: 400px;" align="center"></div>
      
      <h2>Dishes search statistics</h2>
      This Pie chart shows what dishes were searched for, and how many times.
      <div id ="chart_div2" style="width: 500px; height: 400px" align="center"></div>   
	
	
	<!-- A form is created with a button where one can clear data stored in the database. -->
	<FORM name="clearData" method="POST" action="ClearDataServlet">			
	The button below clears all the stored data for dish searches and resets the number of searches for restaurants.
		
	<br>
	
	<input type="submit" name="ButtonPressed" value="ClearStats" onClick="return confirm('Are you sure you want delete all data?')" >
			
	  </FORM>
      
		<div class=footer>Copyright Group 9, Don't touch!</div>	
			
   </div>
   
   
  
</body>	
</html>