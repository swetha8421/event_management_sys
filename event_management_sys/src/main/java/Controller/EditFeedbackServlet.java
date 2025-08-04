package Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import Dao.EventDao;
import Dao.FeedbackDao;
import Model.Event;
import Model.EventStatus;
import Model.EventType;
import Model.Feedback;


public class EditFeedbackServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method = request.getParameter("_method");
		if("put".equalsIgnoreCase(method)) 
		{
			update(request,response);
		}
	}
	
	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
		
		try {
		

		int rating = Integer.parseInt(request.getParameter("rating"));
		String comments = request.getParameter("comments");
		String attendeeName = request.getParameter("attendeeName");
		int feedbackId = Integer.parseInt(request.getParameter("feedbackId"));
		int eventId = Integer.parseInt(request.getParameter("eventId"));
		int attendeeId = Integer.parseInt(request.getParameter("attendeeId"));
		
		System.out.println("feedbackId "+feedbackId);
		System.out.println("eventId "+eventId);
		System.out.println("attendeeId "+attendeeId);
		
		
		Feedback feedback = new Feedback();
		FeedbackDao dao = new FeedbackDao();
		
		feedback.setComments(comments);
		feedback.setRating(rating);
		feedback.setAttendeeName(attendeeName);
		feedback.setEventId(eventId);
		feedback.setAttendeeId(attendeeId);
		boolean result = dao.updateByFeedbackByid(feedback, feedbackId);
		
		if(result) 
		{
			System.out.println(result);
			
			List<Feedback> feedbackList = dao.getFeedbackByAttendeeId(attendeeId);
			request.setAttribute("feedbackList", feedbackList);
			request.getRequestDispatcher("viewFeedbacks.jsp").forward(request, response);
			
			
		}
		else 
		{
			System.out.println(result);
			request.setAttribute("message", "Failed to Update event");
			
			
		}
		
		}
		catch(Exception e) 
		{
			e.printStackTrace();
			
		}
	}

}
