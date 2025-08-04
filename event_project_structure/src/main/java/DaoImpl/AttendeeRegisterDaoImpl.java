package DaoImpl;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Types;

import Dao.AttendeeRegisterDao;
import Model.Attendee;
import Utility_folder.Connection_Db;

public class AttendeeRegisterDaoImpl  implements AttendeeRegisterDao{


	public boolean insertAttendee(Attendee attendee,InputStream imageStream)
	{
		PreparedStatement pstmt;
		try
		{

			Connection_Db obj = new Connection_Db();
			Connection conn = obj.connect_to_db();

			String tableName="attendee";

			String query = "INSERT INTO "+tableName+" (name,email,password,mobile,image,image_path,image_name) VALUES (?,?,?,?,?,?,?)";

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
				pstmt.setNull(5, Types.BINARY);
			}

			pstmt.setString(6, attendee.getImagePath());
			pstmt.setString(7, attendee.getImageName());
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
