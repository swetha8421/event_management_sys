package Model;



public class Feedback 
{
	private int id;
	private int eventId;
	private String attendeeName;
	private int attendeeId;
	private int rating;
	
	private String comments;
	private String eventName;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getEventId() {
		return eventId;
	}
	public void setEventId(int eventId) {
		this.eventId = eventId;
	}
	public String getAttendeeName() {
		return attendeeName;
	}
	public void setAttendeeName(String attendeeName) {
		this.attendeeName = attendeeName;
	}
	public int getAttendeeId() {
		return attendeeId;
	}
	public void setAttendeeId(int attendeeId) {
		this.attendeeId = attendeeId;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	
	

}
