package com.tickethandling.dto;

public class PieChartDataCountResponseDTO {

	private Long openticketcount;
	private Long progressticketcount;
	private Long closedtcketcount;
	private Long totalticketscount;

	public Long getOpenticketcount() {
		return openticketcount;
	}

	public void setOpenticketcount(Long openticketcount) {
		this.openticketcount = openticketcount;
	}

	public Long getProgressticketcount() {
		return progressticketcount;
	}

	public void setProgressticketcount(Long progressticketcount) {
		this.progressticketcount = progressticketcount;
	}

	public Long getClosedtcketcount() {
		return closedtcketcount;
	}

	public void setClosedtcketcount(Long closedtcketcount) {
		this.closedtcketcount = closedtcketcount;
	}

	public Long getTotalticketscount() {
		return totalticketscount;
	}

	public void setTotalticketscount(Long totalticketscount) {
		this.totalticketscount = totalticketscount;
	}

}
