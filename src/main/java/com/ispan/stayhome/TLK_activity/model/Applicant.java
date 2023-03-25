package com.ispan.stayhome.TLK_activity.model;

import java.util.Date;

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

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.ispan.stayhome.CPC_login.model.HouseholdData;

@Entity
@Table(name="Applicant")
public class Applicant {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "P_Id")
	private Integer P_Id;
	
	@Column(name = "Applicant_Status")
	private String applicantStatus;
	
	
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Column(name = "Registration_Date")
	private Date registrationDate;
	
	//@JsonBackReference //當讀到JsonBackReference 就不會序列化 由另外一邊進行
	@ManyToOne
	@JoinColumn(name="Householder_Id")
	private HouseholdData householdData;
	
	@ManyToOne
	@JoinColumn(name="Applicant_Id")
	private Activity activity;
	
	@PrePersist
	public void createDate() {
		if(registrationDate==null) {
			registrationDate=new Date();
		}
	}
	
	
	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	public Integer getP_Id() {
		return P_Id;
	}

	public void setP_Id(Integer p_Id) {
		P_Id = p_Id;
	}


	public String getApplicantStatus() {
		return applicantStatus;
	}

	public void setApplicantStatus(String applicantStatus) {
		this.applicantStatus = applicantStatus;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	

	public HouseholdData getHouseholdData() {
		return householdData;
	}


	public void setHouseholdData(HouseholdData householdData) {
		this.householdData = householdData;
	}


	public Applicant() {
		super();
	}


	public Applicant(String applicantStatus, HouseholdData householdData,
			Activity activity) {
		super();
		this.applicantStatus = applicantStatus;
		this.householdData = householdData;
		this.activity = activity;
	}

	

	
	
	
	
}
