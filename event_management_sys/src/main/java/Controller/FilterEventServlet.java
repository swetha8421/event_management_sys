package Controller;

import jakarta.servlet.ServletException;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

import Dao.EventDao;
import Model.Event;


public class FilterEventServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
		 HttpSession session = request.getSession(false);
	        if (session == null || session.getAttribute("organizer_id") == null) {
	            request.setAttribute("message", "Organizer not logged in");
	            request.getRequestDispatcher("orgLogin.jsp").forward(request, response);
	            return;
	        }

	        int organizerID = (int) session.getAttribute("organizer_id");
		
		
		
        String filterTypeParam = request.getParameter("filterType");
        String filterStatusParam = request.getParameter("filterStatus");
        String searchItem = request.getParameter("search");

        Integer filterType = (filterTypeParam != null && !filterTypeParam.isEmpty()) ? Integer.parseInt(filterTypeParam) : null;
        Integer filterStatus = (filterStatusParam != null && !filterStatusParam.isEmpty()) ? Integer.parseInt(filterStatusParam) : null;
        
        
        String startDateParam = request.getParameter("startDate");
        String endDateParam = request.getParameter("endDate");

        java.sql.Date startDate = (startDateParam != null && !startDateParam.isEmpty()) 
                ? java.sql.Date.valueOf(startDateParam) 
                : null;

        java.sql.Date endDate = (endDateParam != null && !endDateParam.isEmpty()) 
                ? java.sql.Date.valueOf(endDateParam) 
                : null;
        
        
        EventDao dao = new EventDao();
        
       
        List<Event> eventList = dao.getFilteredEvents(organizerID, filterType, filterStatus,searchItem,startDate,endDate);
        
        request.setAttribute("eventTypes", dao.getEventType());
        request.setAttribute("eventStatuses", dao.getEventStatus());
        request.setAttribute("eventList", eventList);
        request.setAttribute("selectedType", filterTypeParam);
        request.setAttribute("selectedStatus", filterStatusParam);
        request.setAttribute("selectedStartDate", startDateParam);
        request.setAttribute("selectedEndDate", endDateParam);
        request.setAttribute("searchTerm", searchItem); 
        
        request.getRequestDispatcher("viewEvents.jsp").forward(request, response);
        }



	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
