package DaoImpl;


import java.sql.Connection;
import java.sql.PreparedStatement;


import Dao.AttendeeRegisterDao;
import Model.Attendee;
import Utility_folder.Connection_Db;

public class AttendeeRegisterDaoImpl  implements AttendeeRegisterDao{


	public boolean insertAttendee(Attendee attendee)
	{
		PreparedStatement pstmt;
		try
		{

			Connection_Db obj = new Connection_Db();
			Connection conn = obj.connect_to_db();

			String tableName="attendee";

			String query = "INSERT INTO "+tableName+" (name,email,password,mobile,image_path,image_name) VALUES (?,?,?,?,?,?)";

			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, attendee.getName());
			pstmt.setString(2, attendee.getEmail());
			pstmt.setString(3, attendee.getPassword());
			pstmt.setString(4, attendee.getMobile());
			
			

			pstmt.setString(5, attendee.getImagePath());
			pstmt.setString(6, attendee.getImageName());
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
