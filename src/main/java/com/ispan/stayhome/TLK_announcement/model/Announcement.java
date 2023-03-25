package com.ispan.stayhome.TLK_announcement.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ispan.stayhome.CPC_login.model.HouseholdData;
import com.ispan.stayhome.TLK_activity.model.Applicant;

@Entity
@Table(name="Announcement")
public class Announcement {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="P_Id")
	private Integer P_Id;
	

	@ManyToOne
	@JoinColumn(name="Manager_Id")
	private HouseholdData householdData;
	
	@Column(name="Title")
	private String title;
	
	@Column(name="Text")
	private String text;
	
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
	@Column(name="Announcement_Date")
	private Date announcementDate;
	
	@Column(name="PictureName")
	private String pictureName;
	
	@OneToMany(mappedBy = "announcement")
	private List<AnnouncementPicture> announcementPicture = new ArrayList<>();
	
	
	public Announcement() {
	}

	public Integer getP_Id() {
		return P_Id;
	}

	public void setP_Id(Integer p_Id) {
		P_Id = p_Id;
	}

	public HouseholdData getHouseholdData() {
		return householdData;
	}

	public void setHouseholdData(HouseholdData householdData) {
		this.householdData = householdData;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getAnnouncementDate() {
		return announcementDate;
	}

	public void setAnnouncementDate(Date announcementDate) {
		this.announcementDate = announcementDate;
	}
	
	

	public List<AnnouncementPicture> getAnnouncementPicture() {
		return announcementPicture;
	}

	public void setAnnouncementPicture(List<AnnouncementPicture> announcementPicture) {
		this.announcementPicture = announcementPicture;
	}

	public Announcement(HouseholdData householdData, String title, String text, Date announcementDate) {
		super();
		this.householdData = householdData;
		this.title = title;
		this.text = text;
		this.announcementDate = announcementDate;
	}

	public String getPictureName() {
		return pictureName;
	}

	public void setPictureName(String pictureName) {
		this.pictureName = pictureName;
	}

	public Announcement(HouseholdData householdData, String title, String text, Date announcementDate,
			String pictureName) {
		super();
		this.householdData = householdData;
		this.title = title;
		this.text = text;
		this.announcementDate = announcementDate;
		this.pictureName = pictureName;
	}

	public Announcement(Integer p_Id, HouseholdData householdData, String title, String text, Date announcementDate) {
		super();
		P_Id = p_Id;
		this.householdData = householdData;
		this.title = title;
		this.text = text;
		this.announcementDate = announcementDate;
	}

	public Announcement(Integer p_Id, HouseholdData householdData, String title, String text, Date announcementDate,
			String pictureName) {
		super();
		P_Id = p_Id;
		this.householdData = householdData;
		this.title = title;
		this.text = text;
		this.announcementDate = announcementDate;
		this.pictureName = pictureName;
	}

	
	
	
}
