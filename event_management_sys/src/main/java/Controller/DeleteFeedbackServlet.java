package Controller;

import jakarta.servlet.ServletException;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import Dao.FeedbackDao;

/**
 * Servlet implementation class DeleteFeedbackServlet
 */

public class DeleteFeedbackServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException 
	{
		try 
        {
        
           
		String feedbackIdStr = request.getParameter("feedbackId");
            if (feedbackIdStr != null && !feedbackIdStr.isEmpty()) 
            {
            	 
                int feedbackId = Integer.parseInt(feedbackIdStr);
                
                
                boolean success = FeedbackDao.deleteFeedbackById(feedbackId);
                
                if (success) 
                {
                	 response.sendRedirect("ViewOrganizerFeedbackServlet?organizerId=" + request.getParameter("organizerId"));
                    
                   
                } 
                else 
                {
                    response.getWriter().println("Failed to delete feedback. Try again.");
                }
            }
            else 
            {
                response.getWriter().println("Invalid Feedback ID");
            }
        } 
		catch (Exception e) 
        {
            e.printStackTrace();
            response.getWriter().println("Error while deleting feedback: " + e.getMessage());
        
    }}

}
