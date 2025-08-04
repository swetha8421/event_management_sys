package ServiceImpl;

import Model.Attendee;
import Service.AttendeeLoginService;
import Dao.AttendeeLoginDao;
import DaoImpl.AttendeeLoginDaoImpl;

public class AttendeeLoginServiceImpl implements AttendeeLoginService {
	
	private AttendeeLoginDao attendeeLoginDao = new AttendeeLoginDaoImpl();
	
	public Attendee loginByEmailRole(String email) 
	{
		
		return attendeeLoginDao.loginByEmailRole(email);
	}

	
	public String getAttendeeImageById(int attendeeId) 
	{
		return attendeeLoginDao.getAttendeeImageById(attendeeId);
	}

}
