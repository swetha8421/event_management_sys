<%@page import="Model.EventType"%>
<%@page import="Model.EventStatus"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Events</title>
<link rel="stylesheet" href="css/eventStyle.css?v=2">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
</head>
<body>
	<nav class="navbar">
		<ul>
			<li>
				<a href="attendeeHomePage.jsp">
					<i class="fas fa-house" style="margin-right:5px; color:white; "></i>
				</a>	
				
			</li>

			
		</ul>
		<div class="logOutDiv">
			<h2>Welcome <%=session.getAttribute("attendee_name") %></h2>
			<a href="orgLogin.jsp" >
  				<i class="fas fa-sign-out-alt" style="margin-right: 2px;"></i> 
			</a>
		</div>
	</nav>
	<div class="containerBox">

		<div class="mainContainer">
			<div class="eventHeading">
				<h1 >Add Feedback</h1>
			</div>
			
			<div class="fieldColumns">
			<form action="AddFeedbackServlet" method="post" class="formContainer">
				<input type="hidden" id="eventId" name="eventId" value="<%=request.getAttribute("eventId")%>"> 
				<input type="hidden" id="attendeeId" name="attendeeId" value="<%=request.getAttribute("attendeeId")%>"> 
				<div class="formRow3">
					
					<div class="fields">
							<label for="attendeeName">Attendee Name:</label>
							<input type="text" id="attendeeName" name="attendeeName"  value="<%= request.getAttribute("attendeeName")%>" required>
					</div>
				
					<div class="fields">
							<label for="eventName">Event Name:</label>
							<input type="text" id="eventName" name="eventName"  value="<%= request.getAttribute("eventName") %>"> 
					</div>
				
				</div>
				<div class="formRow3">
					
					
					<div class="fields">
							<label for="rating">Rating</label>
							<input type="number" id="rating" name="rating" required>
					</div>
					
					
					
					
				<div class="formRowFull">
						<label for="comments">Comments</label>
						<textarea id="comments" name="comments" rows="4" cols="50"></textarea>
				</div>
				
				
						<div class="submitRow">
    					<button type="submit">Submit Feedback</button>
  				</div>
  				</div>
			</form>
			</div>
		</div>
	</div>
	<%
   		 String message = (String) request.getAttribute("message");
    	if (message != null) {
	%>
    <script>
        alert("<%= message.replaceAll("\"", "\\\\\"") %>");
    </script>
	<%
    	}
	%>

	
</body>
</html>