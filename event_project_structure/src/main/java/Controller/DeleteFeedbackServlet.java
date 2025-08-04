package Controller;

import jakarta.servlet.ServletException;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import Service.FeedbackService;
import ServiceImpl.FeedbackServiceImpl;


/**
 * Servlet implementation class DeleteFeedbackServlet
 */

public class DeleteFeedbackServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private FeedbackService feedbackService = new FeedbackServiceImpl();
       

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException 
	{
		try 
        {
        
           
		String feedbackIdStr = request.getParameter("feedbackId");
            if (feedbackIdStr != null && !feedbackIdStr.isEmpty()) 
            {
            	 
                int feedbackId = Integer.parseInt(feedbackIdStr);
                
             
                boolean success = false;
				try {
					success = feedbackService.deleteFeedbackById(feedbackId);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                
                if (success) 
                {
                	 response.sendRedirect("${pageContext.request.contextPath}/ViewOrganizerFeedbackServlet?organizerId=" + request.getParameter("organizerId"));
                    
                   
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
