package Controller;

import jakarta.servlet.ServletException;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

import Service.EventService;
import ServiceImpl.EventServiceImpl;
import Model.EventStatus;
import Model.EventType;

public class DropdownServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	private EventService eventService = new EventServiceImpl();
   	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
   	{
   		HttpSession session = request.getSession(false);
   		if (session == null || session.getAttribute("organizer_id") == null) {
   		    response.sendRedirect("Pages/orgLogin.jsp"); // Or show error
   		    return;
   		}
        
        List<EventType> eventTypes = eventService.getEventType();
        List<EventStatus> eventStatuses = eventService.getEventStatus();

        request.setAttribute("eventTypes", eventTypes);
        request.setAttribute("eventStatuses", eventStatuses);

        // Forward to event form JSP
        request.getRequestDispatcher("Pages/event.jsp").forward(request, response);

	
   	}
}
