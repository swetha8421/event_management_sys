package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Model.Organizer;
import Utility_folder.Connection_Db;


public class OrgLoginDao
{


	public Organizer loginByEmailRole(String email)
	{
		PreparedStatement pstmt;
		ResultSet result = null;
		System.out.println("inside login");
		try
		{

			Connection_Db obj = new Connection_Db();
			Connection conn = obj.connect_to_db();

			String tableName="organizer";

			String query = String.format("Select * from "+tableName+ " where email = ?");
			System.out.println("inside login 2");
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, email);

			
			result = pstmt.executeQuery();

			
			Organizer organizer = new Organizer();
			 if (result.next()) {
		            // ✅ Print all columns from the result for debugging
		            System.out.println("Row Found:");
		            System.out.println("ID: " + result.getInt("id"));
		            System.out.println("Name: " + result.getString("name"));
		            System.out.println("Email: " + result.getString("email"));
		            
		            organizer.setId(result.getInt("id"));
		            organizer.setName(result.getString("name"));
		            organizer.setEmail(result.getString("email"));
		            organizer.setOrganizationName(result.getString("organization_name"));
		            organizer.setPhone(result.getString("phone"));
		            organizer.setPassword(result.getString("password"));

		            return organizer; 
			}
			else
			{
				return null;

			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public byte[] getOrganizerImageById(int organizerId) 
	{
		byte[] imageData=null;
		try 
		{
			Connection_Db obj = new Connection_Db();
			Connection conn = obj.connect_to_db();
			
			String query = "SELECT image FROM organizer WHERE id = ?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1,organizerId);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) 
			{
				imageData = rs.getBytes("image");
				
			}
			else 
			{
				return null;
			}
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		return imageData;
	}
	public static void main(String[] args)
	{


	}

}
