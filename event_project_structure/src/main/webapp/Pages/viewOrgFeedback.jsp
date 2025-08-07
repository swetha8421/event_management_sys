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
					<div class="filterSearchBox">
    					
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

        			<button id="filterBtn" type="submit" style="padding: 3px 10px;">Filter</button>
        			
        			<a href="${pageContext.request.contextPath}/ViewOrganizerFeedbackServlet?organizerId=<%= session.getAttribute("organizer_id") %>">
        				<button type="button">Clear</button>
        			</a>
    				
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
						<div class="eventTable">
						
					<table border=1 id="feedbackTable">
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
	<script>	
	  function getParameterByName(name) {
	        const url = new URL(window.location.href);
	        return url.searchParams.get(name);
	    }

			function loadFeedbacks(rating,searchTerm)
			{
				const organizer_id = getParameterByName("organizerId");
				$.ajax({
					    
					url:"<%=request.getContextPath() %>/ViewOrganizerFeedbackServlet",
					method:"GET",
					data:{
						rating:rating,
						searchTerm:searchTerm,
						organizerId:organizer_id
						
					},
					dataType:"json",
					success:function(data)
					{
						let tableBody = $("#feedbackTable tbody");
						console.log("Data",data);
						tableBody.empty();
						try{
							data.forEach(function(eventFeedback)
									{
										let row = $("<tr>");
										row.append(
												 
									                $("<td>").text(eventFeedback.eventName),
									                $("<td>").text(eventFeedback.eventLocation),
									                $("<td>").text(eventFeedback.attendeeName),
									                $("<td>").text(eventFeedback.rating),
									                $("<td>").text(eventFeedback.comments),
											      $("<td>").html(`
											    		  <form action="DeleteFeedbackServlet" method="post" style="display:inline;" 
						          							onsubmit="return confirm('Are you sure you want to delete this feedback?');">
						        								<input type="hidden" name="feedbackId" value="${eventFeedback.feedbackId}">
						        								<input type="hidden" name="organizerId" value="${sessionScope.organizer_id}">
						        									<button type="submit" title="Delete" style="background:none;border:none;cursor:pointer;">
						            									<i class="fas fa-trash-alt" style="color: red; margin-left:5px;"></i>
						        									</button>
						    								</form>
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
					error:function(xhr,status,error)
					{	
						console.error("AJAX Error ",status,error);
						console.log("Response text:", xhr.responseText); //
					}
				});
			}
			$(document).ready(function(){
				   
				$("#filterBtn").on("click",function()
						{
					 
							const selectedRating = $("#rating").val();
							const selectedSearch =$("#searchTerm").val();
							
							
							loadFeedbacks(selectedRating,selectedSearch);
						})
			})
	</script>
	</html>