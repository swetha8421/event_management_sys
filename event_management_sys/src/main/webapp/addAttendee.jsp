<%@page import="Model.Attendee"%>
<%@page import="Model.EventType"%>
<%@page import="Model.EventStatus"%>
<%@page import="java.util.List"%>
<%@page import="Dao.EventDao"%>
<%@page import="Model.Event"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Events</title>

<link rel="stylesheet" href="css/addAttendeeStyle.css?v=2">
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
	<%
		String eventIdParam =  request.getParameter("eventId");
		Event event = null;
		
		if(eventIdParam != null && !eventIdParam.isEmpty())
		{
			int eventId = Integer.parseInt(eventIdParam);
			event = new EventDao().getEventById(eventId);
		}
		
		
	
	%>
	<div class="containerBox">

		<div class="mainContainer">
		
			<div class="eventHeading">
				<h1 >Add Attendee</h1>
			</div>
		<form action="AddAttendeeServlet" method="post" class="formContainer">	
		<div class="fields">
    		<label for="attendeeId">Select Attendee</label>
    		  <% if (event != null) { %>
    				<input type="hidden" name="eventId" value="<%= event.getId() %>">
			<% } 
    		  else 
    		  { %>
    			<p style="color:red;">Event not found!</p>
	<% } %>
    	<%
        	List<Model.Attendee> attendees = (List<Model.Attendee>) request.getAttribute("attendees");
    	%>
    	<select name="attendeeId" id="attendeeId" required>
        <option value="">-- Select Attendee --</option>
        <%
            if (attendees != null) {
                for (Model.Attendee attendee : attendees) {
        %>
            <option value="<%= attendee.getId() %>">
                <%= attendee.getName() %>
            </option>
        <%
                }
            }
        %>
    		</select>
		</div>
		
		<div class="submitRow">
    					<button type="submit">Add Attendee</button>
  				</div>

			</form>
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