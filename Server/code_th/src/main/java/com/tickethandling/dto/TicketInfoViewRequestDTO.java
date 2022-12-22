package com.tickethandling.dto;

public class TicketInfoViewRequestDTO {

	private Long userid;
	private int ticketstatus;
	private int isBookMarkedStatus = -1;
	private int pageNumber;
	private int depId;
	private int orgId;

	public int getDepId() {
		return depId;
	}

	public void setDepId(int depId) {
		this.depId = depId;
	}

	public int getOrgId() {
		return orgId;
	}

	public void setOrgId(int orgId) {
		this.orgId = orgId;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getNumberOfElementsRequired() {
		return numberOfElementsRequired;
	}

	public void setNumberOfElementsRequired(int numberOfElementsRequired) {
		this.numberOfElementsRequired = numberOfElementsRequired;
	}

	private int numberOfElementsRequired;

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public int getTicketstatus() {
		return ticketstatus;
	}

	public void setTicketstatus(int ticketstatus) {
		this.ticketstatus = ticketstatus;
	}

	public int getIsBookMarkedStatus() {
		return isBookMarkedStatus;
	}

	public void setIsBookMarkedStatus(int isBookMarkedStatus) {
		this.isBookMarkedStatus = isBookMarkedStatus;
	}

	public String getContentFilter() {
		return contentFilter;
	}

	public void setContentFilter(String contentFilter) {
		this.contentFilter = contentFilter;
	}

	public String getTitleFlter() {
		return titleFlter;
	}

	public void setTitleFlter(String titleFlter) {
		this.titleFlter = titleFlter;
	}

	private String contentFilter;
	private String titleFlter;

}
