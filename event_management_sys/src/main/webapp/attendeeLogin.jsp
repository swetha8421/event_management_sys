<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<link rel="stylesheet" href="css/loginRegister.css?v=1" >
</head>
<body>
	<div class="heading">
		<h1>Event Management System</h1>
	</div>
	<div class="container">
		
		<h2>Login</h2>
		<form action="AttendeeLoginServlet" method="post">
			<div class="login">
				<div class="fields">
					<label for="email">Email:</label>
					<input type="email" id="email" name="email" required>
				</div>
				
				<div class="fields">
					<label for="password">Password:</label>
					<input type="password" id="password" name="password" required>
				</div>
				
			</div>	
			<%
			String error = request.getParameter("error");
    		if ("invalid".equals(error)) {
			%>
    			<div class="error-message">❌ Invalid email or password  Please try again.</div>
			<%
    		}
			%>
			
			<%
			
    		if ("invalidEmail".equals(error)) {
			%>
    			<div class="error-message">❌ Invalid email Please try again.</div>
			<%
    		}
			%>
    		

			<div>
				<h4>
					Not Have a Account! Create One  <a href="attendeeRegister.jsp">Register</a>
				</h4>
				<a href="<%= request.getContextPath() %>/" style="padding: 8px 16px; background-color: #007bff; color: white; text-decoration: none; border-radius: 4px;">Go to Home</a>
			</div>
			<div class="submitDiv">
    			<button type="submit" class="submitBtn">Login</button>
			</div>
		</form>
	
	</div>

</body>
</html>