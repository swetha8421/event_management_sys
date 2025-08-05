package ServiceImpl;

import java.io.InputStream;

import Model.Attendee;
import Service.AttendeeRegisterService;
import Dao.AttendeeRegisterDao;
import DaoImpl.AttendeeRegisterDaoImpl;

public class AttendeeRegisterServiceImpl implements AttendeeRegisterService
{
	private AttendeeRegisterDao attendeeRegisterDao = new AttendeeRegisterDaoImpl();
	
	public boolean insertAttendee(Attendee attendee) 
	{
		return attendeeRegisterDao.insertAttendee(attendee);
		
	}
	

}
