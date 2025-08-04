package Service;

import java.io.InputStream;

import Model.Organizer;

public interface OrgRegisterService 
{
	public boolean insertOrganizer(Organizer organizer,InputStream imageStream);

}
