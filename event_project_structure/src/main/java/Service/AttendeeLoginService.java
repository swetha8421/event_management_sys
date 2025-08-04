package Service;

import Model.Attendee;

public interface AttendeeLoginService 
{
	public Attendee loginByEmailRole(String email);
	public String getAttendeeImageById(int attendeeId);
	

}
