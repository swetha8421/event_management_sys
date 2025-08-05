<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/home.css?v=3">
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
			<h2>Welcome <%= session.getAttribute("attendee_name")%></h2>
			<a href="attendeeLogin.jsp">Logout</a>			
		</div>
	</nav>
	<div class="containerBox">
		
		<div class="mainContainer">
			
			<table border=1 class="userTable">
				<thead>
					<tr>
						
						<th>Name</th>
						<th>Profile</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						
						<td><%= session.getAttribute("attendee_name") %></td>
						<td>
								<img src="${pageContext.request.contextPath}/AttendeeImageServlet?id=<%= session.getAttribute("attendee_id") %>"
								 width = "140" height="140" alt="Profile"
								 onerror = "this.src = 'default-profile.jpg'"
								 style="border-radius:50%; object-fit:cover;"/>
						</td>
					</tr>


				</tbody>
				</table>
				<div class="buttons">
			<div class="addEventBtn">
				<a href="${pageContext.request.contextPath}/ViewAttendeeEventServlet?attendeeId=<%=session.getAttribute("attendee_id")%>">View Event</a>
			</div>
			
			
			<div class="addEventBtn">
				<a href="${pageContext.request.contextPath}/ViewFeedbackServlet?attendeeId=<%=session.getAttribute("attendee_id")%>">View Feedbacks</a>
			</div>
			</div>
				
			
		</div>
	</div>
</body>
</html>