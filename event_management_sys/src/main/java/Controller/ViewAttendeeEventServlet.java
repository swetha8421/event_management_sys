package Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Dao.AttendeeDao;
import Model.Event;

public class ViewAttendeeEventServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
		{
			try 
			{
				int attendeeId = Integer.parseInt(request.getParameter("attendeeId"));
				System.out.println("attende_id "+attendeeId);
				AttendeeDao dao = new AttendeeDao();
				List<Event> events = dao.getEventByAttendeeId(attendeeId);
				List<Integer> feedbackGivenEventIds = dao.getEventIdsWithFeedback(attendeeId);
				if (feedbackGivenEventIds == null) {
				    feedbackGivenEventIds = new ArrayList<>();
				}

				
				request.setAttribute("attendeeEventList", events);
				request.setAttribute("feedbackGivenIds", feedbackGivenEventIds);
				request.getRequestDispatcher("viewEventAttendees.jsp").forward(request, response);
				
				
				
			}
			catch(Exception e) 
			{
				e.printStackTrace();
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
