package ServiceImpl;

import java.sql.Date;
import java.util.List;

import Model.Event;
import Model.EventStatus;
import Model.EventType;
import Service.EventService;
import Dao.EventDao;
import DaoImpl.EventDaoImpl;

public class EventServiceImpl implements EventService{

	private EventDao eventDao = new EventDaoImpl();
	public boolean insert(Event event) 
	{
		return eventDao.insert(event);
		
	}


	public int getIdByEventStatus(String eventStatus) 
	{
		return eventDao.getIdByEventStatus(eventStatus);
	}

	public int getIdByEventType(String eventType) 
	{
		return eventDao.getIdByEventType(eventType);
	}

	public List<Event> getAllEvents(int organizerId) 
	{
		return eventDao.getAllEvents(organizerId);
	
	}
	
	public Event getEventById(int id) 
	{
		return eventDao.getEventById(id);
	}

	public boolean updateEventById(Event event) 
	{
		return eventDao.updateEventById(event);
	}

	
	public boolean deleteEventById(int eventId) 
	{
		return eventDao.deleteEventById(eventId);
	}

	
	public List<EventType> getEventType() 
	{
		return eventDao.getEventType();
		
	}

	
	public List<EventStatus> getEventStatus() 
	{
		return eventDao.getEventStatus();
	}

	@Override
	public List<Event> getFilteredEvents(int organizerId, Integer filterType, Integer filterStatus, String searchTerm,
			Date startDate, Date endDate) 
	{
		return eventDao.getFilteredEvents(organizerId, filterType, filterStatus, searchTerm, startDate, endDate);
		
	}

}
