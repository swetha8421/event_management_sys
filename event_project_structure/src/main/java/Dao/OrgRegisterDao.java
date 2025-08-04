package Dao;

import java.io.InputStream;

import Model.Organizer;

public interface OrgRegisterDao 
{
	public boolean insertOrganizer(Organizer organizer,InputStream imageStream);

}
