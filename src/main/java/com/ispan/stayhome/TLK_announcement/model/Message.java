package com.ispan.stayhome.TLK_announcement.model;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.ispan.stayhome.CPC_login.model.HouseholdData;

@Entity
@Table(name="Message")
public class Message {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="P_Id")
	private Integer p_Id;
	
	@Column(name="Message_Response")
	private String messageResponse;
	
	@ManyToOne
	@JoinColumn(name="Householder_Id")
	private HouseholdData householdData;
	
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
	@Column(name="Message_Date")
	private Date messageDate;
	
	@ManyToOne
	@JoinColumn(name="Response_Id")
	private Announcement announcement;
	
	
	
	public Integer getP_Id() {
		return p_Id;
	}



	public void setP_Id(Integer p_Id) {
		this.p_Id = p_Id;
	}



	public HouseholdData getHouseholdData() {
		return householdData;
	}



	public void setHouseholdData(HouseholdData householdData) {
		this.householdData = householdData;
	}



	public Date getMessageDate() {
		return messageDate;
	}



	public void setMessageDate(Date messageDate) {
		this.messageDate = messageDate;
	}



	public Announcement getAnnouncement() {
		return announcement;
	}



	public void setAnnouncement(Announcement announcement) {
		this.announcement = announcement;
	}



	public String getMessageResponse() {
		return messageResponse;
	}



	public void setMessageResponse(String messageResponse) {
		this.messageResponse = messageResponse;
	}



	public Message(String messageResponse, HouseholdData householdData, Date messageDate, Announcement announcement) {
		super();
		this.messageResponse = messageResponse;
		this.householdData = householdData;
		this.messageDate = messageDate;
		this.announcement = announcement;
	}



	public Message() {
	}

}
