	<%@page import="java.util.List"%>
	<%@ page language="java" contentType="text/html; charset=UTF-8"
		pageEncoding="UTF-8"%>
	<!DOCTYPE html>
	<html>
	<head>
	<meta charset="UTF-8">
	<title>Home</title>
	<link rel="stylesheet" href="css/home.css?v=5">
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
				<li>
					<div class="filterSearchBox">
    					<form action="FilterFeedbackServlet" method="get">
        				<label for="rating">Filter by Rating:</label>
        				<select name="rating" id="rating">
            				<option value="">All</option>
            				<option value="1">1</option>
            				<option value="2">2</option>
            				<option value="3">3</option>
            				<option value="4">4</option>
            				<option value="5">5</option>
        				</select>

        			<label for="searchTerm">Search:</label>
        			<input type="text" name="searchTerm" id="searchTerm" placeholder="Event name or comments">

        			<input type="submit" value="Apply">
        			
        			<button href="viewFeedbacks.jsp">Clear</button>
    				</form>
				</div>
				</li>
			
			</ul>
			<div class="logOutDiv">
				<h2>Welcome <%=session.getAttribute("attendee_name") %></h2>
				<a href="attendeeLogin.jsp" >
	  				<i class="fas fa-sign-out-alt" style="margin-right: 2px;"></i> 
				</a>
			</div>
		</nav>
	
		<div class="containerBox">
			<div class="mainContainer">
						<div class="eventTable">
					<table border=1>
						<thead>
							<tr>
								<th>ID</th>
								<th>Event Name</th>
								<th>Rating</th>
								<th>Comments</th>
								
								<th>Actions</th>
							</tr>
						</thead>
						<tbody>
						<%
							List<Model.Feedback> feedbackList = (List<Model.Feedback>) request.getAttribute("feedbackList");
							if(feedbackList !=null && !feedbackList.isEmpty()){
								for(Model.Feedback feedback:feedbackList){
							
						
						%>
						<tr>
								<td><%= feedback.getId() %></td>
								<td><%= feedback.getEventName() %></td>
								<td><%= feedback.getRating() %></td>
								<td><%= feedback.getComments() %></td>
								
								<td> 
								 
									<a href="editFeedback.jsp?
									feedbackId=<%= feedback.getId() %>&
									eventName=<%= feedback.getEventName()%>&
									attendeeName=<%= session.getAttribute("attendee_name")%>&
									eventId=<%= feedback.getEventId()%>" title="Edit">
	    							<i class="fas fa-edit"></i>
	</a>
	    							
	    							
								</td>
	
							</tr>
	
					<%
								}
							}else{
					%>	
						<tr>
							<td colspan="8" style="text-align:center;">No Events Found,</td>
					
						</tr>		
					<%			
							}
					%>
					
	
	
						</tbody>
	
					</table>
	
				</div>
			</div>
		</div>
	</body>
	</html>