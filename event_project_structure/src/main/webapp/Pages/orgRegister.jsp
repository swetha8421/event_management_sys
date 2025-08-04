<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/loginRegister.css?v=1" >
</head>
<body>
	<div class="heading">
		<h1>Event Management System</h1>
	</div>
	<div class="container">
		
		<h2>Register</h2>
		<form action="${pageContext.request.contextPath}/OrgRegisterServlet" method="post" enctype="multipart/form-data">
			<div class="login">
				<div class="fields">
					<label for="name">Name:</label>
					<input type="text" id="name" name="name" required>
				</div>
				
				
				<div class="fields">
					<label for="email">Email:</label>
					<input type="email" id="email" name="email" required>
				</div>
				
				<div class="fields">
					<label for="password">Password:</label>
					<input type="password" id="password" name="password" required>
				</div>
				
				<div class="fields">
					<label for="phone">Phone:</label>
					<input type="text" id="phone" name="phone" required>
				</div>
				
				<div class="fields">
					<label for="phone">Image:</label>
					<input type="file" id="image" name="image" accept = "image/*" required>
				</div>
				
				
				<div class="fields">
					<label for="organization">Organization:</label>
					<input type="text" id="organization" name="organization" required>
				</div>
			</div>
			<div>
				<h4>
					Already Have One Login Here!  <a href="Pages/orgLogin.jsp">Login</a>
				</h4>
			</div>
			<div class="submitDiv">
				<button type="submit" class="submitBtn">Register</button>
			</div>
		</form>
		<%
    		String error = request.getParameter("error");
    		if (error != null) {
        	String errorMessage = "";
        	switch (error) 
        	{
            	case "emptyFields":
                	errorMessage = "Please fill in all required fields.";
                	break;
            	case "invalidEmail":
                	errorMessage = "Please enter a valid email address.";
                	break;
            	case "invalidPhone":
                	errorMessage = "Phone number must be 10 digits.";
                	break;
            	case "invalidImageType":
                	errorMessage = "Only image files are allowed (jpg, png, etc).";
                	break;
            	case "imageTooLarge":
                	errorMessage = "Image is too large. Max 2MB allowed.";
                	break;
            	case "insertFailed":
                	errorMessage = "Registration failed. Please try again.";
                	break;
        		}
%>
    <p style="color:red; margin-bottom: 10px;"><%= errorMessage %></p>
<%
    }
%>
	</div>

</body>
</html>