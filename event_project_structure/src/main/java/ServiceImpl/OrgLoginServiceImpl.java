package ServiceImpl;

import Model.Organizer;
import Service.OrgLoginService;
import Dao.OrgLoginDao;
import DaoImpl.OrgLoginDaoImpl;

public class OrgLoginServiceImpl implements OrgLoginService
{	
	private OrgLoginDao orgLoginDao =  new OrgLoginDaoImpl();
	
	
	public Organizer loginByEmailRole(String email) {
		return orgLoginDao.loginByEmailRole(email);
	}

	public String getOrganizerImageById(int organizerId) {
		return orgLoginDao.getOrganizerImageById(organizerId);
	}
	

}
