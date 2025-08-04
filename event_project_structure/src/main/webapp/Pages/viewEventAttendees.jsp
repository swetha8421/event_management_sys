<%@page import="java.util.List"%>
<%@ page import="java.util.Set" %>
<%@ page import="java.util.HashSet" %>
<%
	List<Integer> feedbackGivenEventIds = (List<Integer>) request.getAttribute("feedbackGivenIds");
	Set<Integer> feedbackGivenEventSet = new HashSet<>(feedbackGivenEventIds); 
%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/home.css?v=5">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
</head>
<body>
	<nav class="navbar">
		<ul>
			<li>
				<a href="Pages/attendeeHomePage.jsp">
					<i class="fas fa-house" style="margin-right:5px; color:white; "></i>
				</a>
			</li>

			<li><input type="text" placeholder="search" id="search">

			</li>
		</ul>
		<div class="logOutDiv">
			<h2>Welcome <%=session.getAttribute("attendee_name") %></h2>
			<a href="Pages/attendeeLogin.jsp" >
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
							<th>Event Location</th>
							<th>Event Type</th>
							<th>Event Status</th>
							<th>Actions</th>
						</tr>
					</thead>
					<tbody>
					<%
						List<Model.Event> eventList = (List<Model.Event>) request.getAttribute("attendeeEventList");
						if(eventList !=null && !eventList.isEmpty()){
							for(Model.Event event:eventList){
						
					
					%>
					<tr>
							<td><%= event.getId() %></td>
							<td><%= event.getName() %></td>
							<td><%= event.getLocation() %></td>
							<td><%= event.getEvent_type() %></td>
							<td><%= event.getEvent_status() %></td>
							<td>  
								<%
									boolean feedbackGiven = feedbackGivenEventSet.contains(event.getId());
									if (feedbackGiven) 
									{
								%>
								<i class="fas fa-comment-dots" style="margin-left:5px; color: grey;" title="Feedback Already Submitted"></i>
								<%
								} 
								else 
								{
								%>
									<a href="${pageContext.request.contextPath}/AddFeedbackServlet?eventId=<%= event.getId() %>&attendeeId=<%= session.getAttribute("attendee_id") %>" title="Give Feedback">
									<i class="fas fa-comment-dots" style="margin-left:5px;"></i>
									</a>
								<%
									}
								%>
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