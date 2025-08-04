package Dao;

import Model.Attendee;

public interface AttendeeLoginDao 
{
	public Attendee loginByEmailRole(String email);
	public String getAttendeeImageById(int attendeeId);
	

}
