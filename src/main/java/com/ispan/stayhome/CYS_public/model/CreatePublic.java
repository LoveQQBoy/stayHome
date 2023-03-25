package com.ispan.stayhome.CYS_public.model;



import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "CreatePublic")
public class CreatePublic {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Public_Id")
	private Integer id;
	
	@Temporal(TemporalType.TIMESTAMP)   
	@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
	@Column(name = "Date")
	private Date date;
	
	@Column(name = "Public_Name")
	private String publicName;
	
	@Column(name = "Public_Photo")
	private byte[] publicPhoto;
	
	@Transient
	MultipartFile m_publicPhoto;
	
	@Column(name = "Days_Of_Week")
	private String daysOfWeek;
	
//	@Temporal(TemporalType.TIME)
//	@DateTimeFormat(pattern = "HH:mm")
//	@JsonFormat(pattern = "HH:mm:ss", timezone = "GMT+8")
	@Column(name = "Opening_Hour")
	private String openingHour;
	
//	@Temporal(TemporalType.TIME)
//	@DateTimeFormat(pattern = "HH:mm")
//	@JsonFormat(pattern = "HH:mm:ss", timezone = "GMT+8")
	@Column(name = "Closing_Hour")
	private String closingHour;
	
	@Column(name = "Inter_Minute")
	private Integer interMinute;
	
	@Column(name = "Inter_Times")
	private String interTimes;
	
	@Column(name = "Limit_Number")
	private Integer limitNumber;
	
	@Column(name = "State")
	private String state;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy/MM/dd")
//	@JsonFormat(pattern = "yyyy/MM/dd", timezone = "GMT+8")
	@Column(name = "Appointment_Date_Limit")
	private Date appointmentDateLimit;	
	
	@OneToMany(mappedBy = "public_",orphanRemoval = true ) //orphanRemoval = true 
	private List<PublicAppointment> publicAppointment = new ArrayList<>();
	
	
	@PrePersist
	public void onCreate() {
		if(date == null) {
			date= new Date();
		}
	}	
	
	
	public CreatePublic() {
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public String getPublicName() {
		return publicName;
	}


	public void setPublicName(String publicName) {
		this.publicName = publicName;
	}


	public byte[] getPublicPhoto() {
		return publicPhoto;
	}


	public void setPublicPhoto(byte[] publicPhoto) {
		this.publicPhoto = publicPhoto;
	}


	public MultipartFile getM_publicPhoto() {
		return m_publicPhoto;
	}


	public void setM_publicPhoto(MultipartFile m_publicPhoto) {
		this.m_publicPhoto = m_publicPhoto;
	}


	public String getDaysOfWeek() {
		return daysOfWeek;
	}


	public void setDaysOfWeek(String daysOfWeek) {
		this.daysOfWeek = daysOfWeek;
	}


	public String getOpeningHour() {
		return openingHour;
	}


	public void setOpeningHour(String openingHour) {
		this.openingHour = openingHour;
	}


	public String getClosingHour() {
		return closingHour;
	}


	public void setClosingHour(String closingHour) {
		this.closingHour = closingHour;
	}


	public Integer getInterMinute() {
		return interMinute;
	}


	public void setInterMinute(Integer interMinute) {
		this.interMinute = interMinute;
	}


	public String getInterTimes() {
		return interTimes;
	}


	public void setInterTimes(String interTimes) {
		this.interTimes = interTimes;
	}


	public Integer getLimitNumber() {
		return limitNumber;
	}


	public void setLimitNumber(Integer limitNumber) {
		this.limitNumber = limitNumber;
	}


	public Date getAppointmentDateLimit() {
		return appointmentDateLimit;
	}


	public void setAppointmentDateLimit(Date appointmentDateLimit) {
		this.appointmentDateLimit = appointmentDateLimit;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public List<PublicAppointment> getPublicAppointment() {
		return publicAppointment;
	}


	public void setPublicAppointment(List<PublicAppointment> publicAppointment) {
		this.publicAppointment = publicAppointment;
	}


	
	

}
