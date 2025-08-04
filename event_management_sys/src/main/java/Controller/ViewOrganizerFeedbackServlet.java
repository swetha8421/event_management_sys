package Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import Dao.FeedbackDao;
import Model.EventFeedback;

public class ViewOrganizerFeedbackServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

	

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String organizerIdStr = request.getParameter("organizerId");
        String ratingStr = request.getParameter("rating");
        String searchTerm = request.getParameter("searchTerm");

        try {
            int organizerId = Integer.parseInt(organizerIdStr);

            List<EventFeedback> eventFeedbackList = FeedbackDao.getFilteredFeedback(
                organizerId, ratingStr, searchTerm);

            request.setAttribute("eventFeedbackList", eventFeedbackList);
            request.getRequestDispatcher("viewOrgFeedback.jsp").forward(request, response);

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
