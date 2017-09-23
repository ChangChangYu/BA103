package com.FAQ.model;

import java.sql.Timestamp;

public class FAQVO implements java.io.Serializable {

	private Integer FAQID;
	private String FAQTitle;
	private String FAQComtent;
	private Timestamp FAQdate;
	public Integer getFAQID() {
		return FAQID;
	}
	public void setFAQID(Integer fAQID) {
		FAQID = fAQID;
	}
	public String getFAQTitle() {
		return FAQTitle;
	}
	public void setFAQTitle(String fAQTitle) {
		FAQTitle = fAQTitle;
	}
	public String getFAQComtent() {
		return FAQComtent;
	}
	public void setFAQComtent(String fAQComtent) {
		FAQComtent = fAQComtent;
	}
	public Timestamp getFAQdate() {
		return FAQdate;
	}
	public void setFAQdate(Timestamp fAQdate) {
		FAQdate = fAQdate;
	}
	
	
	
}
