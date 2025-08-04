package Dao;

import java.sql.SQLException;
import java.util.List;

import Model.EventFeedback;
import Model.Feedback;

public interface FeedbackDao 
{
	public boolean insertFeedback(Feedback feedback);
	public List<Feedback> getFeedbackByAttendeeId(int id);
	public Feedback getFeedbackById(int id);
	public boolean updateByFeedbackByid(Feedback feedback,int id);
	public List<Feedback> getFilteredFeedback(Integer rating, String searchTerm) throws SQLException;
	public List<EventFeedback> getFeedbackByOrgId(int id);
	public  boolean deleteFeedbackById(int feedbackId);
	public  List<EventFeedback> getFilteredFeedback(int organizerId, String ratingStr, String searchTerm);
}
