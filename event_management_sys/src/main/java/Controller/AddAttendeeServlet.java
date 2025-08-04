package Controller;

import jakarta.servlet.ServletException;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import java.util.List;

import Dao.AttendeeDao;
import Model.Attendee;

public class AddAttendeeServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String eventIdParam = request.getParameter("eventId");
			if (eventIdParam == null || eventIdParam.isEmpty()) {
				request.setAttribute("message", "Event ID is missing.");
				request.getRequestDispatcher("viewEvents.jsp").forward(request, response);
				return;
			}

			int eventId = Integer.parseInt(eventIdParam);

			AttendeeDao dao = new AttendeeDao();
			List<Attendee> attendees = dao.getAllAttendees();

			request.setAttribute("eventId", eventId); // So it goes to JSP hidden field
			request.setAttribute("attendees", attendees);

			request.getRequestDispatcher("addAttendee.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "Unable to load attendee form.");
			request.getRequestDispatcher("viewEvents.jsp").forward(request, response);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    try {
	        int eventId = Integer.parseInt(request.getParameter("eventId"));
	        int attendeeId = Integer.parseInt(request.getParameter("attendeeId"));

	        AttendeeDao dao = new AttendeeDao();
	        boolean result = dao.assignAttendeeToEvent(attendeeId, eventId);

	        List<Attendee> attendees = dao.getAllAttendees(); 
	        request.setAttribute("attendees", attendees);
	        request.setAttribute("eventId", eventId);

	        if (result) {
	            request.setAttribute("message", "Attendee Assigned");
	        } else {
	            request.setAttribute("message", "Failed to assign attendee");
	        }
	        HttpSession session = request.getSession(false);
			session.setAttribute("organizer_id", session.getAttribute("organizer_id"));
			session.setAttribute("organizer_name", session.getAttribute("organizer_name"));
			
	        request.getRequestDispatcher("orgHomePage.jsp").forward(request, response);

	    } catch (Exception e) {
	        e.printStackTrace();
	        request.setAttribute("message", "Error assigning attendee.");
	        request.getRequestDispatcher("viewEvents.jsp").forward(request, response);
	    }
	}


}