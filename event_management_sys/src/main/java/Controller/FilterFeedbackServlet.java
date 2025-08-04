package Controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import Dao.FeedbackDao;
import Model.Feedback;

public class FilterFeedbackServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
   	{
   		try {
   	   
   		String ratingStr = request.getParameter("rating");
   		String searchTerm = request.getParameter("searchTerm");

       Integer rating = null;
       if (ratingStr != null && !ratingStr.isEmpty()) {
           rating = Integer.parseInt(ratingStr);
       }

       FeedbackDao dao = new FeedbackDao();
       List<Feedback> feedbackList = dao.getFilteredFeedback(rating, searchTerm);
       request.setAttribute("feedbackList", feedbackList);

       RequestDispatcher rd = request.getRequestDispatcher("viewFeedbacks.jsp");
       rd.forward(request, response);
   		}
   		catch(Exception e) 
   		{
   			e.printStackTrace();
   		}
   }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
