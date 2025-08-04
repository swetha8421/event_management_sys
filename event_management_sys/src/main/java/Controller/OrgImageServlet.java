package Controller;

import jakarta.servlet.ServletException;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

import Dao.OrgLoginDao;
public class OrgImageServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		int organizerId = Integer.parseInt(request.getParameter("id"));
		
		OrgLoginDao dao = new OrgLoginDao();
		byte[] imageData = dao.getOrganizerImageById(organizerId);
		
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

	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
