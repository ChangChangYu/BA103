package com.clubImg.model;

import java.sql.Date;

public class ClubImgVO  implements java.io.Serializable{
	private Integer clubImgID;
	private Integer clubAlbumID;
	private Integer memID;
	private Date clubImgDate;
	private String clubImgContent;
	//private Blob clubImg;
	private Integer clubImgStatus;
	public Integer getClubImgID() {
		return clubImgID;
	}
	public void setClubImgID(Integer clubImgID) {
		this.clubImgID = clubImgID;
	}
	public Integer getClubAlbumID() {
		return clubAlbumID;
	}
	public void setClubAlbumID(Integer clubAlbumID) {
		this.clubAlbumID = clubAlbumID;
	}
	public Integer getMemID() {
		return memID;
	}
	public void setMemID(Integer memID) {
		this.memID = memID;
	}
	public Date getClubImgDate() {
		return clubImgDate;
	}
	public void setClubImgDate(Date clubImgDate) {
		this.clubImgDate = clubImgDate;
	}
	public String getClubImgContent() {
		return clubImgContent;
	}
	public void setClubImgContent(String clubImgContent) {
		this.clubImgContent = clubImgContent;
	}
	public Integer getClubImgStatus() {
		return clubImgStatus;
	}
	public void setClubImgStatus(Integer clubImgStatus) {
		this.clubImgStatus = clubImgStatus;
	}
	
	
	
	
	
}
