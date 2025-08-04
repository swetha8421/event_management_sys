package Controller;

import java.io.IOException;
import java.io.InputStream;


import Model.Attendee;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import Service.AttendeeRegisterService;
import ServiceImpl.AttendeeRegisterServiceImpl;

@MultipartConfig
public class AttendeeRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AttendeeRegisterService attendeeRegisterService = new AttendeeRegisterServiceImpl();

	public AttendeeRegisterServlet() {
        super();

    }

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String name=request.getParameter("name");
		String email=request.getParameter("email");
		String password = request.getParameter("password");
		String mobile = request.getParameter("mobile");
		Part image = request.getPart("image");

		
		if(name == null || email == null || password == null ||
				mobile == null ) 
		{
			response.sendRedirect("Pages/orgRegister.jsp?error=emptyFields");
	        return;
		}
		
		 if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
		        response.sendRedirect("Pages/attendeeRegister.jsp?error=invalidEmail");
		        return;
		    }
		 if (!mobile.matches("\\d{10}")) {
		        response.sendRedirect("Pages/attendeeRegister.jsp?error=invalidMobile");
		        return;
		    }
		
		
		
		 InputStream imageStream = null;
		    if (image != null && image.getSize() > 0) {
		        String contentType = image.getContentType();
		        long size = image.getSize();

		        if (!contentType.startsWith("image/")) {
		            response.sendRedirect("Pages/attendeeRegister.jsp?error=invalidImageType");
		            return;
		        }
		        if (size > 2 * 1024 * 1024) { // 2MB limit
		            response.sendRedirect("Pages/attendeeRegister.jsp?error=imageTooLarge");
		            return;
		        }
		        imageStream = image.getInputStream();
		    }
		
		

		
		Attendee attendee = new Attendee();

		attendee.setName(name);
		attendee.setEmail(email);
		attendee.setPassword(password);
		attendee.setMobile(mobile);
		

		
		boolean result = attendeeRegisterService.insertAttendee(attendee,imageStream);

		if(result) {
			response.sendRedirect("Pages/attendeeLogin.jsp");
		}
		else {
			response.sendRedirect("Pages/attendeeRegister.jsp?error=failed");
		}


	}

}
