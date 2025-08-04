package Dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.List;



import Model.Event;
import Model.EventStatus;
import Model.EventType;
import Utility_folder.Connection_Db;

public class EventDao 
{
	public boolean insert(Event event) 
	{
		PreparedStatement pstmt;
		try
		{

			Connection_Db obj = new Connection_Db();
			Connection conn = obj.connect_to_db();
			String tableName="events";

			String query = "INSERT INTO "+tableName+
			" (name,event_location,start_date,end_date,budget,description,expected_attendees,organizer_id,event_type_id,event_status_id) "
			+ "VALUES (?,?,?,?,?,?,?,?,?,?)";

			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1,event.getName());
			pstmt.setString(2,event.getLocation());
			pstmt.setDate(3,event.getStartDate());
			pstmt.setDate(4,event.getEndDate());
			pstmt.setInt(5, event.getBudget());
			pstmt.setString(6, event.getDescription());
			pstmt.setInt(7,event.getExpectedAttendees());
			pstmt.setInt(8, event.getOrganizerId());
			pstmt.setInt(9, event.getEventType());
			pstmt.setInt(10, event.getEventStatus());
			
			
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
	
	public int getIdByEventStatus(String eventStatus) 
	{
		PreparedStatement pstmt;
		ResultSet result = null;
		try
		{

			Connection_Db obj = new Connection_Db();
			Connection conn = obj.connect_to_db();
			String tableName="dd_event_status";

			String query = "SELECT id FROM "+tableName+" WHERE status_name = ?";
			
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, eventStatus);
			
			result = pstmt.executeQuery();
			if(result.next()) 
			{
				return result.getInt("id");
			}
			return 0;
		}
		catch(Exception e)
		{
			System.out.println("Error Fetching Data "+e.getMessage());
			return 0;
		}
	}
	
	
	public int getIdByEventType(String eventType) 
	{
		PreparedStatement pstmt;
		ResultSet result = null;
		try
		{

			Connection_Db obj = new Connection_Db();
			Connection conn = obj.connect_to_db();
			String tableName="dd_event_type";

			String query = "SELECT id FROM "+tableName+" WHERE type_name = ?";
			
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, eventType);
			
			result = pstmt.executeQuery();
			if(result.next()) 
			{
				return result.getInt("id");
			}
			return 0;
		}
		catch(Exception e)
		{
			System.out.println("Error Fetching Data "+e.getMessage());
			return 0;
		}
	}
	
	public List<Event> getAllEvents(int organizerId)
	{
		List<Event> events = new ArrayList<>();
		try 
		{
			Connection_Db obj = new Connection_Db();
			Connection conn = obj.connect_to_db();
			
			String query = "\r\n"
					+ "select e.*,et.type_name,es.status_name from events  e join dd_event_type  et on e.event_type_id = et.id \r\n"
					+ "						join dd_event_status es on e.event_status_id = es.id 	\r\n"
					+ "where organizer_id=? order by e.id";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, organizerId);
			ResultSet rs = pstmt.executeQuery();
			
			
			while(rs.next()) 
			{
				Event event = new Event();
				
				event.setId(rs.getInt("id"));
				event.setName(rs.getString("name"));
				event.setLocation(rs.getString("event_location"));
				event.setStartDate(rs.getDate("start_date"));
				event.setEndDate(rs.getDate("end_date"));
				event.setEvent_type(rs.getString("type_name"));
				event.setEvent_status(rs.getString("status_name"));
				
				events.add(event);
				
			}
			return events;
			
		}
		catch(Exception e)
		{
			System.out.println(e);
			return null;
			
		}
	}
	
	public Event getEventById(int id) 
	{
		try 
		{
		String query = "\r\n"
				+ "select e.*,et.type_name,es.status_name from events  e join dd_event_type  et on e.event_type_id = et.id \r\n"
				+ "						join dd_event_status es on e.event_status_id = es.id 	\r\n"
				+ "where e.id=?";
		Connection_Db obj = new Connection_Db();
		Connection conn = obj.connect_to_db();
		PreparedStatement pstmt = conn.prepareStatement(query);
		pstmt.setInt(1, id);
		ResultSet rs = pstmt.executeQuery();
		Event e = new Event();
		if(rs.next()) 
		{
			
			e.setId(rs.getInt("id"));
			e.setName(rs.getString("name"));
			e.setLocation(rs.getString("event_location"));
			e.setEvent_type(rs.getString("type_name"));
			e.setEvent_status(rs.getString("status_name"));
			e.setBudget(rs.getInt("budget"));
			e.setExpectedAttendees(rs.getInt("expected_attendees"));
			e.setStartDate(rs.getDate("start_date"));
			e.setEndDate(rs.getDate("end_date"));
			e.setDescription(rs.getString("description"));
			e.setEventStatus(rs.getInt("event_status_id"));
			e.setEventType(rs.getInt("event_type_id"));
			
		}
		return e;
		
		
		}
		catch(Exception e) 
		{
			e.printStackTrace();
			return null;
		}
		
	}
	
	public boolean updateEventById(Event event) 
	{
		try 
		{
		String query = "UPDATE events SET name = ?,event_location=?,start_date=?,"
				
				+ "end_date=?,budget=?,description = ?,expected_attendees=?,event_type_id = ?,event_status_id=?,"
				+ "organizer_id=? where id = ?";
		Connection_Db obj = new Connection_Db();
		Connection conn = obj.connect_to_db();
		PreparedStatement pstmt = conn.prepareStatement(query);
		
		
		pstmt.setString(1, event.getName());
		pstmt.setString(2, event.getLocation());
		pstmt.setDate(3, event.getStartDate());
		pstmt.setDate(4, event.getEndDate());
		pstmt.setInt(5,event.getBudget());
		pstmt.setString(6, event.getDescription());
		pstmt.setInt(7, event.getExpectedAttendees());
		pstmt.setInt(8, event.getEventType());
		pstmt.setInt(9, event.getEventStatus());
		pstmt.setInt(10, event.getOrganizerId());
		
		pstmt.setInt(11, event.getId());
		
		
		int rowUpdated = pstmt.executeUpdate();
		if(rowUpdated>0) 
		{
			System.out.println("Error Updating Error");
			return true;
		}
		else {
			return false;
		}
		
		
		
		
		}
		catch(Exception  e) 
		{
			System.out.println("Error inserting Data "+e.getMessage());
			e.printStackTrace();
			return false;
		
		}
		
	}
	
	public boolean deleteEventById(int eventId) 
	{
		boolean rowDeleted = false;
		try 
		{
			Connection_Db obj = new Connection_Db();
			Connection conn = obj.connect_to_db();
			
			String query = "DELETE FROM events WHERE id = ?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, eventId);
			rowDeleted = pstmt.executeUpdate()>0;
			
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		return rowDeleted;
	}
	
	public List<EventType> getEventType()
	{
		List<EventType> types = new ArrayList<>();
		
		try 
		{
			Connection_Db obj = new Connection_Db();
			Connection conn = obj.connect_to_db();
			
			String query = "SELECT * FROM dd_event_type";
			PreparedStatement pstmt = conn.prepareStatement(query);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) 
			{
				EventType type = new EventType();
				type.setId(rs.getInt("id"));
				type.setType_name(rs.getString("type_name"));
				types.add(type);
			}
			
			return types;
		}
		catch(Exception e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public List<EventStatus> getEventStatus()
	{
		List<EventStatus> statuses = new ArrayList<>();
		
		try 
		{
			Connection_Db obj = new Connection_Db();
			Connection conn = obj.connect_to_db();
			
			String query = "SELECT * FROM dd_event_status";
			PreparedStatement pstmt = conn.prepareStatement(query);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) 
			{
				EventStatus status = new EventStatus();
				status.setId(rs.getInt("id"));
				status.setStatus_name(rs.getString("status_name"));
				statuses.add(status);
			}
			
			return statuses;
		}
		catch(Exception e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Event> getFilteredEvents(int organizerId, Integer filterType, Integer filterStatus,String searchTerm,Date startDate,Date endDate) {
	    List<Event> events = new ArrayList<>();

	    StringBuilder query = new StringBuilder(
	    		"SELECT e.*, et.type_name, es.status_name " +
	    	               "FROM events e " +
	    	               "JOIN dd_event_type et ON e.event_type_id = et.id " +
	    	               "JOIN dd_event_status es ON e.event_status_id = es.id " +
	    	               "WHERE e.organizer_id = ?"
	    	);

	    if (filterType != null) {
	    	query.append(" AND e.event_type_id = ?");
	    }

	    if (filterStatus != null) {
	    	query.append(" AND e.event_status_id = ?");
	    }
	    if (searchTerm != null && !searchTerm.trim().isEmpty()) {
	        query.append(" AND (LOWER(e.name) LIKE ? OR LOWER(e.event_location) LIKE ?)");
	    }

	    if (startDate != null) {
	    	query.append(" AND e.start_date >= ?");
	    }
	    if (endDate != null) {
	    	query.append(" AND e.end_date <= ?");
	    }

	    try (Connection conn = new Connection_Db().connect_to_db();
	         PreparedStatement stmt = conn.prepareStatement(query.toString())) {

	        int index = 1;
	        stmt.setInt(index++, organizerId);

	        if (filterType != null) {
	            stmt.setInt(index++, filterType);
	        }

	        if (filterStatus != null) {
	            stmt.setInt(index++, filterStatus);
	        }
	        
	        if (searchTerm != null && !searchTerm.trim().isEmpty()) {
	            String searchLike = "%" + searchTerm.toLowerCase() + "%";
	            stmt.setString(index++, searchLike); // for name
	            stmt.setString(index++, searchLike); // for location
	        }
	        
	        if (startDate != null) {
	            stmt.setDate(index++, startDate);
	        }
	        if (endDate != null) {
	            stmt.setDate(index++, endDate);
	        }


	        ResultSet rs = stmt.executeQuery();
	        while (rs.next()) {
	            Event event = new Event();

	            event.setId(rs.getInt("id"));
	            event.setName(rs.getString("name"));
	            event.setLocation(rs.getString("event_location"));
	            event.setStartDate(rs.getDate("start_date"));
	            event.setEndDate(rs.getDate("end_date"));
	            event.setEventType(rs.getInt("event_type_id"));
	            event.setEventStatus(rs.getInt("event_status_id"));
	            event.setBudget(rs.getInt("budget"));
	            event.setExpectedAttendees(rs.getInt("expected_attendees"));
	            event.setOrganizerId(rs.getInt("organizer_id"));
	            event.setDescription(rs.getString("description"));

	            // Set readable names for JSP display
	            event.setEvent_type(rs.getString("type_name"));
	            event.setEvent_status(rs.getString("status_name"));

	            events.add(event);
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return events;
	}
}
