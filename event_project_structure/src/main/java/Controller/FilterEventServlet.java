package Controller;

import jakarta.servlet.ServletException;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import Service.EventService;
import ServiceImpl.EventServiceImpl;
import Model.Event;


public class FilterEventServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EventService eventService = new EventServiceImpl();
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
		 HttpSession session = request.getSession(false);
	        if (session == null || session.getAttribute("organizer_id") == null) {
	            request.setAttribute("message", "Organizer not logged in");
	            request.getRequestDispatcher("Pages/orgLogin.jsp").forward(request, response);
	            return;
	        }

	        int organizerID = (int) session.getAttribute("organizer_id");
		
		
		
        String filterTypeParam = request.getParameter("filterType");
        String filterStatusParam = request.getParameter("filterStatus");
        String searchItem = request.getParameter("search");
        String startDateParam = request.getParameter("startDate");
        String endDateParam = request.getParameter("endDate");
        
        Integer filterType = (filterTypeParam != null && !filterTypeParam.isEmpty()) ? Integer.parseInt(filterTypeParam) : null;
        Integer filterStatus = (filterStatusParam != null && !filterStatusParam.isEmpty()) ? Integer.parseInt(filterStatusParam) : null;
        java.sql.Date startDate = (startDateParam != null && !startDateParam.isEmpty()) 
                ? java.sql.Date.valueOf(startDateParam) 
                : null;

        java.sql.Date endDate = (endDateParam != null && !endDateParam.isEmpty()) 
                ? java.sql.Date.valueOf(endDateParam) 
                : null;
        List<Event> eventList = eventService.getFilteredEvents(organizerID, filterType, filterStatus,searchItem,startDate,endDate);
        
        
        String requestedWidth = request.getHeader("X-Requested-With");
        if("XMLHttpRequest".equals(requestedWidth)) 
        {
        	System.out.println("Entered Into AJax");
        	response.setContentType("application/json");
        	response.setCharacterEncoding("UTF-8");
        	
        	Gson gson = new Gson();
        	JsonArray jsonArray = new JsonArray();
        	
        	for(Event event:eventList) 
        	{
        		JsonObject jsonObj = new JsonObject();
        		jsonObj.addProperty("id", event.getId());
        		jsonObj.addProperty("name", event.getName());
        		jsonObj.addProperty("location", event.getLocation());
        		jsonObj.addProperty("startDate", event.getStartDate().toString());
        		jsonObj.addProperty("endDate", event.getEndDate().toString());
        		jsonObj.addProperty("event_status", event.getEvent_status());
        		jsonObj.addProperty("event_type", event.getEvent_type());
        		
        		jsonArray.add(jsonObj);
        	}
        	response.getWriter().write(gson.toJson(jsonArray));
        	return;
        }
        
        
        request.setAttribute("eventTypes", eventService.getEventType());
        request.setAttribute("eventStatuses", eventService.getEventStatus());
        request.setAttribute("eventList", eventList);
        request.setAttribute("selectedType", filterTypeParam);
        request.setAttribute("selectedStatus", filterStatusParam);
        request.setAttribute("selectedStartDate", startDateParam);
        request.setAttribute("selectedEndDate", endDateParam);
        request.setAttribute("searchTerm", searchItem); 
        
        request.getRequestDispatcher("Pages/viewEvents.jsp").forward(request, response);
        }



	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
