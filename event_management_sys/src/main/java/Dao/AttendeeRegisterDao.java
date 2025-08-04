package Dao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Types;

import Model.Attendee;
import Utility_folder.Connection_Db;

public class AttendeeRegisterDao {


	public boolean insertAttendee(Attendee attendee,InputStream imageStream)
	{
		PreparedStatement pstmt;
		try
		{

			Connection_Db obj = new Connection_Db();
			Connection conn = obj.connect_to_db();

			String tableName="attendee";

			String query = "INSERT INTO "+tableName+" (name,email,password,mobile,image) VALUES (?,?,?,?,?)";

			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, attendee.getName());
			pstmt.setString(2, attendee.getEmail());
			pstmt.setString(3, attendee.getPassword());
			pstmt.setString(4, attendee.getMobile());
			
			if(imageStream != null) 
			{
				pstmt.setBinaryStream(5,imageStream,imageStream.available());
			}
			else 
			{
				pstmt.setNull(6, Types.BINARY);
			}


			int rowsUpdated = pstmt.executeUpdate();
			if(rowsUpdated>0) {
				return true;
			}
			return false;




		}
		catch(Exception e)
		{
			System.out.println("Error Inserting Data "+e.getMessage());
			return false;


		}


	}

}
