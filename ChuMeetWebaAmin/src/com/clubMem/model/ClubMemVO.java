package com.clubMem.model;

import java.sql.Date;

public class ClubMemVO  implements java.io.Serializable{
	private Integer memID;
	private Integer clubID;
	private Integer clubMemType;
	private Date clubMemJoinDate;
	public Integer getMemID() {
		return memID;
	}
	public void setMemID(Integer memID) {
		this.memID = memID;
	}
	public Integer getClubID() {
		return clubID;
	}
	public void setClubID(Integer clubID) {
		this.clubID = clubID;
	}
	public Integer getClubMemType() {
		return clubMemType;
	}
	public void setClubMemType(Integer clubMemType) {
		this.clubMemType = clubMemType;
	}
	public Date getClubMemJoinDate() {
		return clubMemJoinDate;
	}
	public void setClubMemJoinDate(Date clubMemJoinDate) {
		this.clubMemJoinDate = clubMemJoinDate;
	}
	
	
}
