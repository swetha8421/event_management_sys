<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register</title>
<link rel="stylesheet" href="css/loginRegister.css?v=1">
</head>
<body>
	<div class="heading">
		<h1>Event Management System</h1>
	</div>
	<div class="container">

		<h2>Register</h2>
		<form action="AttendeeRegisterServlet" method="post"
			enctype="multipart/form-data">
			<div class="login">
				<div class="fields">
					<label for="name">Name:</label> <input type="text" id="name"
						name="name" required>
				</div>


				<div class="fields">
					<label for="email">Email:</label> <input type="email" id="email"
						name="email" required>
				</div>

				<div class="fields">
					<label for="password">Password:</label> <input type="password"
						id="password" name="password" required>
				</div>

				<div class="fields">
					<label for="mobile">Mobile:</label> <input type="text" id="mobile"
						name="mobile" required>
				</div>

				<div class="fields">
					<label for="phone">Image:</label> <input type="file" id="image"
						name="image" accept="image/*" required>
				</div>

			</div>
			<div>
				<h4>
					Already Have One Login Here! <a href="attendeeLogin.jsp">Login</a>
				</h4>
			</div>
			<%
    			String error = request.getParameter("error");
    			if (error != null) {
        		String errorMessage = "";
        		switch (error) 
        		{
            case "emptyFields":
                errorMessage = "All fields are required.";
                break;
            case "invalidEmail":
                errorMessage = "Please enter a valid email address.";
                break;
            case "invalidMobile":
                errorMessage = "Mobile number must be 10 digits.";
                break;
            case "invalidImageType":
                errorMessage = "Only image files are allowed.";
                break;
            case "imageTooLarge":
                errorMessage = "Image is too large. Max 2MB allowed.";
                break;
            case "insertFailed":
                errorMessage = "Registration failed. Please try again.";
                break;
        }
%>
			<p style="color: red; margin-bottom: 10px;"><%= errorMessage %></p>
			<%
    }
%>
			<div class="submitDiv">
				<button type="submit" class="submitBtn">Register</button>
			</div>
		</form>

	</div>

</body>
</html>