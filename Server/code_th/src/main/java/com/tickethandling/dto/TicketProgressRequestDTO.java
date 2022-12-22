package com.tickethandling.dto;

public class TicketProgressRequestDTO {
	public Long getCurrentassigneeuserid() {
		return currentassigneeuserid;
	}

	public void setCurrentassigneeuserid(Long currentassigneeuserid) {
		this.currentassigneeuserid = currentassigneeuserid;
	}

	public Long getCurrentassigneeroleid() {
		return currentassigneeroleid;
	}

	public void setCurrentassigneeroleid(Long currentassigneeroleid) {
		this.currentassigneeroleid = currentassigneeroleid;
	}

	public Long getAssignedbyuserid() {
		return assignedbyuserid;
	}

	public void setAssignedbyuserid(Long assignedbyuserid) {
		this.assignedbyuserid = assignedbyuserid;
	}

	public Long getAssignedbyroleid() {
		return assignedbyroleid;
	}

	public void setAssignedbyroleid(Long assignedbyroleid) {
		this.assignedbyroleid = assignedbyroleid;
	}

	public Long getTicketid() {
		return ticketid;
	}

	public void setTicketid(Long ticketid) {
		this.ticketid = ticketid;
	}

	private Long currentassigneeuserid;
	private Long currentassigneeroleid;
	private Long assignedbyuserid;
	private Long assignedbyroleid;
	private Long ticketid;
	private String workdone;
	private String notes;

	public String getWorkdone() {
		return workdone;
	}

	public void setWorkdone(String workdone) {
		this.workdone = workdone;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public boolean getClosing() {
		return closing;
	}

	public void setClosing(boolean closing) {
		this.closing = closing;
	}

	private boolean closing = false;

}
