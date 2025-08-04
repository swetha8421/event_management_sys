package Controller;

import jakarta.servlet.ServletException;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import Service.FeedbackService;
import ServiceImpl.FeedbackServiceImpl;
import Model.EventFeedback;

public class ViewOrganizerFeedbackServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	private FeedbackService feedbackService = new FeedbackServiceImpl();
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String organizerIdStr = request.getParameter("organizerId");
        String ratingStr = request.getParameter("rating");
        String searchTerm = request.getParameter("searchTerm");

        try {
            int organizerId = Integer.parseInt(organizerIdStr);

            List<EventFeedback> eventFeedbackList = feedbackService.getFilteredFeedback(
                organizerId, ratingStr, searchTerm);

            request.setAttribute("eventFeedbackList", eventFeedbackList);
            request.getRequestDispatcher("Pages/viewOrgFeedback.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            response.getWriter().println("Invalid organizer ID.");
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
