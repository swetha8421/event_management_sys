package Controller;

import jakarta.servlet.ServletException;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import Service.FeedbackService;
import ServiceImpl.FeedbackServiceImpl;
import Model.Feedback;

public class ViewFeedbackServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	private FeedbackService feedbackService = new FeedbackServiceImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		int attendeeId = Integer.parseInt(request.getParameter("attendeeId"));
		
		
		List<Feedback> feedbacks = feedbackService.getFeedbackByAttendeeId(attendeeId);
		if(!feedbacks.isEmpty()) 
		{
			request.setAttribute("feedbackList", feedbacks);
			request.getRequestDispatcher("Pages/viewFeedbacks.jsp").forward(request, response);
		}
		else 
		{
			request.setAttribute("message", "Failed to delete event");
			request.getRequestDispatcher("Pages/attendeeHomePge.jsp").forward(request, response);
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
