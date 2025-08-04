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
				<a href="orgHomePage.jsp">
					<i class="fas fa-house" style="margin-right:5px; color:white; "></i>
				</a>	
				
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
			<div class="eventHeading">
				<h1 >Add Event</h1>
			</div>
			
			<div class="fieldColumns">
			<form action="EventServlet" method="post" class="formContainer">
			
				<div class="formRow3">
					
					<div class="fields">
							<label for="name">Name:</label>
							<input type="text" id="name" name="name" required>
					</div>
				
					<div class="fields">
							<label for="eventLocation">Event Location:</label>
							<input type="text" id="eventLocation" name="eventLocation" required>
					</div>
				
					<div class="fields">
						<label for="eventType">Event Type</label>
						<select name="eventType" id="eventType">
    						<option value="">-- Select Event Type --</option>
    						<%
    							List<EventType> eventTypes = (List<EventType>)request.getAttribute("eventTypes");
								if(eventTypes != null)
								{
									for(EventType type:eventTypes)
									{
							%>
										<option value = "<%= type.getId()%>"><%=type.getType_name() %></option>
								<%
									}
								}
								 %>
						</select>
					</div>
				
				</div>
				<div class="formRow3">
					<div class="fields">
							<label for="startDate">Start Date:</label>
							<input type="date" id="startDate" name="startDate" required>
					</div>
				
					<div class="fields">
							<label for="endDate">End Date:</label>
							<input type="date" id="endDate" name="endDate" required>
					</div>
				
				
					<div class="fields">
							<label for="eventStatus">Event Status</label>
							<select name="eventStatus" id="eventStatus">
    							<option value="">-- Select Event Status --</option>
    							<%
    							List<Model.EventStatus> eventStatus = (List<Model.EventStatus>)request.getAttribute("eventStatuses");
								if(eventStatus != null)
								{
									for(Model.EventStatus status:eventStatus)
									{
							%>
										<option value = "<%= status.getId()%>"><%=status.getStatus_name() %></option>
								<%
									}
								}
								 %>
							</select>
					</div>
				
				</div>
				<div class="formRow3">
					<div class="fields">
							<label for="budget">Budget</label>
							<input type="number" id="budget" name="budget" required>
					</div>
				
				
					<div class="fields">
							<label for="noOfAttendees">Expected Attendees</label>
							<input type="number" id="noOfAttendees" name="noOfAttendees" required>
					</div>
					<div class="fields">
  							<label for="organizerID">Organizer ID</label>
  							<input type="number" id="organizerID" name="organizerID" 
         					value="<%= session.getAttribute("organizer_id") %>" readonly>
					</div>
				</div>
				
				<div class="formRowFull">
						<label for="description">Description</label>
						<textarea id="description" name="description" rows="4" cols="50"></textarea>
				</div>
				
				<div class="submitRow">
    					<button type="submit">Submit Event</button>
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