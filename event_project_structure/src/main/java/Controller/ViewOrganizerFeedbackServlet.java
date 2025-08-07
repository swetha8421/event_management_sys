package Controller;

import jakarta.servlet.ServletException;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import Service.FeedbackService;
import ServiceImpl.FeedbackServiceImpl;
import Model.Event;
import Model.EventFeedback;

public class ViewOrganizerFeedbackServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	private FeedbackService feedbackService = new FeedbackServiceImpl();
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String organizerIdStr = request.getParameter("organizerId");
        System.out.println("org "+organizerIdStr);
        String ratingStr = request.getParameter("rating");
        String searchTerm = request.getParameter("searchTerm");

        try {
            int organizerId = Integer.parseInt(organizerIdStr);

            List<EventFeedback> eventFeedbackList = feedbackService.getFilteredFeedback(
                organizerId, ratingStr, searchTerm);
            

            String requestedWidth = request.getHeader("X-Requested-With");
            
            if (organizerId == 0) {
                response.getWriter().write("Invalid or no organizer ID");
                return;
            }
            
            
            if("XMLHttpRequest".equals(requestedWidth)) 
            {
            	System.out.println("Entered Into AJax");
            	response.setContentType("application/json");
            	response.setCharacterEncoding("UTF-8");
            	
            	Gson gson = new Gson();
            	JsonArray jsonArray = new JsonArray();
            	
            	for(EventFeedback eventFeedback:eventFeedbackList) 
            	{
            		JsonObject jsonObj = new JsonObject();
            		jsonObj.addProperty("eventName", eventFeedback.getEventName());
            		jsonObj.addProperty("eventLocation", eventFeedback.getEventLocation());
            		jsonObj.addProperty("attendeeName", eventFeedback.getAttendeeName());
            		jsonObj.addProperty("rating", eventFeedback.getRating());
            		jsonObj.addProperty("comments", eventFeedback.getComments());
            		
            		jsonArray.add(jsonObj);
            	}
            	response.getWriter().write(gson.toJson(jsonArray));
            	return;
            	
            	
            }

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
