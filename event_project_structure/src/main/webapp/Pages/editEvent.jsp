<%@page import="Model.EventType"%>
<%@page import="Model.EventStatus"%>
<%@page import="java.util.List"%>
<%@page import="DaoImpl.EventDaoImpl"%>
<%@page import="Model.Event"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Events</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/eventStyle.css?v=2">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
</head>
<body>
	<nav class="navbar">
		<ul>
			<li>
			<a href="Pages/orgHomePage.jsp">
					<i class="fas fa-house" style="margin-right:5px; color:white; "></i>
				</a>
			</li>

			<li><input type="text" placeholder="search" id="search"></li>
		</ul>
		<div class="logOutDiv">
			<h2>Welcome <%=session.getAttribute("organizer_name")%></h2>
			<a href="Pages/orgLogin.jsp" >
  				<i class="fas fa-sign-out-alt" style="margin-right: 2px;"></i> 
			</a>
		</div>
	</nav>
	<%
	String eventIdParam =  request.getParameter("eventId");
			Event event = null;
			
			if(eventIdParam != null && !eventIdParam.isEmpty())
			{
		int eventId = Integer.parseInt(eventIdParam);
		event = new EventDaoImpl().getEventById(eventId);
			}
	%>
	<div class="containerBox">

		<div class="mainContainer">
			<div class="eventHeading">
				<h1 >Edit Event</h1>
			</div>
			
			<div class="fieldColumns">
			<form action="${pageContext.request.contextPath}/EventServlet" method="post" class="formContainer">
				<input type="hidden" name="_method" value="put">
				<input type="hidden" name="eventId" value="<%= event.getId() %>">
				<div class="formRow3">
					
					<div class="fields">
							<label for="name">Name:</label>
							<input type="text" id="name" name="name" value = "<%=event!=null ? event.getName() : "" %>" required>
					</div>
				
					<div class="fields">
							<label for="eventLocation">Event Location:</label>
							<input type="text" id="eventLocation" name="eventLocation" value= "<%=event!=null ? event.getLocation():" " %>"required>
					</div>
				
					<div class="fields">
						<label for="eventType">Event Type</label>
						<%
    						List<EventType> eventTypes = (List<EventType>) request.getAttribute("eventTypes");
						%>
						<select name="eventType" id="eventType">
    							<option value="">-- Select Event Type --</option>
    					<%
        					if (eventTypes != null) {
            					for (EventType type : eventTypes) {
                					boolean selected = (event != null && event.getEvent_type().equals(type.getType_name()));
    					%>
                			<option value="<%= type.getId() %>" <%= selected ? "selected" : "" %>>
                    			<%= type.getType_name() %>
                			</option>
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
							<input type="date" id="startDate" name="startDate" value="<%=event !=null ? event.getStartDate():" " %>" required>
					</div>
				
					<div class="fields">
							<label for="endDate">End Date:</label>
							<input type="date" id="endDate" name="endDate" value="<%=event !=null ? event.getEndDate():" " %>"  required>
					</div>
				
				
					<div class="fields">
							<label for="eventStatus">Event Status</label>
							<%
    							List<EventStatus> eventStatuses = (List<EventStatus>) request.getAttribute("eventStatuses");
							%>
							<select name="eventStatus" id="eventStatus">
    							<option value="">-- Select Event Status --</option>
    						<%
        						if (eventStatuses != null) {
            						for (EventStatus status : eventStatuses) {
                						boolean selected = (event != null && event.getEvent_status().equals(status.getStatus_name()));
    						%>
                				<option value="<%= status.getId() %>" <%= selected ? "selected" : "" %>>
                    				<%= status.getStatus_name() %>
                				</option>
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
							<input type="number" id="budget" name="budget" value = "<%= event!=null ? event.getBudget() : " " %>" required>
					</div>
				
				
					<div class="fields">
							<label for="noOfAttendees">Expected Attendees</label>
							<input type="number" id="noOfAttendees" name="noOfAttendees" value= "<%= event != null ? event.getExpectedAttendees():" " %>"required>
					</div>
					<div class="fields">
  							<label for="organizerID">Organizer ID</label>
  							<input type="number" id="organizerID" name="organizerID" 
         					value="<%= session.getAttribute("organizer_id") %>" readonly>
					</div>
				</div>
				
				<div class="formRowFull">
						<label for="description">Description</label>
						<textarea id="description" name="description" rows="4" cols="50" ><%= event != null ? event.getDescription() : "" %></textarea>
				</div>
				
				<div class="submitRow">
    					<button type="submit">Update</button>
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