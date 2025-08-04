package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Model.Attendee;
import Utility_folder.Connection_Db;

public class AttendeeLoginDao
{


	public Attendee loginByEmailRole(String email)
	{
		PreparedStatement pstmt;
		ResultSet result = null;
		System.out.println("inside login");
		try
		{

			Connection_Db obj = new Connection_Db();
			Connection conn = obj.connect_to_db();

			String tableName="attendee";

			String query = String.format("Select * from "+tableName+ " where email = ?");
			
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, email);

			result = pstmt.executeQuery();
			Attendee attendee = new Attendee();
			
			 if (result.next()) 
			 {
				 attendee.setId(result.getInt("id"));
				 attendee.setName(result.getString("name"));
				 attendee.setEmail(result.getString("email"));
				 attendee.setMobile(result.getString("mobile"));
				 attendee.setPassword(result.getString("password"));
				 
				 return attendee;
		           
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
	
	public byte[] getAttendeeImageById(int attendeeId) 
	{
		byte[] imageData=null;
		try 
		{
			Connection_Db obj = new Connection_Db();
			Connection conn = obj.connect_to_db();
			
			String query = "SELECT image FROM attendee WHERE id = ?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1,attendeeId);
			
			
			
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
	}