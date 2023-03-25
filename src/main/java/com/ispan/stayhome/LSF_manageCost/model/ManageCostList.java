package com.ispan.stayhome.LSF_manageCost.model;

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

@Entity
@Table(name="ManageCostList")
public class ManageCostList {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Id")
	private Integer id;
	
	@Column(name="Manage_Title")
	private String mcTitle;
	
	@Column(name="Manage_Year")
	private String mcYear;
	
	@Column(name="Manage_Month")
	private String mcMonth;
	
	@Column(name = "Create_Date")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
	private Date createDate;
	
	//狀態分三種:0未啟用、1啟用中
	@Column(name = "Manage_State")
	private Integer mcState;
	
	@Column(name="Square_Feet")
	private Integer squareFeet;
	
	@Column(name="Parking_Fee")
	private Integer parkingFee;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true,mappedBy = "manageCostList")
	private Set<ManagementCost> managementCosts = new HashSet<>();
	
	public ManageCostList() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMcTitle() {
		return mcTitle;
	}

	public void setMcTitle(String mcTitle) {
		this.mcTitle = mcTitle;
	}

	public String getMcYear() {
		return mcYear;
	}

	public void setMcYear(String mcYear) {
		this.mcYear = mcYear;
	}

	public String getMcMonth() {
		return mcMonth;
	}

	public void setMcMonth(String mcMonth) {
		this.mcMonth = mcMonth;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Integer getMcState() {
		return mcState;
	}

	public void setMcState(Integer mcState) {
		this.mcState = mcState;
	}

	public Integer getSquareFeet() {
		return squareFeet;
	}

	public void setSquareFeet(Integer squareFeet) {
		this.squareFeet = squareFeet;
	}

	public Integer getParkingFee() {
		return parkingFee;
	}

	public void setParkingFee(Integer parkingFee) {
		this.parkingFee = parkingFee;
	}

	public Set<ManagementCost> getManagementCosts() {
		return managementCosts;
	}

	public void setManagementCosts(Set<ManagementCost> managementCosts) {
		this.managementCosts = managementCosts;
	}

}
