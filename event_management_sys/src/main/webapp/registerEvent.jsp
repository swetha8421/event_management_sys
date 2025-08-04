<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Events</title>
<link rel="stylesheet" href="css/attendeeStyle.css?v=1">
</head>
<body>
	<nav class="navbar">
		<ul>
			<li><span>Home</span></li>

			<li><input type="text" placeholder="search" id="search"></li>
		</ul>
		<div class="logOutDiv">
			<h2>Welcome <%=session.getAttribute("attendee_name") %></h2>
			<a href="orgLogin.jsp">Logout</a>
		</div>
	</nav>
	<div class="containerBox">

		<div class="mainContainer">
			<div class="eventHeading">
				<h1 >Register Event</h1>
			</div>
			
			<div class="fieldColumns">
			<form action="AttendeeServlet" method="post" class="formContainer">
			
				<div class="formRow3">
					
				
					<div class="fields">
						<label for="eventType">Event Name</label>
						<select name="eventType" id="eventType">
    						<option value="">-- Select Event Type --</option>
    						<option value="Workshop">Workshop</option>
    						<option value="Seminar">Seminar</option>
   	 						<option value="Webinar">Webinar</option>
   	 						<option value="Concerts">Concerts</option>
						</select>
					</div>
				
				</div>
					</form>
			</div>
		</div>
	</div>
	
	
</body>
</html>