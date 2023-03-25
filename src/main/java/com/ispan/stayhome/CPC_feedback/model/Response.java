package com.ispan.stayhome.CPC_feedback.model;

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
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ispan.stayhome.CPC_login.model.HouseholdData;

@Entity
@Table(name = "Response")
public class Response {
	
	@Id //設定主鍵
	@GeneratedValue(strategy = GenerationType.IDENTITY) //自動生成欄位
	@Column(name="Response_Id") //SQL欄位名稱
	private Integer responseId;
	
	@Temporal(TemporalType.TIMESTAMP) //DATE（日期）、TIME（时间）、TIMESTAMP（日期和时间）
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")//可以在JAVA 環境設定日期格式，但HTML無法
	@JsonFormat(pattern ="yyyy-MM-dd HH:mm:ss EEEE",timezone = "GMT+8" )
	@Column(name="Response_Date")
	private Date responseDate;
	
	@Column(name="Response_Content") //SQL欄位名稱
	private String responseContent;
	
	@JsonBackReference //當讀到JsonBackReference 就不會序列化 由另外一邊進行
	@JoinColumn(name = "House_Id") //該table的對應 主鍵table 的SQL外鍵名稱 //@JoinColumn 再多方  ，mappedBy 在一方，兩個是互斥的
	@ManyToOne
	private HouseholdData householdData;
	
	@JsonBackReference //當讀到JsonBackReference 就不會序列化 由另外一邊進行
	@JoinColumn(name = "FK_Feedback_Id", nullable = true ) //該table的對應 主鍵table 的SQL外鍵名稱  nullable = true 可以為NULL  ，foreignKey = 可以幫兩個table之間的外鍵名稱 
	@ManyToOne
	private Feedback feedback;
	
	@PrePersist // 新增之前要做的事情
	public void onCreate() {
		if(responseDate ==null) {
			responseDate =new Date();
		}
	}
	
	//只要 entity 要有預設建構子
	public Response() {
		
	}



	public Integer getResponseId() {
		return responseId;
	}



	public void setResponseId(Integer responseId) {
		this.responseId = responseId;
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



	public Feedback getFeedback() {
		return feedback;
	}



	public void setFeedback(Feedback feedback) {
		this.feedback = feedback;
	}

}
