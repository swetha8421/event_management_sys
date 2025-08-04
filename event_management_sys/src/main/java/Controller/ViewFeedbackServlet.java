package Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import Dao.FeedbackDao;
import Model.Feedback;

public class ViewFeedbackServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		int attendeeId = Integer.parseInt(request.getParameter("attendeeId"));
		FeedbackDao dao = new FeedbackDao();
		
		List<Feedback> feedbacks = dao.getFeedbackByAttendeeId(attendeeId);
		if(!feedbacks.isEmpty()) 
		{
			request.setAttribute("feedbackList", feedbacks);
			request.getRequestDispatcher("viewFeedbacks.jsp").forward(request, response);
		}
		else 
		{
			request.setAttribute("message", "Failed to delete event");
			request.getRequestDispatcher("attendeeHomePge.jsp").forward(request, response);
			
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
