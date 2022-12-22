package com.tickethandling.dto;

public class TicketCreateRequestDTO {

	private Long creatoruserid;
	private Long creatorroleid;
	private Long orgid;
	private Long depid;
	private String tags;
	private String content;
	private String title;


	public Long getCreatoruserid() {
		return creatoruserid;
	}

	public void setCreatoruserid(Long creatoruserid) {
		this.creatoruserid = creatoruserid;
	}

	public Long getCreatorroleid() {
		return creatorroleid;
	}

	public void setCreatorroleid(Long creatorroleid) {
		this.creatorroleid = creatorroleid;
	}

	public Long getOrgid() {
		return orgid;
	}

	public void setOrgid(Long orgid) {
		this.orgid = orgid;
	}

	public Long getDepid() {
		return depid;
	}

	public void setDepid(Long depid) {
		this.depid = depid;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
