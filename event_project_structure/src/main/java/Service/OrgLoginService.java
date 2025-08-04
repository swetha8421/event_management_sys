package Service;

import Model.Organizer;

public interface OrgLoginService 
{
	public Organizer loginByEmailRole(String email);
	public String getOrganizerImageById(int organizerId);

}
