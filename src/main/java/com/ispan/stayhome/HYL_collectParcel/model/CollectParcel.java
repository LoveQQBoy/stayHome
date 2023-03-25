package com.ispan.stayhome.HYL_collectParcel.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



@Entity
@Table(name = "CollectParcel")
public class CollectParcel {

	// 宣告對應欄位的變數

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "P_Id")
	private int id;

	@Column(name = "State")
	private String state;

	@Column(name = "Parcel_Name")
	private String parcelname;

	@Column(name = "Received_Date")
	private String receiveddate;

	@Column(name = "Sender_Name")
	private String sendername;

	@Column(name = "Description")
	private String description;
	
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="Recipient_Id")
	private PackHouseholdData packHouseholdData;
	// constructor

	public CollectParcel() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getParcelname() {
		return parcelname;
	}

	public void setParcelname(String parcelname) {
		this.parcelname = parcelname;
	}

	public PackHouseholdData getPackHouseholdData() {
		return packHouseholdData;
	}

	public void setPackHouseholdData(PackHouseholdData packHouseholdData) {
		this.packHouseholdData = packHouseholdData;
	}

	public String getReceiveddate() {
		return receiveddate;
	}

	public void setReceiveddate(String receiveddate) {
		this.receiveddate = receiveddate;
	}

	public String getSendername() {
		return sendername;
	}

	public void setSendername(String sendername) {
		this.sendername = sendername;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	// getter 和 setter 方法

	
}