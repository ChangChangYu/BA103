package com.admPril.model;

import java.sql.Date;

public class AdmPrilVO implements java.io.Serializable  {
	//管理員ID
	private Integer admPriladminID;
	//權限編號
	private Integer admPrilID;
	private Date admPrildate;
	private Integer admPrilStatus;
	public Integer getAdmPriladminID() {
		return admPriladminID;
	}
	public void setAdmPriladminID(Integer admPriladminID) {
		this.admPriladminID = admPriladminID;
	}
	public Integer getAdmPrilID() {
		return admPrilID;
	}
	public void setAdmPrilID(Integer admPrilID) {
		this.admPrilID = admPrilID;
	}
	public Date getAdmPrildate() {
		return admPrildate;
	}
	public void setAdmPrildate(Date admPrildate) {
		this.admPrildate = admPrildate;
	}
	public Integer getAdmPrilStatus() {
		return admPrilStatus;
	}
	public void setAdmPrilStatus(Integer admPrilStatus) {
		this.admPrilStatus = admPrilStatus;
	}
	
	
	
}
