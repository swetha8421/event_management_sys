package Service;

import java.io.InputStream;

import Model.Attendee;

public interface AttendeeRegisterService 
{
	public boolean insertAttendee(Attendee attendee,InputStream imageStream);

}
