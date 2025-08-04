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
			<form action="FilterEventServlet" method="get" style="display: flex; gap: 10px;">
				<!-- Event Type Filter -->
				<select name="filterType">
					<option value="">All Types</option>
					<%
						List<Model.EventType> eventTypes = (List<Model.EventType>) request.getAttribute("eventTypes");
					Object selectedTypeObj = request.getAttribute("selectedType");
					String selectedType = selectedTypeObj != null ? selectedTypeObj.toString() : "";
					System.out.println("selectedType "+selectedType);
						if (eventTypes != null) {
							for (Model.EventType type : eventTypes) {
					%>
						<option value="<%= type.getId() %>" 
    						<%= (String.valueOf(type.getId()).equals(selectedType) ? "selected" : "") %>>
    						<%= type.getType_name() %>
						</option>
					<%
							}
						}
					%>
				</select>

				<!-- Event Status Filter -->
				<select name="filterStatus">
					<option value="">All Statuses</option>
					<%
						List<Model.EventStatus> eventStatuses = (List<Model.EventStatus>) request.getAttribute("eventStatuses");
					Object selectedStatusObj = request.getAttribute("selectedStatus");
					String selectedStatus = selectedStatusObj != null ? selectedStatusObj.toString() : "";
						if (eventStatuses != null) {
							for (Model.EventStatus status : eventStatuses) {
					%>
						<option value="<%= status.getId() %>" 
    							<%= (String.valueOf(status.getId()).equals(selectedStatus) ? "selected" : "") %>>
    							<%= status.getStatus_name() %>
						</option>
					<%
							}
						}
					%>
				</select>
				
				<input type="text" name="search" placeholder="Search by name or location" 
       value="<%= request.getAttribute("searchTerm") != null ? request.getAttribute("searchTerm") : "" %>">
       
       
		<input type="date" name="startDate" placeholder="start date"
       				value="<%= request.getAttribute("selectedStartDate") != null ? request.getAttribute("selectedStartDate") : "" %>" />to


		<input type="date" name="endDate" placeholder="end Date"
       			value="<%= request.getAttribute("selectedEndDate") != null ? request.getAttribute("selectedEndDate") : "" %>" />

				<!-- Submit Button -->
				<button type="submit" style="padding: 3px 10px;">Filter</button>
				
				
				
			</form>
			
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
							<th>ID</th>
							<th>Event Name</th>
							<th>Event Location</th>
							<th>start date</th>
							<th>end date</th>
							<th>Event Type</th>
							<th>Event Status</th>
							<th>Actions</th>
						</tr>
					</thead>
					<tbody>
					<%
						List<Model.Event> eventList = (List<Model.Event>) request.getAttribute("eventList");
						if(eventList !=null && !eventList.isEmpty()){
							for(Model.Event event:eventList){
						
					
					%>
					<tr>
							<td><%= event.getId() %></td>
							<td><%= event.getName() %></td>
							<td><%= event.getLocation() %></td>
							<td><%= event.getStartDate() %></td>
							<td><%= event.getEndDate() %></td>
							<td><%= event.getEvent_type()%></td>
							<td><%= event.getEvent_status() %></td>
							<td>  
								<a href="EventServlet?action=edit&eventId=<%= event.getId() %>" title="Edit"><i class="fas fa-edit"></i></a>

  								<a href="DeleteEventServlet?eventId=<%= event.getId() %>" 
       								onclick="return confirm('Are you sure you want to delete this event?');" 
       								title="Delete">
        							<i class="fas fa-trash-alt" style="color: red; margin-left:5px;"></i>
    							</a>
    							
    							<a href = "AddAttendeeServlet?eventId=<%= event.getId() %>" title="addAttendee"><i class="fas fa-user-plus" style="margin-left:5px;"></i></a>
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