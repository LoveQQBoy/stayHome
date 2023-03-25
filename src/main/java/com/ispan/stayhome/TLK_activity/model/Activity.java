package com.ispan.stayhome.TLK_activity.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ispan.stayhome.CPC_login.model.HouseholdData;

@Entity
@Table(name = "Activity")
public class Activity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "P_Id")
	private Integer P_Id;


	@Column(name = "Title")
	private String title;

	@Column(name = "Text")
	private String text;

	@Column(name = "Activity_Teacher")
	private String activityTeacher;

	@Column(name = "Place")
	private String place;

	@Column(name = "Teacher_Phone")
	private String teacherPhone;

	@Column(name = "Applicant_Number")
	private Integer applicantNumber;

	@Column(name = "Applicant_Number_Full")
	private Integer applicantNumberFull;

	@Column(name = "Applicant_Status")
	private String applicantStatus;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "Applicant_Create_Date")
	private Date applicantCreateDate;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "Applicant_Dead_Date")
	private Date applicantDeadDate;
	
	@Lob
	@Column(name = "Picture")
	private byte[] picture;
	
	@JsonBackReference //當讀到JsonBackReference 就不會序列化 由另外一邊進行
	@ManyToOne
	@JoinColumn(name="Manager_Id",foreignKey = @ForeignKey(name="FK_Activity_Manager_Id"))
	private HouseholdData householdData;

	@OneToMany(mappedBy = "activity")
	@JsonIgnore
	private List<Applicant> applicant = new ArrayList<>();
	
	@Column(name="Count")
	private Integer count;

	public Integer getP_Id() {
		return P_Id;
	}

	public void setP_Id(Integer p_Id) {
		P_Id = p_Id;
	}

//	public Integer getManagerId() {
//		return managerId;
//	}
//
//	public void setManagerId(Integer managerId) {
//		this.managerId = managerId;
//	}

//	public String getManagerName() {
//		return managerName;
//	}
//
//	public void setManagerName(String managerName) {
//		this.managerName = managerName;
//	}

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

	public String getActivityTeacher() {
		return activityTeacher;
	}

	public void setActivityTeacher(String activityTeacher) {
		this.activityTeacher = activityTeacher;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getTeacherPhone() {
		return teacherPhone;
	}

	public void setTeacherPhone(String teacherPhone) {
		this.teacherPhone = teacherPhone;
	}

	public Integer getApplicantNumber() {
		return applicantNumber;
	}

	public void setApplicantNumber(Integer applicantNumber) {
		this.applicantNumber = applicantNumber;
	}

	public Integer getApplicantNumberFull() {
		return applicantNumberFull;
	}

	public void setApplicantNumberFull(Integer applicantNumberFull) {
		this.applicantNumberFull = applicantNumberFull;
	}

	public String getApplicantStatus() {
		return applicantStatus;
	}

	public void setApplicantStatus(String applicantStatus) {
		this.applicantStatus = applicantStatus;
	}

	public Date getApplicantCreateDate() {
		return applicantCreateDate;
	}

	public void setApplicantCreateDate(Date applicantCreateDate) {
		this.applicantCreateDate = applicantCreateDate;
	}

	public Date getApplicantDeadDate() {
		return applicantDeadDate;
	}

	public void setApplicantDeadDate(Date applicantDeadDate) {
		this.applicantDeadDate = applicantDeadDate;
	}

	public byte[] getPicture() {
		return picture;
	}

	public void setPicture(byte[] picture) {
		this.picture = picture;
	}

	public List<Applicant> getApplicant() {
		return applicant;
	}

	public void setApplicant(List<Applicant> applicant) {
		this.applicant = applicant;
	}

	public Activity() {
		super();
	}

	
	
	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public HouseholdData getHouseholdData() {
		return householdData;
	}

	public void setHouseholdData(HouseholdData householdData) {
		this.householdData = householdData;
	}

	public Activity(String title, String text, String activityTeacher, String place, String teacherPhone,
			Integer applicantNumber, Integer applicantNumberFull, String applicantStatus, Date applicantCreateDate,
			Date applicantDeadDate, HouseholdData householdData) {
		super();
		this.title = title;
		this.text = text;
		this.activityTeacher = activityTeacher;
		this.place = place;
		this.teacherPhone = teacherPhone;
		this.applicantNumber = applicantNumber;
		this.applicantNumberFull = applicantNumberFull;
		this.applicantStatus = applicantStatus;
		this.applicantCreateDate = applicantCreateDate;
		this.applicantDeadDate = applicantDeadDate;
		this.householdData = householdData;
	}

	public Activity(String title, String text, String activityTeacher, String place, String teacherPhone,
			Integer applicantNumber, Integer applicantNumberFull, String applicantStatus, Date applicantCreateDate,
			Date applicantDeadDate, byte[] picture, HouseholdData householdData) {
		super();
		this.title = title;
		this.text = text;
		this.activityTeacher = activityTeacher;
		this.place = place;
		this.teacherPhone = teacherPhone;
		this.applicantNumber = applicantNumber;
		this.applicantNumberFull = applicantNumberFull;
		this.applicantStatus = applicantStatus;
		this.applicantCreateDate = applicantCreateDate;
		this.applicantDeadDate = applicantDeadDate;
		this.picture = picture;
		this.householdData = householdData;
	}

	public Activity(Integer p_Id, String title, String text, String activityTeacher, String place, String teacherPhone,
			Integer applicantNumber, Integer applicantNumberFull, String applicantStatus, Date applicantCreateDate,
			Date applicantDeadDate, HouseholdData householdData) {
		super();
		P_Id = p_Id;
		this.title = title;
		this.text = text;
		this.activityTeacher = activityTeacher;
		this.place = place;
		this.teacherPhone = teacherPhone;
		this.applicantNumber = applicantNumber;
		this.applicantNumberFull = applicantNumberFull;
		this.applicantStatus = applicantStatus;
		this.applicantCreateDate = applicantCreateDate;
		this.applicantDeadDate = applicantDeadDate;
		this.householdData = householdData;
	}

	
		
	
	

	
	
	
	
	
	

//	public Activity(Integer managerId, String managerName, String title, String text, String activityTeacher,
//			String place, String teacherPhone, Integer applicantNumber, Integer applicantNumberFull,
//			String applicantStatus, Date applicantCreateDate, Date applicantDeadDate, byte[] picture) {
//		super();
//		this.managerId = managerId;
//		this.managerName = managerName;
//		this.title = title;
//		this.text = text;
//		this.activityTeacher = activityTeacher;
//		this.place = place;
//		this.teacherPhone = teacherPhone;
//		this.applicantNumber = applicantNumber;
//		this.applicantNumberFull = applicantNumberFull;
//		this.applicantStatus = applicantStatus;
//		this.applicantCreateDate = applicantCreateDate;
//		this.applicantDeadDate = applicantDeadDate;
//		this.picture = picture;
//	}
//
//	public Activity(Integer managerId, String managerName, String title, String text, String activityTeacher,
//			String place, String teacherPhone, Integer applicantNumber, Integer applicantNumberFull,
//			String applicantStatus, Date applicantCreateDate, Date applicantDeadDate) {
//		super();
//		this.managerId = managerId;
//		this.managerName = managerName;
//		this.title = title;
//		this.text = text;
//		this.activityTeacher = activityTeacher;
//		this.place = place;
//		this.teacherPhone = teacherPhone;
//		this.applicantNumber = applicantNumber;
//		this.applicantNumberFull = applicantNumberFull;
//		this.applicantStatus = applicantStatus;
//		this.applicantCreateDate = applicantCreateDate;
//		this.applicantDeadDate = applicantDeadDate;
//	}
//
//	public Activity(Integer p_Id, Integer managerId, String managerName, String title, String text,
//			String activityTeacher, String place, String teacherPhone, Integer applicantNumber,
//			Integer applicantNumberFull, String applicantStatus, Date applicantCreateDate, Date applicantDeadDate
//			) {
//		super();
//		P_Id = p_Id;
//		this.managerId = managerId;
//		this.managerName = managerName;
//		this.title = title;
//		this.text = text;
//		this.activityTeacher = activityTeacher;
//		this.place = place;
//		this.teacherPhone = teacherPhone;
//		this.applicantNumber = applicantNumber;
//		this.applicantNumberFull = applicantNumberFull;
//		this.applicantStatus = applicantStatus;
//		this.applicantCreateDate = applicantCreateDate;
//		this.applicantDeadDate = applicantDeadDate;
//		
//	}

	

}
