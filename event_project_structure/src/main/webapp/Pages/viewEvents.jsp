<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/home.css?v=5">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
</head>
<body>
	<nav class="navbar">
		<ul>
			<li>
				<a href="Pages/orgHomePage.jsp">
					<i class="fas fa-house" style="margin-right:5px; color:white; "></i>
					
				</a>
				
			</li>
			<li>
			<div style="display: flex; gap: 10px;">
				<!-- Event Type Filter -->
				<select name="filterType" id="filterType">
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
				<select name="filterStatus" id="filterStatus">
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
				
				<input type="text" name="search" placeholder="Search by name or location"  id="searchFilter"
       value="<%= request.getAttribute("searchTerm") != null ? request.getAttribute("searchTerm") : "" %>">
       
       
		<input type="date" name="startDate" placeholder="start date" id="startDate"
       				value="<%= request.getAttribute("selectedStartDate") != null ? request.getAttribute("selectedStartDate") : "" %>" />to


		<input type="date" name="endDate" placeholder="end Date" id="endDate"
       			value="<%= request.getAttribute("selectedEndDate") != null ? request.getAttribute("selectedEndDate") : "" %>" />

				<!-- Submit Button -->
				<button id="filterBtn" type="submit" style="padding: 3px 10px;">Filter</button>
				
				
				
			</div>
			
		</li>
						

			
		</ul>
		<div class="logOutDiv">
			<h2>Welcome <%=session.getAttribute("organizer_name") %></h2>
			<a href="Pages/orgLogin.jsp" >
  				<i class="fas fa-sign-out-alt" style="margin-right: 2px;"></i> 
			</a>
		</div>
	</nav>

	<div class="containerBox">
		<div class="mainContainer">
					<div class="eventTable" >
				<table border=1 id="eventTableBody">
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
								<a href="${pageContext.request.contextPath}/EventServlet?action=edit&eventId=<%= event.getId() %>" title="Edit"><i class="fas fa-edit"></i></a>

  								<a href="${pageContext.request.contextPath}/DeleteEventServlet?eventId=<%= event.getId() %>" 
       								onclick="return confirm('Are you sure you want to delete this event?');" 
       								title="Delete">
        							<i class="fas fa-trash-alt" style="color: red; margin-left:5px;"></i>
    							</a>
    							
    							<a href = "${pageContext.request.contextPath}/AddAttendeeServlet?eventId=<%= event.getId() %>" title="addAttendee"><i class="fas fa-user-plus" style="margin-left:5px;"></i></a>
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
	
	<script>
			function loadEvents(filterType,filterStatus,startDate,endDate,searchFilter)
			{
				$.ajax({
					url:"<%=request.getContextPath() %>/FilterEventServlet",
					method:"GET",
					data:{
						filterType:filterType,
						filterStatus:filterStatus,
						startDate:startDate,
						endDate:endDate,
						search:searchFilter
					},
					dataType:"json",
					success:function(data)
					{
						let tableBody = $("#eventTableBody tbody");
						console.log("Data",data);
						tableBody.empty();
						try{
							data.forEach(function(event)
									{
										let row = $("<tr>");
										row.append(
												 $("<td>").text(event.id),
									                $("<td>").text(event.name),
									                $("<td>").text(event.location),
									                $("<td>").text(event.startDate),
									                $("<td>").text(event.endDate),
									                $("<td>").text(event.event_type),
									                $("<td>").text(event.event_status),
											      $("<td>").html(`
											        <a href="${pageContext.request.contextPath}/EventServlet?action=edit&eventId=${event.id}" title="Edit"><i class="fas fa-edit"></i></a>
											        <a href="${pageContext.request.contextPath}/DeleteEventServlet?eventId=${event.id}" 
											          onclick="return confirm('Are you sure you want to delete this event?');" 
											          title="Delete">
											          <i class="fas fa-trash-alt" style="color: red; margin-left:5px;"></i>
											        </a>
											        <a href="${pageContext.request.contextPath}/AddAttendeeServlet?eventId=${event.id}" title="addAttendee"><i class="fas fa-user-plus" style="margin-left:5px;"></i></a>
											      `)
											    );
											    tableBody.append(row);
									});
						}
						catch(e)
						{
							console.error("Error Handling Rows");
						}
					},
					error:function(xhr,status,erro)
					{
						console.error("AJAX Error ",status,error);
					}
				});
			}
			$(document).ready(function(){
				$("#filterBtn").on("click",function()
						{
					 
							const selectedType = $("#filterType").val();
							const selectedStatus =$("#filterStatus").val();
							const selectedSearch = $("#searchFilter").val();
							const selectedStartDate = $("#startDate").val();
							const selectedEndDate = $("#endDate").val();
							
							console.log("Filter Clicked ",selectedType);
							loadEvents(selectedType,selectedStatus,selectedStartDate,selectedEndDate,selectedSearch)
						})
			})
	</script>
</body>
</html>