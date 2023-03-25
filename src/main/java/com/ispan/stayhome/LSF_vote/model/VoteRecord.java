package com.ispan.stayhome.LSF_vote.model;

import java.util.Date;

import javax.persistence.CascadeType;
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

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "VoteRecord")
public class VoteRecord {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "VR_Id")
	private Integer id;
	
	@Column(name = "Choose")
	private Integer choose;
	
	@Column(name = "VR_Date")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss", timezone = "GMT+8")
	private Date voteDate;
	
	@ManyToOne
	@JoinColumn(name="VR_Vote_Id")
	private VoteActivity voteActivity;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="VR_User_Id")
	private VoteHousehold voteHousehold;
	
	public VoteRecord() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getChoose() {
		return choose;
	}

	public void setChoose(Integer chose) {
		this.choose = chose;
	}

	public Date getVoteDate() {
		return voteDate;
	}

	public void setVoteDate(Date voteDate) {
		this.voteDate = voteDate;
	}

	public VoteActivity getVoteActivity() {
		return voteActivity;
	}
	
	public void setVoteActivity(VoteActivity voteActivity) {
		this.voteActivity = voteActivity;
	}

	public VoteHousehold getVoteHousehold() {
		return voteHousehold;
	}

	public void setVoteHousehold(VoteHousehold voteHousehold) {
		this.voteHousehold = voteHousehold;
	}
}
