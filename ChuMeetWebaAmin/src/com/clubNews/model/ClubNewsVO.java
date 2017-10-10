package com.clubNews.model;
import java.sql.Date;

public class ClubNewsVO implements java.io.Serializable{
	private Integer clubNewsID;
	private Integer clubID;
	private Integer memID;
	private Date clubNewsDate;
	private String clubNewsTitle;
	private String clubNewsContent;
	private Integer actID;
	public Integer getClubNewsID() {
		return clubNewsID;
	}
	public void setClubNewsID(Integer clubNewsID) {
		this.clubNewsID = clubNewsID;
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
	public Date getClubNewsDate() {
		return clubNewsDate;
	}
	public void setClubNewsDate(Date clubNewsDate) {
		this.clubNewsDate = clubNewsDate;
	}
	public String getClubNewsTitle() {
		return clubNewsTitle;
	}
	public void setClubNewsTitle(String clubNewsTitle) {
		this.clubNewsTitle = clubNewsTitle;
	}
	public String getClubNewsContent() {
		return clubNewsContent;
	}
	public void setClubNewsContent(String clubNewsContent) {
		this.clubNewsContent = clubNewsContent;
	}
	public Integer getActID() {
		return actID;
	}
	public void setActID(Integer actID) {
		this.actID = actID;
	}
	

	
}
