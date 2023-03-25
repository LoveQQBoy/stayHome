package com.ispan.stayhome.TLK_announcement.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="AnnouncementPicture")
public class AnnouncementPicture {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="P_Id")
	private Integer P_Id;
	
	@Column(name="PictureNameMany")
	private String pictureNameMany;
	
	@ManyToOne
	@JoinColumn(name="FKAnnouncement")
	private Announcement announcement;


	public Integer getP_Id() {
		return P_Id;
	}

	public void setP_Id(Integer p_Id) {
		P_Id = p_Id;
	}

	public String getPictureNameMany() {
		return pictureNameMany;
	}

	public void setPictureNameMany(String pictureNameMany) {
		this.pictureNameMany = pictureNameMany;
	}

	public Announcement getAnnouncement() {
		return announcement;
	}

	public void setAnnouncement(Announcement announcement) {
		this.announcement = announcement;
	}

	public AnnouncementPicture() {
		super();
	}
	
	
	public AnnouncementPicture(Integer p_Id, String pictureNameMany, Announcement announcement) {
		super();
		P_Id = p_Id;
		this.pictureNameMany = pictureNameMany;
		this.announcement = announcement;
	}

	public AnnouncementPicture(String pictureNameMany, Announcement announcement) {
		super();
		this.pictureNameMany = pictureNameMany;
		this.announcement = announcement;
	}

	
	
	
	
}

