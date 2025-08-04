package ServiceImpl;

import java.util.List;

import Dao.AttendeeDao;
import DaoImpl.AttendeeDaoImpl;
import Model.Attendee;
import Model.Event;
import Service.AttendeeService;

public class AttendeeServiceImpl implements AttendeeService
{
	private AttendeeDao attendeeDao = new AttendeeDaoImpl();

	public List<Attendee> getAllAttendees() 
	{
		return attendeeDao.getAllAttendees();
	}

	
	public boolean assignAttendeeToEvent(int attendeeId, int eventId) 
	{
		return attendeeDao.assignAttendeeToEvent(attendeeId, eventId);
	}

	
	public List<Event> getEventByAttendeeId(int attendeeId) 
	{
		return attendeeDao.getEventByAttendeeId(attendeeId);
	}

	
	public Attendee getAttendeeById(int id) 
	{
		return attendeeDao.getAttendeeById(id);
	
	}

	
	public List<Integer> getEventIdsWithFeedback(int attendeeId) 
	{	
		return attendeeDao.getEventIdsWithFeedback(attendeeId);
	}

}
