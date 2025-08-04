package Controller;

import jakarta.servlet.ServletException;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import Dao.AttendeeDao;
import Dao.EventDao;
import Dao.FeedbackDao;
import Model.Attendee;
import Model.Event;
import Model.Feedback;

/**
 * Servlet implementation class AddFeedbackServlet
 */

public class AddFeedbackServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    
		
	    String eventId = request.getParameter("eventId");
	    String attendeeId = request.getParameter("attendeeId");
	    System.out.println("attendee id"+attendeeId);
	    System.out.println("event id"+eventId);
	    int event_id = Integer.parseInt(eventId);
	    int attendee_id = Integer.parseInt(attendeeId);
	    
	    request.setAttribute("eventId", eventId);
	    request.setAttribute("attendeeId", attendeeId);
	    
	    EventDao dao = new EventDao();
	    Event event = dao.getEventById(event_id);
	    
	    AttendeeDao daoAttendee = new AttendeeDao();
	    Attendee attendee = daoAttendee.getAttendeeById(attendee_id);
	    System.out.println(attendee.getName());
	    
	    request.setAttribute("eventName", event.getName());
	    request.setAttribute("attendeeName", attendee.getName());
	    request.getRequestDispatcher("AddFeedback.jsp").forward(request, response);
	}
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		try 
		{
			int eventId = Integer.parseInt(request.getParameter("eventId"));
			int attendeeId = Integer.parseInt(request.getParameter("attendeeId"));
			String attendeeName = request.getParameter("attendeeName");
			int rating = Integer.parseInt(request.getParameter("rating"));
			
			String comments = request.getParameter("comments");
			
			
			
			Feedback feedback = new Feedback();
			feedback.setEventId(eventId);
			feedback.setAttendeeId(attendeeId);
			feedback.setAttendeeName(attendeeName);
			feedback.setRating(rating);
		
			feedback.setComments(comments);
			
			FeedbackDao dao = new FeedbackDao();
			boolean result = dao.insertFeedback(feedback);
			if(result) 
			{
				request.setAttribute("message", "added feedback");
				List<Feedback> feedbackList = dao.getFeedbackByAttendeeId(attendeeId);
				request.setAttribute("feedbackList", feedbackList);
				request.getRequestDispatcher("viewFeedbacks.jsp").forward(request, response);
			}
		}
		catch(NumberFormatException e) 
		{
			e.printStackTrace();
			
		}
			
	}
	}
	