package Utility_folder;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connection_Db
{
	public Connection connect_to_db()
	{
		Connection conn = null;
		try
		{
			try {
		        Class.forName("org.postgresql.Driver");
		        System.out.println("Driver Loaded Successfully!");
		    } catch (ClassNotFoundException e) {
		        e.printStackTrace();
		    }
			String dbName="event_management_system";
			String username="postgres";
			String password="";
			conn=DriverManager.getConnection("jdbc:postgresql://localhost:5432/"+dbName,username,password);
			if(conn!=null)
			{
				System.out.println("Connection successful");
			}
			else
			{
				System.out.println("Connection failed");
			}

		}
		catch(Exception e)
		{
			System.out.println(e);

		}
		return conn;

	}

}
