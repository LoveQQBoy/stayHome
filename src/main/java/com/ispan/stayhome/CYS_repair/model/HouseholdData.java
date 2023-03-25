package com.ispan.stayhome.CYS_repair.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.ispan.stayhome.CYS_public.model.PublicAppointment;


@Entity(name="RepairHouseholdData")
@Table(name = "HouseholdData")
public class HouseholdData {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "P_Id")
	private Integer id;
	
	@Column(name = "Name")
	private String name;
	
	@Column(name = "Phone")
	private String phone;
	
	@Column(name = "Password")
	private String password;
	
	@Column(name = "Birthday")
	private Date birthday;
	
	@Column(name = "Email")
	private String email;
	
	@Column(name = "Address")
	private String address;
	
	@Column(name = "Manager_Permission")
	private Integer managerPermission;
		
	@OneToMany(mappedBy = "resident") //orphanRemoval = true
	private List<RepairRequite> RepairRequites = new ArrayList<>();
	
	@OneToMany(mappedBy = "resident") //orphanRemoval = true
	private List<PublicAppointment> publicAppointment = new ArrayList<>();
	
	
	public HouseholdData() {
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


	public List<RepairRequite> getRepairRequites() {
		return RepairRequites;
	}


	public void setRepairRequites(List<RepairRequite> repairRequites) {
		RepairRequites = repairRequites;
	}

	
	public List<PublicAppointment> getPublicAppointment() {
		return publicAppointment;
	}


	public void setPublicAppointment(List<PublicAppointment> publicAppointment) {
		this.publicAppointment = publicAppointment;
	}
	

	
	
	
}
