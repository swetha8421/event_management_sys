package Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

import Service.EventService;
import ServiceImpl.EventServiceImpl;
import Model.Event;

/**
 * Servlet implementation class DeleteEventServlet
 */
@WebServlet("/DeleteEventServlet")
public class DeleteEventServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EventService eventService = new EventServiceImpl();
       
    	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    	{
    		HttpSession session = request.getSession(false);
    		if(session==null || session.getAttribute("organizer_id")== null) 
    		{
    			response.sendRedirect("Pages/orgLogin.jsp");
    			return;
    		}
    		
    		int eventIdParam = Integer.parseInt(request.getParameter("eventId"));
    		int organizerId = (int)session.getAttribute("organizer_id");
    		if(eventIdParam != 0) 
    		{
    			
    			try 
    			{
    				
    				boolean deleted = eventService.deleteEventById(eventIdParam);
    		
    				if(deleted) 
    				{
    					List<Event> events = eventService.getAllEvents(organizerId);
    					request.setAttribute("eventList", events);
    					request.getRequestDispatcher("Pages/viewEvents.jsp").forward(request, response);
    				}
    				else 
    				{
    					request.setAttribute("message", "Failed to delete event");
    					request.getRequestDispatcher("Pages/viewEvents.jsp").forward(request, response);
    				}
    			}
    			catch(NumberFormatException e) 
    			{
    				 request.setAttribute("message", "Invalid event ID.");
    	                request.getRequestDispatcher("Pages/orgHomePage.jsp").forward(request, response);
    			}
    		}
    		else 
    		{
    			response.sendRedirect("Pages/orgHomePage.jsp");
    		}
	
		
    	}
	
    	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
