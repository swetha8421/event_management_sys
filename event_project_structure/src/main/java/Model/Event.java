package Model;

import java.sql.Date;

public class Event 

{
	private int id;
	private String name;
	private String location;
	private int eventType;
	private Date startDate;
	private Date endDate;
	private int eventStatus;
	private String event_status;
	private String event_type;
	private int budget;
	private int expectedAttendees;
	private int organizerId;
	private String description;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public int getEventType() {
		return eventType;
	}
	public void setEventType(int eventType) {
		this.eventType = eventType;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public int getEventStatus() {
		return eventStatus;
	}
	public void setEventStatus(int eventStatus) {
		this.eventStatus = eventStatus;
	}
	public int getBudget() {
		return budget;
	}
	public void setBudget(int budget) {
		this.budget = budget;
	}
	public int getExpectedAttendees() {
		return expectedAttendees;
	}
	public void setExpectedAttendees(int expectedAttendees) {
		this.expectedAttendees = expectedAttendees;
	}
	public int getOrganizerId() {
		return organizerId;
	}
	public void setOrganizerId(int organizerId) {
		this.organizerId = organizerId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getEvent_status() {
		return event_status;
	}
	public void setEvent_status(String event_status) {
		this.event_status = event_status;
	}
	public String getEvent_type() {
		return event_type;
	}
	public void setEvent_type(String event_type) {
		this.event_type = event_type;
	}
	
	
	
	

}
