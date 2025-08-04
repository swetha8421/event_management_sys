package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Model.Attendee;
import Model.Event;
import Model.EventAttendees;
import Utility_folder.Connection_Db;

public class AttendeeDao 
{

	public List<Attendee> getAllAttendees()
	{
		try 
		{
			Connection_Db obj = new Connection_Db();
			Connection conn = obj.connect_to_db();
			
			String query = "SELECT * FROM attendee";
			PreparedStatement pstmt = conn.prepareStatement(query);
			
			ResultSet rs = pstmt.executeQuery();
			
			List<Attendee> attendees = new ArrayList<Attendee>();
			
			while(rs.next()) 
			{
				Attendee attendee = new Attendee();
				attendee.setId(rs.getInt("id"));
				attendee.setName(rs.getString("name"));
				attendee.setEmail(rs.getString("email"));
				attendee.setMobile(rs.getString("Mobile"));
				
				attendees.add(attendee);
				
				
			}
			
			return attendees;
			
		}
		catch(Exception e) 
		{
			e.printStackTrace();
			return null;
			
		}
	}
	
	public boolean assignAttendeeToEvent(int attendeeId,int eventId) 
	{
		try {
			Connection_Db obj = new Connection_Db();
			Connection conn = obj.connect_to_db();
	        String sql = "INSERT INTO event_attendee (event_id, attendee_id) VALUES (?, ?)";
	        PreparedStatement pstmt = conn.prepareStatement(sql);
	        pstmt.setInt(1, eventId);
	        pstmt.setInt(2, attendeeId);
	        int rows = pstmt.executeUpdate();
	        return rows > 0;
	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    }
		
	}
	
	public  List<Event> getEventByAttendeeId(int attendeeId)
	{

		try {
			Connection_Db obj = new Connection_Db();
			Connection conn = obj.connect_to_db();
	        
			String sql = "SELECT e.*, et.type_name, es.status_name"
					+ " FROM events e"
					+ " JOIN event_attendee ea ON e.id = ea.event_id"
					+ " JOIN dd_event_type et ON e.event_type_id = et.id"
					+ " JOIN dd_event_status es ON e.event_status_id = es.id" 
					+ " WHERE ea.attendee_id = ?";
					
					
					
	        PreparedStatement pstmt = conn.prepareStatement(sql);
	        pstmt.setInt(1, attendeeId);
	        
	        List<Event> events = new ArrayList<Event>();
	        
	        ResultSet rs = pstmt.executeQuery();
	        
	        while(rs.next()) 
	        {
	        	Event event = new Event();
	        	event.setId(rs.getInt("id"));
	        	event.setName(rs.getString("name"));
	        	event.setLocation(rs.getString("event_location"));
	        	event.setEvent_status(rs.getString("status_name"));
	        	event.setEvent_type(rs.getString("type_name"));
	        	event.setDescription(rs.getString("description"));
	        	events.add(event);
	        }
	        return events;
	        
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }
	}
	
	public Attendee getAttendeeById(int id) 
	{
		try {
			Connection_Db obj = new Connection_Db();
			Connection conn = obj.connect_to_db();
	        
			String sql = "SELECT name FROM attendee WHERE id = ?";
			System.out.println(id);	
					
					
	        PreparedStatement pstmt = conn.prepareStatement(sql);
	        pstmt.setInt(1, id);
	        
	        Attendee attendee = new Attendee();
	        
	        ResultSet rs = pstmt.executeQuery();
	        
	        while(rs.next()) 
	        {
	        	attendee.setName(rs.getString("name"));
	        }
	        return attendee;
	        
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }
		
	}
	
	public List<Integer> getEventIdsWithFeedback(int attendeeId) 
	{
	    List<Integer> eventIds = new ArrayList<>();
	    String query = "SELECT event_id FROM feedback WHERE attendee_id = ?";
	    
	    try {
	    	
	    	Connection_Db obj = new Connection_Db();
	    	Connection conn = obj.connect_to_db();
	    
	        PreparedStatement ps = conn.prepareStatement(query);
	        ps.setInt(1, attendeeId);
	        ResultSet rs = ps.executeQuery();
	        
	        while (rs.next()) 
	        {
	            eventIds.add(rs.getInt("event_id"));
	        }
	    } 
	         catch (Exception e) 
	         {
	        e.printStackTrace();
	    }
	    return eventIds;
	}
	
	

}
