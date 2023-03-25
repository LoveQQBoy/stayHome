package com.ispan.stayhome.HYL_collectParcel.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="HouseholdData") //設定資料夾名稱
public class PackHouseholdData {
	
	@Id  //設定主鍵
	@GeneratedValue(strategy = GenerationType.IDENTITY) //自動生成欄位
	@Column(name="P_Id") //欄位名稱
	private Integer id;
	
	@Column(name="Name")
	private String name;
	
	@Column(name="Phone")
	private String phone;
	
	
	@Column(name="Password")
	private String password;
	
	@Temporal(TemporalType.DATE) //保存SQL的DATE類型，將日期轉為 yyyy/MM/dd 格式
	@DateTimeFormat(pattern = "yyyy/MM/dd")//可以在JAVA 環境設定日期格式，但頁面無法
	@JsonFormat(pattern ="yyyy/MM/dd",timezone = "GMT+8" )
	@Column(name="Birthday")
	private Date birthday;
	
	@Column(name="Email")
	private String email;
	
	@Column(name="Address")
	private String address;
	
	@Column(name="Manager_Permission")
	private Integer managerPermission;
	
	 
//	@OneToMany(cascade = CascadeType.ALL, mappedBy = "HouseholdData")
//	@Column(name = "Recipient_Id")
//	private Set<CollectParcel> collectParcel;
	
	//PrePersist新增時 會作動以下事情
	@PrePersist
	public void onCreate() {
		if(managerPermission == null) {
			managerPermission=0;
		}	
	}
	
	
	
	public PackHouseholdData() {
	
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



	public Integer getManagerPermission() {
		return managerPermission;
	}



	public void setManagerPermission(Integer managerPermission) {
		this.managerPermission = managerPermission;
	}



	
}