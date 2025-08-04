package Dao;

import Model.Attendee;

public interface AttendeeLoginDao 
{
	public Attendee loginByEmailRole(String email);
	public byte[] getAttendeeImageById(int attendeeId);
	

}
