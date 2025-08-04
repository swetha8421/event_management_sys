package ServiceImpl;

import java.io.InputStream;

import Model.Organizer;
import Service.OrgRegisterService;
import Dao.OrgRegisterDao;
import DaoImpl.OrgRegisterDaoImpl;
public class OrgRegisterServiceImpl implements OrgRegisterService 
{
	private OrgRegisterDao orgRegisterDao = new OrgRegisterDaoImpl();
	@Override
	public boolean insertOrganizer(Organizer organizer, InputStream imageStream) {
		
		return orgRegisterDao.insertOrganizer(organizer, imageStream);
	}

}
