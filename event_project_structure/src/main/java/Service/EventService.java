package Service;

import java.sql.Date;
import java.util.List;

import Model.Event;
import Model.EventStatus;
import Model.EventType;

public interface EventService 
{
	public boolean insert(Event event);
	public int getIdByEventStatus(String eventStatus);
	public int getIdByEventType(String eventType);
	public List<Event> getAllEvents(int organizerId);
	public Event getEventById(int id);
	public boolean updateEventById(Event event);
	public boolean deleteEventById(int eventId);
	public List<EventType> getEventType();
	public List<EventStatus> getEventStatus();
	public List<Event> getFilteredEvents(int organizerId, Integer filterType, Integer filterStatus,String searchTerm,Date startDate,Date endDate);

}
