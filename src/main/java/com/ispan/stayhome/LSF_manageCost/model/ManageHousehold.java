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

@Entity
@Table(name = "HouseholdData")
public class ManageHousehold {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "P_Id")
	private Integer id;
	
	@Column(name="Name")
	private String name;
	
	@Column(name="Phone")
	private String phone;
	
	@Column(name="Password")
	private String password;
	
	@Column(name="Birthday")
	private Date birthday;
	
	@Column(name="Email")
	private String email;
	
	@Column(name="Address")
	private String address;
	
	@Column(name="Manager_Permission")
	private Integer level;
	
	@JoinColumn(name = "FK_House_Type_Id", nullable = true ) //該table的對應 主鍵table 的SQL外鍵名稱  nullable = true 可以為NULL  ，foreignKey = 可以幫兩個table之間的外鍵名稱 
	@ManyToOne(cascade = CascadeType.ALL)
	private ManageHouseType houseType;
	
	public ManageHousehold() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public ManageHouseType getHouseType() {
		return houseType;
	}

	public void setHouseType(ManageHouseType houseType) {
		this.houseType = houseType;
	}


}
