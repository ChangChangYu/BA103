package com.clubMB.model;
import java.sql.Date;

public class ClubMBVO implements java.io.Serializable{
	private Integer clubMBID;
	private Integer clubID;
	private Integer memID;
	private String clubMBContent;
	private Date clubMBDate;
	private Integer clubMBStatus;
	
	public Integer getClubMBID() {
		return clubMBID;
	}
	public void setClubMBID(Integer clubMBID) {
		this.clubMBID = clubMBID;
	}
	public Integer getClubID() {
		return clubID;
	}
	public void setClubID(Integer clubID) {
		this.clubID = clubID;
	}
	public Integer getMemID() {
		return memID;
	}
	public void setMemID(Integer memID) {
		this.memID = memID;
	}
	public String getClubMBContent() {
		return clubMBContent;
	}
	public void setClubMBContent(String clubMBContent) {
		this.clubMBContent = clubMBContent;
	}
	public Date getClubMBDate() {
		return clubMBDate;
	}
	public void setClubMBDate(Date clubMBDate) {
		this.clubMBDate = clubMBDate;
	}
	public Integer getClubMBStatus() {
		return clubMBStatus;
	}
	public void setClubMBStatus(Integer clubMBStatus) {
		this.clubMBStatus = clubMBStatus;
	}
	
	
}
