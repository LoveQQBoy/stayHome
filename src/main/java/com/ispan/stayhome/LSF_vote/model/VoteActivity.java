package com.ispan.stayhome.LSF_vote.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
@Entity
@Table(name = "VoteActivity")
public class VoteActivity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Vote_Id")
	private Integer id;
	
	@Column(name = "Vote_Title")
	private String title;
	
	@Column(name = "Vote_Context")
	private String context;
	
	@Column(name = "Start_Date")
	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern = "yyyy/MM/dd", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private Date startDate;
	
	@Column(name = "End_Date")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	@JsonFormat(pattern = "yyyy/MM/dd", timezone = "GMT+8")
	private Date endDate;
	
	@Column(name = "Select_1")
	private String select1;
	
	@Column(name = "Select_2")
	private String select2;
	
	@Column(name = "Select_3")
	private String select3;
	
	@Column(name = "Select_4")
	private String select4;
	
	@Column(name = "Select_5")
	private String select5;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "voteActivity")
	private Set<VoteRecord> records = new HashSet<>();
	
	public VoteActivity() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startData) {
		this.startDate = startData;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getSelect1() {
		return select1;
	}

	public void setSelect1(String select1) {
		this.select1 = select1;
	}

	public String getSelect2() {
		return select2;
	}

	public void setSelect2(String select2) {
		this.select2 = select2;
	}

	public String getSelect3() {
		return select3;
	}

	public void setSelect3(String select3) {
		this.select3 = select3;
	}

	public String getSelect4() {
		return select4;
	}

	public void setSelect4(String select4) {
		this.select4 = select4;
	}

	public String getSelect5() {
		return select5;
	}

	public void setSelect5(String select5) {
		this.select5 = select5;
	}

	public Set<VoteRecord> getRecords() {
		return records;
	}

	public void setRecords(Set<VoteRecord> records) {
		this.records = records;
	}

}
