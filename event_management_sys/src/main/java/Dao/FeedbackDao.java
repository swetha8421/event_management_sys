package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.Event;
import Model.EventAttendees;
import Model.EventFeedback;
import Model.Feedback;
import Utility_folder.Connection_Db;

public class FeedbackDao 
{
	public boolean insertFeedback(Feedback feedback) 
	{
		try 
		{
			Connection_Db obj = new Connection_Db();
			Connection conn = obj.connect_to_db();
			
			String query = "INSERT INTO feedback"
					+ "(event_id,attendee_name,attendee_id,rating,comments)"
					+ "VALUES(?,?,?,?,?)";
			PreparedStatement pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, feedback.getEventId());
			pstmt.setString(2,feedback.getAttendeeName());
			pstmt.setInt(3, feedback.getAttendeeId());
			pstmt.setInt(4,feedback.getRating());
			pstmt.setString(5,feedback.getComments());
		
			
			int rowUpdated = pstmt.executeUpdate();
			return rowUpdated>0;
		}
		catch(Exception e) 
		{
			e.printStackTrace();
			return false;
		}
	}
	
	public List<Feedback> getFeedbackByAttendeeId(int id) 
	{
		try 
		{
			Connection_Db obj = new Connection_Db();
			Connection conn = obj.connect_to_db();
			
			String query = "SELECT f.*, e.name AS event_name " +
		               "FROM feedback f " +
		               "JOIN events e ON f.event_id = e.id " +
		               "WHERE f.attendee_id = ?";	
			PreparedStatement pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, id);
			
			ResultSet rs = pstmt.executeQuery();
			
			List<Feedback> feedbacks = new ArrayList<Feedback>();
			
			while(rs.next()) 
			{
				Feedback feedback = new Feedback();
				feedback.setId(rs.getInt("id"));
				feedback.setAttendeeId(rs.getInt("attendee_id"));
				feedback.setRating(rs.getInt("rating"));
				feedback.setComments(rs.getString("comments"));
				feedback.setEventId(rs.getInt("event_id"));
				feedback.setEventName(rs.getString("event_name"));
				feedbacks.add(feedback);
			}
			return feedbacks;
			
		}
		catch(Exception e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public Feedback getFeedbackById(int id) 
	{
		try 
		{
			Connection_Db obj = new Connection_Db();
			Connection conn = obj.connect_to_db();
			
			String query = "SELECT f.*, e.name AS event_name " +
		               "FROM feedback f " +
		               "JOIN events e ON f.event_id = e.id " +
		               "WHERE f.id = ?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, id);
			
			ResultSet rs = pstmt.executeQuery();
			
			Feedback feedback = new Feedback();
			while(rs.next()) 
			{
				feedback.setAttendeeId(rs.getInt("attendee_id"));
				feedback.setAttendeeName(rs.getString("attendee_name"));
				feedback.setEventId(rs.getInt("event_id"));
				feedback.setRating(rs.getInt("rating"));
				feedback.setComments(rs.getString("comments"));
				feedback.setEventName(rs.getString("event_name"));
				
			}
			return feedback;
			
		}
		catch(Exception e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public boolean updateByFeedbackByid(Feedback feedback,int id) 
	{
		try 
		{
			Connection_Db obj = new Connection_Db();
			
			Connection conn = obj.connect_to_db();
			
			String query = "UPDATE feedback SET rating = ?,comments=?,attendee_name=?,attendee_id=?,event_id=? WHERE id = ?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1,feedback.getRating());
			pstmt.setString(2, feedback.getComments());
			pstmt.setString(3,feedback.getAttendeeName());
			pstmt.setInt(4, feedback.getAttendeeId());
			pstmt.setInt(5, feedback.getEventId());
			pstmt.setInt(6, id);
			
			
			int rowUpdated = pstmt.executeUpdate();
			return rowUpdated>0;
			
			
			
		}
		catch(Exception e) 
		{
			e.printStackTrace();
			return false;
		}
	}
		public List<Feedback> getFilteredFeedback(Integer rating, String searchTerm) throws SQLException {
		    List<Feedback> list = new ArrayList<>();
		    Connection conn = null;
		    PreparedStatement ps = null;
		    ResultSet rs = null;

		    try {
		    	Connection_Db obj = new Connection_Db();
		    	
		        conn = obj.connect_to_db();

		        StringBuilder query = new StringBuilder("SELECT f.*,e.name  FROM feedback f JOIN events e ON f.event_id = e.id WHERE 1=1");
		        if (rating != null) {
		            query.append(" AND rating = ?");
		        }
		        if (searchTerm != null && !searchTerm.trim().isEmpty()) {
		            query.append(" AND (name LIKE ? OR comments LIKE ?)");
		        }

		        ps = conn.prepareStatement(query.toString());

		        int index = 1;
		        if (rating != null) {
		            ps.setInt(index++, rating);
		        }
		        if (searchTerm != null && !searchTerm.trim().isEmpty()) {
		            String term = "%" + searchTerm.trim() + "%";
		            ps.setString(index++, term);
		            ps.setString(index++, term);
		        }

		        rs = ps.executeQuery();
		        while (rs.next()) {
		            Feedback f = new Feedback();
		            f.setId(rs.getInt("id"));
		            f.setEventId(rs.getInt("event_id"));
		            f.setEventName(rs.getString("name"));
		            f.setRating(rs.getInt("rating"));
		            f.setComments(rs.getString("comments"));
		            list.add(f);
		        }

		    } catch (Exception e) {
		        e.printStackTrace();
		    } finally {
		        conn.close();
		    }

		    return list;
		}
		
		public List<EventFeedback> getFeedbackByOrgId(int id)
		{
			try 
			{
				Connection_Db obj = new Connection_Db(); 
				Connection conn = obj.connect_to_db();
				
				String query = "SELECT f.id AS feedback_id, f.attendee_name, f.attendee_id, f.rating, f.comments, " +
			               "e.id AS event_id, e.name AS event_name, e.event_location " +
			               "FROM feedback f JOIN events e ON f.event_id = e.id " +
			               "WHERE e.organizer_id = ?";
				
				PreparedStatement pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, id);
				
				List<EventFeedback> eventFeedbacks = new ArrayList<EventFeedback>();
				ResultSet rs = pstmt.executeQuery();
				
				
				
				while(rs.next()) 
				{
					EventFeedback eventFeedback = new EventFeedback();
					eventFeedback.setEventId(rs.getInt("event_id"));
					eventFeedback.setEventName(rs.getString("event_name"));
					eventFeedback.setEventLocation(rs.getString("event_location"));
					
					eventFeedback.setFeedbackId(rs.getInt("feedback_id"));
					eventFeedback.setRating(rs.getInt("rating"));
					eventFeedback.setAttendeeId(rs.getInt("attendee_id"));
					eventFeedback.setAttendeeName(rs.getString("attendee_name"));
					eventFeedback.setComments(rs.getString("comments"));
					
					eventFeedbacks.add(eventFeedback);
				}
				return eventFeedbacks;
			}
			catch(Exception e) 
			{
				e.printStackTrace();
				return null;
			}
		}
		
		public static boolean deleteFeedbackById(int feedbackId) 
		{
			boolean rowDeleted = false;
			try 
			{
				Connection_Db obj = new Connection_Db();
				Connection conn = obj.connect_to_db();
				
				String query = "DELETE FROM feedback WHERE id = ?";
				PreparedStatement pstmt = conn.prepareStatement(query);
				
				pstmt.setInt(1, feedbackId);
				rowDeleted = pstmt.executeUpdate()>0;
				
			}
			catch(Exception e) 
			{
				e.printStackTrace();
			}
			return rowDeleted;
		}
		
		public static List<EventFeedback> getFilteredFeedback(int organizerId, String ratingStr, String searchTerm) {
		    List<EventFeedback> list = new ArrayList<>();

		    StringBuilder sql = new StringBuilder(
		        "SELECT f.id AS feedback_id, f.attendee_name, f.rating, f.comments, " +
		        "e.name AS event_name, e.event_location " +
		        "FROM feedback f JOIN events e ON f.event_id = e.id " +
		        "WHERE e.organizer_id = ?");

		    List<Object> params = new ArrayList<>();
		    params.add(organizerId);

		    if (ratingStr != null && !ratingStr.isEmpty()) {
		        sql.append(" AND f.rating = ?");
		        params.add(Integer.parseInt(ratingStr));
		    }

		    if (searchTerm != null && !searchTerm.trim().isEmpty()) {
		        sql.append(" AND (LOWER(e.name) LIKE ? OR LOWER(e.event_location) LIKE ? OR LOWER(f.attendee_name) LIKE ?)");
		        String likeTerm = "%" + searchTerm.toLowerCase() + "%";
		        params.add(likeTerm);
		        params.add(likeTerm);
		        params.add(likeTerm);
		    }

		    try (Connection conn = new Connection_Db().connect_to_db();
		         PreparedStatement stmt = conn.prepareStatement(sql.toString())) {

		        for (int i = 0; i < params.size(); i++) {
		            stmt.setObject(i + 1, params.get(i));
		        }

		        ResultSet rs = stmt.executeQuery();
		        while (rs.next()) {
		            EventFeedback fb = new EventFeedback();
		            fb.setFeedbackId(rs.getInt("feedback_id"));
		            fb.setAttendeeName(rs.getString("attendee_name"));
		            fb.setRating(rs.getInt("rating"));
		            fb.setComments(rs.getString("comments"));
		            fb.setEventName(rs.getString("event_name"));
		            fb.setEventLocation(rs.getString("event_location"));
		            list.add(fb);
		        }

		    } catch (Exception e) {
		        e.printStackTrace();
		    }

		    return list;
		}
	}

