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
					<a href="orgHomePage.jsp">
						<i class="fas fa-house" style="margin-right:5px; color:white; "></i>
						
					</a>
					
				</li>
				<li>
					<div class="filterSearchBox">
    					<form action="ViewOrganizerFeedbackServlet" method="get">
        				   <input type="hidden" name="organizerId" value="<%= session.getAttribute("organizer_id") %>">
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
        			
        			<a href="ViewOrganizerFeedbackServlet?organizerId=<%= session.getAttribute("organizer_id") %>">
        				<button type="button">Clear</button>
        			</a>
    				</form>
				</div>
				</li>
			
			</ul>
			<div class="logOutDiv">
				<h2>Welcome <%=session.getAttribute("organizer_name") %></h2>
				<a href="orgLogin.jsp" >
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
								
								<th>Event Name</th>
								<th>Event Location</th>
								<th>Attendee Name</th>
								<th>Rating</th>
								<th>Comments</th>
								<th>Actions</th>
								
								
							</tr>
						</thead>
						<tbody>
						<%
							List<Model.EventFeedback> eventFeedbackList = (List<Model.EventFeedback>) request.getAttribute("eventFeedbackList");
							if(eventFeedbackList !=null && !eventFeedbackList.isEmpty()){
								for(Model.EventFeedback eventFeedback:eventFeedbackList){
							
						
						%>
						<tr>
								<td><%= eventFeedback.getEventName() %></td>
								<td><%= eventFeedback.getEventLocation() %></td>
								<td><%= eventFeedback.getAttendeeName() %></td>
								<td><%= eventFeedback.getRating()  %></td>
								<td><%= eventFeedback.getComments()  %></td>
								<td>
									<form action="DeleteFeedbackServlet" method="post" style="display:inline;" 
          							onsubmit="return confirm('Are you sure you want to delete this feedback?');">
        								<input type="hidden" name="feedbackId" value="<%= eventFeedback.getFeedbackId() %>">
        								<input type="hidden" name="organizerId" value="<%= session.getAttribute("organizer_id") %>">
        									<button type="submit" title="Delete" style="background:none;border:none;cursor:pointer;">
            									<i class="fas fa-trash-alt" style="color: red; margin-left:5px;"></i>
        									</button>
    								</form>
								</td>
	
							</tr>
	
					<%
								}
							}else{
					%>	
						<tr>
							<td colspan="8" style="text-align:center;">No Feedbacks Found,</td>
					
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