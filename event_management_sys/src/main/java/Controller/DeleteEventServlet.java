package Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

import Dao.EventDao;
import Model.Event;

/**
 * Servlet implementation class DeleteEventServlet
 */
@WebServlet("/DeleteEventServlet")
public class DeleteEventServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    	{
    		HttpSession session = request.getSession(false);
    		if(session==null || session.getAttribute("organizer_id")== null) 
    		{
    			response.sendRedirect("orgLogin.jsp");
    			return;
    		}
    		
    		int eventIdParam = Integer.parseInt(request.getParameter("eventId"));
    		int organizerId = (int)session.getAttribute("organizer_id");
    		if(eventIdParam != 0) 
    		{
    			
    			try 
    			{
    				EventDao dao = new EventDao();
    				boolean deleted = dao.deleteEventById(eventIdParam);
    		
    				if(deleted) 
    				{
    					List<Event> events = dao.getAllEvents(organizerId);
    					request.setAttribute("eventList", events);
    					request.getRequestDispatcher("viewEvents.jsp").forward(request, response);
    				}
    				else 
    				{
    					request.setAttribute("message", "Failed to delete event");
    					request.getRequestDispatcher("viewEvents.jsp").forward(request, response);
    				}
    			}
    			catch(NumberFormatException e) 
    			{
    				 request.setAttribute("message", "Invalid event ID.");
    	                request.getRequestDispatcher("orgHomePage.jsp").forward(request, response);
    			}
    		}
    		else 
    		{
    			response.sendRedirect("orgHomePage.jsp");
    		}
	
		
    	}
	
    	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
