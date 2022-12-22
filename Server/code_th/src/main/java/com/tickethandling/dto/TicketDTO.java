package com.tickethandling.dto;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ticket")
public class TicketDTO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTicketid() {
		return ticketid;
	}

	public void setTicketid(Long ticketid) {
		this.ticketid = ticketid;
	}

	public Long getCurrentassigneeuserid() {
		return currentassigneeuserid;
	}

	public void setCurrentassigneeuserid(Long currentassigneeuserid) {
		this.currentassigneeuserid = currentassigneeuserid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

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

	public String getImagepath() {
		return imagepath;
	}

	public void setImagepath(String imagepath) {
		this.imagepath = imagepath;
	}

	public Date getUpdatedon() {
		return updatedon;
	}

	public void setUpdatedon(Date updatedon) {
		this.updatedon = updatedon;
	}

	public Long getAssignedbyuserid() {
		return assignedbyuserid;
	}

	public void setAssignedbyuserid(Long assignedbyuserid) {
		this.assignedbyuserid = assignedbyuserid;
	}

	public Date getAssignedon() {
		return assignedon;
	}

	public void setAssignedon(Date assignedon) {
		this.assignedon = assignedon;
	}

	public Long getAssignedbyroleid() {
		return assignedbyroleid;
	}

	public void setAssignedbyroleid(Long assignedbyroleid) {
		this.assignedbyroleid = assignedbyroleid;
	}

	public Long getClosedbyroleid() {
		return closedbyroleid;
	}

	public void setClosedbyroleid(Long closedbyroleid) {
		this.closedbyroleid = closedbyroleid;
	}

	private Long ticketid;
	private Long currentassigneeuserid;

	public int getActivestage() {
		return activestage;
	}

	public void setActivestage(int activestage) {
		this.activestage = activestage;
	}

	public Long getCurrentassigneeroleid() {
		return currentassigneeroleid;
	}

	public void setCurrentassigneeroleid(Long currentassigneeroleid) {
		this.currentassigneeroleid = currentassigneeroleid;
	}

	public Date getClosedon() {
		return closedon;
	}

	public void setClosedon(Date closedon) {
		this.closedon = closedon;
	}

	public Long getClosedbyuserid() {
		return closedbyuserid;
	}

	public void setClosedbyuserid(Long closedbyuserid) {
		this.closedbyuserid = closedbyuserid;
	}

	private String title;
	private String content;
	private String workdone;
	private String notes;
	private String imagepath;
	private Date updatedon;
	private Long assignedbyuserid;
	private Date assignedon;
	private Long assignedbyroleid;
	private Long closedbyroleid;
	private int activestage;
	private Long closedbyuserid;
	private Date closedon;
	private Long currentassigneeroleid;

}
