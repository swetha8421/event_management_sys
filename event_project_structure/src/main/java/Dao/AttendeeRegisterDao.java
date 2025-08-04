package Dao;

import java.io.InputStream;

import Model.Attendee;

public interface AttendeeRegisterDao 
{
	public boolean insertAttendee(Attendee attendee,InputStream imageStream);
	

}
