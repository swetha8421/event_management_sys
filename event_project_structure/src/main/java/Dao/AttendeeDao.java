package Dao;

import java.util.List;

import Model.Attendee;
import Model.Event;

public interface AttendeeDao 
{

		public List<Attendee> getAllAttendees();
		public boolean assignAttendeeToEvent(int attendeeId,int eventId);
		public  List<Event> getEventByAttendeeId(int attendeeId);
		public Attendee getAttendeeById(int id);
		public List<Integer> getEventIdsWithFeedback(int attendeeId);
}
