package com.ispan.stayhome.CYS_repair.model;



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
import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "RepairRequite")
public class RepairRequite {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Repair_Id")
	private Integer id;
	
	@Temporal(TemporalType.TIMESTAMP)   //與放進資料庫的型別有關
	@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
//	@JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss", timezone = "GMT+8")
	@Column(name = "Date")
	private Date date;
	
//	@Pattern(regexp = "\\s+", message = "不可空格")
//	@Email(message = "帳號必須是Email 格式")
	@Column(name = "Description", length = 100)
	private String description;
	
	@Column(name = "Repair_Photo")
	private byte[] repairPhoto;
	
	@Transient
	MultipartFile m_repairPhoto;
	
	@Column(name = "Reply_State", length = 1)
	private String replyState;
	
	@Column(name = "Reply", length = 100)
	private String reply;
		
	@Temporal(TemporalType.TIMESTAMP)  
	@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
	@Column(name = "Reply_Time")
	private Date replyTime;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "FK_Resident_Id")
	private HouseholdData resident;
	
	@PrePersist
	public void onCreate() {
		if(date == null) {
			date= new Date();
		}
	}	
	
	public RepairRequite() {
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


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public byte[] getRepairPhoto() {
		return repairPhoto;
	}


	public void setRepairPhoto(byte[] repairPhoto) {
		this.repairPhoto = repairPhoto;
	}
	

	public MultipartFile getM_repairPhoto() {
		return m_repairPhoto;
	}

	
	public void setM_repairPhoto(MultipartFile m_repairPhoto) {
		this.m_repairPhoto = m_repairPhoto;
	}
	

	public String getReplyState() {
		return replyState;
	}


	public void setReplyState(String replyState) {
		this.replyState = replyState;
	}


	public String getReply() {
		return reply;
	}


	public void setReply(String reply) {
		this.reply = reply;
	}


	public HouseholdData getResident() {
		return resident;
	}


	public void setResident(HouseholdData resident) {
		this.resident = resident;
	}

	public Date getReplyTime() {
		return replyTime;
	}

	public void setReplyTime(Date replyTime) {
		this.replyTime = replyTime;
	}

	
	
	

}
