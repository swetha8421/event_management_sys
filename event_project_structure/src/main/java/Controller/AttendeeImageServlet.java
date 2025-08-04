package Controller;

import jakarta.servlet.ServletException;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

import Service.AttendeeLoginService;
import ServiceImpl.AttendeeLoginServiceImpl;



public class AttendeeImageServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	private AttendeeLoginService attendeeLoginService = new AttendeeLoginServiceImpl();
       
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
		int attendeeId = Integer.parseInt(request.getParameter("id"));
		
		
		byte[] imageData = attendeeLoginService.getAttendeeImageById(attendeeId);
		
		if(imageData != null) 
		{
			response.setContentType("image/jpeg");
			OutputStream out = response.getOutputStream();
			
			out.write(imageData);
			out.flush();
		}
		else 
		{
			  response.sendRedirect("default-profile.jpg"); 
			
		}
		
	
	}
    
}
