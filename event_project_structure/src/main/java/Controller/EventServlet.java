package Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;


import Service.EventService;
import ServiceImpl.EventServiceImpl;
import Model.Event;
import Model.EventStatus;
import Model.EventType;
//date filter
//event type,event status
//search filter - 
public class EventServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EventService eventService = new EventServiceImpl();
	
       
    
    public EventServlet() {
        super();
       
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
    	    throws ServletException, IOException 
    	{
    	    HttpSession session = request.getSession(false);

    	    if (session == null || session.getAttribute("organizer_id") == null) {
    	        request.setAttribute("message", "Organizer not logged in");
    	        request.getRequestDispatcher("orgLogin.jsp").forward(request, response);
    	        return;
    	    }
    	    
    	    String action = request.getParameter("action");
    	    String eventIdParam = request.getParameter("eventId");
    	    
    	   
    	    
    	    
    	    
    	    
    	    
    	    try {
    	        if ("edit".equalsIgnoreCase(action) && eventIdParam != null) 
    	        {
    	            int eventId = Integer.parseInt(eventIdParam);
    	            Event event = eventService.getEventById(eventId);

    	            List<EventType> eventTypes = eventService.getEventType();
    	            List<EventStatus> eventStatuses = eventService.getEventStatus();

    	            request.setAttribute("event", event);
    	            request.setAttribute("eventTypes", eventTypes);
    	            request.setAttribute("eventStatuses", eventStatuses);

    	            request.getRequestDispatcher("Pages/editEvent.jsp").forward(request, response);
    	        } else 
    	        
    	        {
    	            int organizerID = (int) session.getAttribute("organizer_id");
    	            List<Event> events = eventService.getAllEvents(organizerID);
    	            
    	            

    	            List<EventType> eventTypes = eventService.getEventType();
    	            List<EventStatus> eventStatuses = eventService.getEventStatus();

    	            
    	            request.setAttribute("eventTypes", eventTypes);
    	            request.setAttribute("eventStatuses", eventStatuses);
    	            
    	            request.setAttribute("eventList", events);
    	            request.getRequestDispatcher("Pages/viewEvents.jsp").forward(request, response);
    	        }
    	    } catch (Exception e) {
    	        e.printStackTrace();
    	        request.setAttribute("message", "Something went wrong while loading the page.");
    	        request.getRequestDispatcher("Pages/orgHomePage.jsp").forward(request, response);
    	    }
    	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException 
	{
		String method = request.getParameter("_method");
		if("put".equalsIgnoreCase(method)) 
		{
			update(request,response);
		}
		else 
		{
			create(request,response);
		}
		
			
	
	}
	
	private void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		String name = request.getParameter("name");
		String eventLocation = request.getParameter("eventLocation");
		int eventType = Integer.parseInt(request.getParameter("eventType"));
		String startDateStr = request.getParameter("startDate");
		String endDateStr = request.getParameter("endDate");
		int eventStatus = Integer.parseInt(request.getParameter("eventStatus"));
		Integer budget = Integer.parseInt(request.getParameter("budget"));
		Integer expectedAttendees = Integer.parseInt(request.getParameter("noOfAttendees"));
		Integer organizerID = Integer.parseInt(request.getParameter("organizerID"));
		String description = request.getParameter("description");
		
		java.sql.Date startDate = java.sql.Date.valueOf(startDateStr);
		java.sql.Date endDate = java.sql.Date.valueOf(endDateStr);
		
		
		
		
		
		Event event = new Event();
		
		event.setName(name);
		event.setLocation(eventLocation);
		event.setStartDate(startDate);
		event.setEndDate(endDate);
		event.setBudget(budget);
		event.setEventStatus(eventStatus);
		event.setEventType(eventType);
		event.setExpectedAttendees(expectedAttendees);
		event.setOrganizerId(organizerID);
		event.setDescription(description);
		
		 
		boolean result = eventService.insert(event);
		if(result) 
		{
			System.out.println(event);
			System.out.println(event.getName());
			request.setAttribute("message","Event Added Successfully");
			
		}
		else 
		{
			request.setAttribute("message", "Failed to create event");
			
			
		}
		
		List<Event> events = eventService.getAllEvents(organizerID);
		request.setAttribute("eventList", events);
		request.getRequestDispatcher("Pages/viewEvents.jsp").forward(request, response);
		
		}
	
	
	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
		
		try {
		
			
		    List<EventType> eventTypes = eventService.getEventType();
		    List<EventStatus> eventStatuses = eventService.getEventStatus();

		    request.setAttribute("eventTypes", eventTypes);
		    request.setAttribute("eventStatuses", eventStatuses);

		String name = request.getParameter("name");
		String eventLocation = request.getParameter("eventLocation");
		String startDateStr = request.getParameter("startDate");
		String endDateStr = request.getParameter("endDate"); 
		int budget = Integer.parseInt(request.getParameter("budget"));
		int expected_attendees = Integer.parseInt(request.getParameter("noOfAttendees"));
		String description = request.getParameter("description");
		int eventType = Integer.parseInt(request.getParameter("eventType"));
		int eventStatus = Integer.parseInt(request.getParameter("eventStatus"));
		int event_id = Integer.parseInt(request.getParameter("eventId"));
		int organizerId = Integer.parseInt(request.getParameter("organizerID"));
		
		java.sql.Date startDate = java.sql.Date.valueOf(startDateStr);
		java.sql.Date endDate = java.sql.Date.valueOf(endDateStr);


		
		
		
		Event event = new Event();
		
		event.setName(name);
		event.setLocation(eventLocation);
		event.setStartDate(startDate);
		event.setEndDate(endDate);
		event.setBudget(budget);
		event.setExpectedAttendees(expected_attendees);
		event.setDescription(description);
		event.setEventStatus(eventStatus);
		event.setEventType(eventType);
		event.setId(event_id);
		event.setOrganizerId(organizerId);
		
		boolean result = eventService.updateEventById(event);
		
		if(result) 
		{
			System.out.println(result);
			System.out.println(event);
			System.out.println(event.getName());
			request.setAttribute("message","Event Updated Successfully");
			
			
		}
		else 
		{
			System.out.println(result);
			request.setAttribute("message", "Failed to Update event");
			
			
		}
		List<Event> events = eventService.getAllEvents(organizerId);
		request.setAttribute("eventList", events);
		request.getRequestDispatcher("Pages/viewEvents.jsp").forward(request, response);
		}
		catch(Exception e) 
		{
			e.printStackTrace();
			
		}
	}
	
		
}