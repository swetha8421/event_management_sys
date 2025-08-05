package DaoImpl;


import java.sql.Connection;
import java.sql.PreparedStatement;


import Dao.OrgRegisterDao;
import Model.Organizer;
import Utility_folder.Connection_Db;


public class OrgRegisterDaoImpl implements OrgRegisterDao
{


	public boolean insertOrganizer(Organizer organizer)
	{
		PreparedStatement pstmt;
		try
		{

			Connection_Db obj = new Connection_Db();
			Connection conn = obj.connect_to_db();
			String tableName="organizer";

			String query = "INSERT INTO "+tableName+" (name,email,password,phone,organization_name,file_name,image_path) VALUES (?,?,?,?,?,?,?)";

			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, organizer.getName());
			pstmt.setString(2, organizer.getEmail());
			pstmt.setString(3, organizer.getPassword());
			pstmt.setString(4, organizer.getPhone());
			pstmt.setString(5, organizer.getOrganizationName());
			
			
			
			pstmt.setString(6, organizer.getFileName());
			pstmt.setString(7, organizer.getImagePath());
			
			
			
			
			int rowsUpdated = pstmt.executeUpdate();
			if(rowsUpdated>0) 
			{
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
