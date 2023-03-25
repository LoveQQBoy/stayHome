package com.ispan.stayhome.LSF_manageCost.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "HouseType")
public class ManageHouseType {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //自動生成欄位
	@Column(name = "House_Type_Id")
	private Integer typeId;

	@Column(name = "House_Size")
	private Integer houseSize;
	
	@Column(name = "House_Floor")
	private Integer houseFloor;
	
	@Column(name = "Parking_Space")
	private Integer parkingSpace;
	
	@JsonManagedReference //JsonManagedReference 當有序列化的情況 要有一方控管 一對多的情況都是由 一方管理
	@OneToMany(cascade = CascadeType.ALL,mappedBy ="houseType",orphanRemoval = false) //Cascade detach operation，级联脱管/游离操作。如果你要删除一个实体，但是它有外键无法删除，你就需要这个级联权限了。它会撤销所有相关的外键关联。
	private List<ManageHousehold> householdData = new ArrayList<>();
	
	public ManageHouseType() {
		
	}

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public Integer getHouseSize() {
		return houseSize;
	}

	public void setHouseSize(Integer houseSize) {
		this.houseSize = houseSize;
	}

	public Integer getHouseFloor() {
		return houseFloor;
	}

	public void setHouseFloor(Integer houseFloor) {
		this.houseFloor = houseFloor;
	}

	public Integer getParkingSpace() {
		return parkingSpace;
	}

	public void setParkingSpace(Integer parkingSpace) {
		this.parkingSpace = parkingSpace;
	}

	public List<ManageHousehold> getHouseholdData() {
		return householdData;
	}

	public void setHouseholdData(List<ManageHousehold> householdData) {
		this.householdData = householdData;
	}

}
