<!-- 
@AUTHOR Patrik.
WELCOME PAGE FOR ENTIRE WEBSITE.
 -->
 
 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="main.css" />
<link rel="stylesheet" type="text/css" href="styling.css" />

<title>Group 9, RDSS</title>

<!-- 
AUTHOR: Patrik.
CURRENTLY NOT IN USE. NOT ERASED CAUSE OF POSSIBLE FUTURE USE.
TOGGLES VISIBILITY ON A DIVIDER.
 -->
 
<script type="text/javascript">
<!--
	function toggle_visibility(restaurants) {
		var e = document.getElementById(restaurants);
		if (e.style.display == 'block')
			e.style.display = 'none';
		else
			e.style.display = 'block';
	}
//-->
</script>

<!-- 
@AUTHOR Patrik.
CURRENTLY NOT IN USE. NOT ERASED CAUSE OF POSSIBLE FUTURE USE.
SETS ANIMATION DURATION FOR ABOVE JAVASCRIPT.
 -->
 
<script type="text/javascript">
<!--
	var TimeToFade = 300.0;

	function fade(eid) {
		var element = document.getElementById(eid);
		if (element == null)
			return;

		if (element.FadeState == null) {
			if (element.style.opacity == null || element.style.opacity == '0'
					|| element.style.opacity == '1') {
				element.FadeState = 2;
			} else {
				element.FadeState = -2;
			}
		}

		if (element.FadeState == 1 || element.FadeState == -1) {
			element.FadeState = element.FadeState == 1 ? -1 : 1;
			element.FadeTimeLeft = TimeToFade - element.FadeTimeLeft;
		} else {
			element.FadeState = element.FadeState == 2 ? -1 : 1;
			element.FadeTimeLeft = TimeToFade;
			element.style.display = element.FadeState == 2 ? 'visible'
					: 'hidden';
			setTimeout("animateFade(" + new Date().getTime() + ",'" + eid
					+ "')", 33);
		}
	}

	function animateFade(lastTick, eid) {
		var curTick = new Date().getTime();
		var elapsedTicks = curTick - lastTick;

		var element = document.getElementById(eid);

		if (element.FadeTimeLeft <= elapsedTicks) {
			element.style.opacity = element.FadeState == 1 ? 'hidden'
					: 'visible';
			element.style.opacity = element.FadeState == 1 ? '1' : '0';
			element.style.filter = 'alpha(opacity = '
					+ (element.FadeState == 1 ? '100' : '0') + ')';
			element.FadeState = element.FadeState == 1 ? 2 : -2;
			return;
		}

		element.FadeTimeLeft -= elapsedTicks;
		var newOpVal = element.FadeTimeLeft / TimeToFade;
		if (element.FadeState == 1)
			newOpVal = 1 - newOpVal;

		element.style.opacity = newOpVal;
		element.style.filter = 'alpha(opacity = ' + (newOpVal * 100) + ')';

		setTimeout("animateFade(" + curTick + ",'" + eid + "')", 33);
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

	<div id=content>
	<div class=menu>
		<ul>
			<li><a href="index.jsp" class=current>Home</a></li>
			<li><a href="dishes.jsp">Browse	dishes</a></li>
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
	<br>
		
	<h1>Welcome to the YODSSO System!</h1>
	
	Welcome to the YouOnlyDecisionSupportSystemOnce System!
	
	On the above pages, you can find the following things:
	
	Home page, here!
	
	Browse Dishes - As the name might suggest, you can browse dishes, and search for others
	That you wish to search for!
	
	Restaurants - You can search or browse for restaurants here.
	
	Administer - This is only important if you're a restaurant owner, you can manage dishes, 
	restaurants and see statistics of searches!
	
	If there are any issues with connection or access, please contact any of the following administrators:
	
	john.a.burchell@gmail.com
	kalle.berglund@gmail.com
	vmilveden@hotmail.com
	micgtho@gmail.com
	helen@anckar.com
	mail@patrikbackstrom.com
	
	Thanks, and enjoy!


		<div class=footer>super duper ultra fancy stuff like disclaimer
			and copyright and shit...</div>
	</div>
</body>
</html>