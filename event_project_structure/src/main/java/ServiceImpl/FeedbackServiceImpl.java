package ServiceImpl;

import java.sql.SQLException;
import java.util.List;

import Model.EventFeedback;
import Model.Feedback;
import Service.FeedbackService;

import Dao.FeedbackDao;
import DaoImpl.FeedbackDaoImpl;

public class FeedbackServiceImpl implements FeedbackService
{
	private FeedbackDao feedbackDao = new FeedbackDaoImpl();

	
	public boolean insertFeedback(Feedback feedback) {
		
		return feedbackDao.insertFeedback(feedback);
	}  

	
	public List<Feedback> getFeedbackByAttendeeId(int id) 
	{
		return feedbackDao.getFeedbackByAttendeeId(id);
	}

	
	public Feedback getFeedbackById(int id) 
	{
		return feedbackDao.getFeedbackById(id);
	}

	
	public boolean updateByFeedbackByid(Feedback feedback, int id) 
	{
		
		return feedbackDao.updateByFeedbackByid(feedback, id);
	}

	
	public List<Feedback> getFilteredFeedback(Integer rating, String searchTerm) throws SQLException 
	{
		return feedbackDao.getFilteredFeedback(rating, searchTerm);
	}

	
	public List<EventFeedback> getFeedbackByOrgId(int id) 
	{
		return feedbackDao.getFeedbackByOrgId(id);
	}

	
	public boolean deleteFeedbackById(int feedbackId) 
	{
		return feedbackDao.deleteFeedbackById(feedbackId);
	}

	
	public List<EventFeedback> getFilteredFeedback(int organizerId, String ratingStr, String searchTerm) 
	{
		
		return feedbackDao.getFilteredFeedback(organizerId, ratingStr, searchTerm);
	}
	

}
