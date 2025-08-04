<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/homePage.css?v=3">
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
			<h1 class="headingUser">Organizer Details</h1>
			<table border=1 class="userTable">
				<thead>
					<tr>
						<th>ID</th>
						<th>Name</th>
						<th>Profile</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td><%= session.getAttribute("organizer_id") %></td>
						<td><%= session.getAttribute("organizer_name") %></td>
						<td>
							<img src="${pageContext.request.contextPath}/OrgImageServlet?id=<%= session.getAttribute("organizer_id") %>"
								 width = "100" height="100" alt="Profile"
								 onerror = "this.src = 'default-profile.jpg'"
								 style="border-radius:50%; object-fit:cover;"/>
						</td>
					</tr>


				</tbody>
		</table>
		<div class="btnDiv">
			<div class="eventBtn">
				<a href="${pageContext.request.contextPath}/DropdownServlet">Add Event</a>
			</div>
			
			<div class="eventBtn">
				<a href="${pageContext.request.contextPath}/EventServlet">View Event</a>
			</div>
			
			
			<div class="eventBtn">
				<a href="${pageContext.request.contextPath}/ViewOrganizerFeedbackServlet?organizerId=<%= session.getAttribute("organizer_id") %>">View Feedbacks</a>
			</div>
		</div>
		</div>
		</div>
		
	
</body>
</html>