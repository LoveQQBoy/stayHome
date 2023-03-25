package com.ispan.stayhome.LSF_manageCost.model;

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

@Entity
@Table(name = "ManagementCost")
public class ManagementCost {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MC_Id")
	private Integer id;
	
	@Column(name = "Money")
	private Integer money;
	
	@Column(name = "Period")
	private String period;
	
	@Column(name = "Pay_Time")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
	private Date payTime;
	
	@ManyToOne
	@JoinColumn(name="MC_List_Id")
	private ManageCostList manageCostList;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="User_Id")
	private ManageHousehold manageHousehold;
	
	public ManagementCost() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getMoney() {
		return money;
	}

	public void setMoney(Integer money) {
		this.money = money;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	public ManageCostList getManageCostList() {
		return manageCostList;
	}

	public void setManageCostList(ManageCostList manageCostList) {
		this.manageCostList = manageCostList;
	}

	public ManageHousehold getManageHousehold() {
		return manageHousehold;
	}

	public void setManageHousehold(ManageHousehold manageHousehold) {
		this.manageHousehold = manageHousehold;
	}

}
