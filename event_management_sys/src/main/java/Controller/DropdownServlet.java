package Controller;

import jakarta.servlet.ServletException;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

import Dao.EventDao;
import Model.EventStatus;
import Model.EventType;

public class DropdownServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
   	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
   	{
   		HttpSession session = request.getSession(false);
   		if (session == null || session.getAttribute("organizer_id") == null) {
   		    response.sendRedirect("orgLogin.jsp"); // Or show error
   		    return;
   		}
        EventDao dao = new EventDao();
        List<EventType> eventTypes = dao.getEventType();
        List<EventStatus> eventStatuses = dao.getEventStatus();

        request.setAttribute("eventTypes", eventTypes);
        request.setAttribute("eventStatuses", eventStatuses);

        // Forward to event form JSP
        request.getRequestDispatcher("event.jsp").forward(request, response);

	
   	}
}
