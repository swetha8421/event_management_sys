<%@page import="Dao.FeedbackDao"%>
<%@page import="Model.Feedback"%>
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
			<a href="attendeeLogin.jsp" >
  				<i class="fas fa-sign-out-alt" style="margin-right: 2px;"></i> 
			</a>
		</div>
	</nav>
	
	
	<%
		String feedbackIdParam =  request.getParameter("feedbackId");
		Feedback feedback = null;
		
		if(feedbackIdParam != null && !feedbackIdParam.isEmpty())
		{
			int feedbackId = Integer.parseInt(feedbackIdParam);
			feedback = new FeedbackDao().getFeedbackById(feedbackId);
		}
		
		
	
	%>
	<div class="containerBox">

		<div class="mainContainer">
			<div class="eventHeading">
				<h1 >Add Feedback</h1>
			</div>
			
			<div class="fieldColumns">
			<form action="EditFeedbackServlet" method="post" class="formContainer">
				
				<input type="hidden" id="attendeeId" name="attendeeId" value = "<%=feedback!=null ? feedback.getAttendeeId() : "" %>"> 
				<input type="hidden" id="eventId" name="eventId" value = "<%=feedback!=null ? feedback.getEventId() : "" %>" > 
				<input type="hidden" name="_method" value="put">
				<input type="hidden" name="feedbackId"  value="<%= request.getParameter("feedbackId")%>">
				<div class="formRow3">
					
					<div class="fields">
							<label for="attendeeName">Attendee Name:</label>
							<input type="text" id="attendeeName" name="attendeeName"  value="<%= request.getParameter("attendeeName")%>" required readonly>
					</div>
				
					<div class="fields">
							<label for="eventName">Event Name:</label>
							<input type="text" id="eventName" name="eventName"  value="<%= request.getParameter("eventName") %>" readonly> 
					</div>
				
				</div>
				<div class="formRow3">
					
					
					<div class="fields">
							<label for="rating">Rating</label>
							<input type="number" id="rating" name="rating" value = "<%=feedback!=null ? feedback.getRating() : "" %>" required>
					</div>
					
					
					
				<div class="formRowFull">
						<label for="comments">Comments</label>
						<textarea id="comments" name="comments" rows="4" cols="50" ><%=feedback!=null ? feedback.getComments() : "" %></textarea>
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