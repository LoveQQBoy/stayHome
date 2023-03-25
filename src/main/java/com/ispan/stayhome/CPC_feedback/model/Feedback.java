package com.ispan.stayhome.CPC_feedback.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ispan.stayhome.CPC_login.model.HouseholdData;

@Entity
@Table(name = "Feedback")
public class Feedback {
	
	@Id  //設定主鍵
	@GeneratedValue(strategy = GenerationType.IDENTITY) //自動生成欄位
	@Column(name="Feedback_Id") //SQL欄位名稱
	private Integer feedbackId;
	
	@NotEmpty(message = "主題必須填寫")
	@Column(name="Feedback_Name") //SQL欄位名稱
	private String feedbackName;
		
	@Temporal(TemporalType.TIMESTAMP) //DATE（日期）、TIME（时间）、TIMESTAMP（日期和时间）
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")//可以在JAVA 環境設定日期格式，但HTML無法
	@JsonFormat(pattern ="yyyy-MM-dd HH:mm:ss EEEE",timezone = "GMT+8" )
	@Column(name="Feedback_Date")
	private Date feedbackDate;
	
	@NotEmpty(message = "內容必須填寫")
	@Size(min = 6,max = 100, message = "最少6字，最多100字")
	@Column(name="Feedback_Content") //SQL欄位名稱
	private String feedbackContent;
	
	@Temporal(TemporalType.TIMESTAMP) //DATE（日期）、TIME（时间）、TIMESTAMP（日期和时间）
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")//可以在JAVA 環境設定日期格式，但HTML無法
	@JsonFormat(pattern ="yyyy-MM-dd HH:mm:ss EEEE",timezone = "GMT+8" )
	@Column(name="Response_Date")
	private Date responseDate;
	
	@Column(name="Response_Content") //SQL欄位名稱
	private String responseContent;

	@JsonBackReference //當讀到JsonBackReference 就不會序列化 由另外一邊進行
	@JoinColumn(name = "House_Id") //該table的對應 主鍵table 的SQL外鍵名稱
	@ManyToOne
	private HouseholdData householdData;
	

	@JsonManagedReference //JsonManagedReference 當有序列化的情況 要有一方控管 一對多的情況都是由 一方管理
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "feedback",orphanRemoval = true)
	private List<Response> response = new ArrayList<>();

	@PrePersist // 新增之前要做的事情
	public void onCreate() {
		if(feedbackDate ==null) {
			feedbackDate =new Date();
		}
	}
	
	
	public Integer getFeedbackId() {
		return feedbackId;
	}


	public void setFeedbackId(Integer feedbackId) {
		this.feedbackId = feedbackId;
	}

	public String getFeedbackName() {
		return feedbackName;
	}

	public void setFeedbackName(String feedbackName) {
		this.feedbackName = feedbackName;
	}


	public Date getFeedbackDate() {
		return feedbackDate;
	}

	public void setFeedbackDate(Date feedbackDate) {
		this.feedbackDate = feedbackDate;
	}

	public String getFeedbackContent() {
		return feedbackContent;
	}

	public void setFeedbackContent(String feedbackContent) {
		this.feedbackContent = feedbackContent;
	}

	public Date getResponseDate() {
		return responseDate;
	}

	public void setResponseDate(Date responseDate) {
		this.responseDate = responseDate;
	}

	public String getResponseContent() {
		return responseContent;
	}

	public void setResponseContent(String responseContent) {
		this.responseContent = responseContent;
	}

	public HouseholdData getHouseholdData() {
		return householdData;
	}

	public void setHouseholdData(HouseholdData householdData) {
		this.householdData = householdData;
	}

	public List<Response> getResponse() {
		return response;
	}


	public void setResponse(List<Response> response) {
		this.response = response;
	}


	public Feedback() {
		
	}
}
