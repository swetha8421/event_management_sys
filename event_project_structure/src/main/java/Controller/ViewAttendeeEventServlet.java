package Controller;

import jakarta.servlet.ServletException;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Service.AttendeeService;
import ServiceImpl.AttendeeServiceImpl;
import Model.Event;

public class ViewAttendeeEventServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	private AttendeeService attendeeService = new AttendeeServiceImpl();

		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
		{
			try 
			{
				int attendeeId = Integer.parseInt(request.getParameter("attendeeId"));
				System.out.println("attende_id "+attendeeId);
				
				List<Event> events = attendeeService.getEventByAttendeeId(attendeeId);
				List<Integer> feedbackGivenEventIds = attendeeService.getEventIdsWithFeedback(attendeeId);
				if (feedbackGivenEventIds == null) {
				    feedbackGivenEventIds = new ArrayList<>();
				}

				
				request.setAttribute("attendeeEventList", events);
				request.setAttribute("feedbackGivenIds", feedbackGivenEventIds);
				request.getRequestDispatcher("Pages/viewEventAttendees.jsp").forward(request, response);
				
				
				
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
