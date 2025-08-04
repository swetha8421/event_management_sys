package Dao;

import Model.Organizer;

public interface OrgLoginDao 
{
	public Organizer loginByEmailRole(String email);
	public String getOrganizerImageById(int organizerId);

}
