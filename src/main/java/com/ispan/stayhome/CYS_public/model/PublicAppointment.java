package com.ispan.stayhome.CYS_public.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import com.ispan.stayhome.CYS_repair.model.HouseholdData;

@Entity
@Table(name = "PublicAppointment")
public class PublicAppointment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Appointment_Id")
	private Integer id;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
	@Column(name = "Date")
	private Date date;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	@Column(name = "Appointment_Date")
	private Date appointmentDate;

	@Column(name = "Appointment_Time")
	private String appointmentTime;

	@Column(name = "Appointment_Number")
	private Integer appointmentNumber;

	@Column(name = "Appointment_State")
	private String appointmentState;

	@Transient
	private Integer publicId;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "FK_Resident_Id")
	private HouseholdData resident;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "FK_Public_Id", nullable = true)
	private CreatePublic public_;

	@PrePersist
	public void onCreate() {
		if(date == null) {
			date= new Date();
		}
	}	

	public PublicAppointment() {
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

	public Date getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(Date appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public String getAppointmentTime() {
		return appointmentTime;
	}

	public void setAppointmentTime(String appointmentTime) {
		this.appointmentTime = appointmentTime;
	}

	public Integer getAppointmentNumber() {
		return appointmentNumber;
	}

	public void setAppointmentNumber(Integer appointmentNumber) {
		this.appointmentNumber = appointmentNumber;
	}

	public String getAppointmentState() {
		return appointmentState;
	}

	public void setAppointmentState(String appointmentState) {
		this.appointmentState = appointmentState;
	}

	public Integer getPublicId() {
		return publicId;
	}

	public void setPublicId(Integer publicId) {
		this.publicId = publicId;
	}

	public HouseholdData getResident() {
		return resident;
	}

	public void setResident(HouseholdData resident) {
		this.resident = resident;
	}

	public CreatePublic getPublic_() {
		return public_;
	}

	public void setPublic_(CreatePublic public_) {
		this.public_ = public_;
	}

}
