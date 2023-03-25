package com.ispan.stayhome.CPC_login.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ispan.stayhome.CPC_feedback.model.Feedback;
import com.ispan.stayhome.CPC_feedback.model.Response;
import com.ispan.stayhome.TLK_activity.model.Activity;
import com.ispan.stayhome.TLK_activity.model.Applicant;

@Entity
@Table(name="HouseholdData") //設定資料夾名稱
public class HouseholdData {
	
	@Id  //設定主鍵
	@GeneratedValue(strategy = GenerationType.IDENTITY) //自動生成欄位
	@Column(name="P_Id") //欄位名稱
	private Integer id;
	
	//@NotBlank: 只应用于char值可读序列，即只用于String，且不能为null
	//@NotEmpty：不能为null，而且长度必须大于0(" “,” ")，一般用在集合类上面
	
	@NotEmpty(message = "姓名必須填寫")
	@Column(name="Name")
	private String name;
	
	@NotBlank(message = "電話必須填寫")
	@Size(min = 7,max = 20, message = "電話最少7碼，最多20碼")
	@Column(name="Phone")
	private String phone;
	
	@NotBlank(message = "密碼必須填寫")
    @Size(min = 6,max = 20, message = "密碼最少6碼，最多20碼")
	@Column(name="Password")
	private String password;
	
	@Temporal(TemporalType.DATE) //保存SQL的DATE類型，將日期轉為 yyyy/MM/dd 格式
	@DateTimeFormat(pattern = "yyyy-MM-dd")//可以在JAVA 環境設定日期格式，但頁面無法
	@JsonFormat(pattern ="yyyy-MM-dd",timezone = "GMT+8" )
	@Column(name="Birthday")
	private Date birthday;
	
	@NotBlank(message = "Email 必須填寫")
    @Size(max = 50, message = "Email 最多50字")
    @Email(message = "Email必須符合格式")
	@Column(name="Email")
	private String email;
	
	@NotEmpty(message = "地址必須填寫")
	@Column(name="Address")
	private String address;
	
	@Column(name="Manager_Permission")
	private Integer managerPermission;
	
	//PrePersist新增時 會作動以下事情
	@PrePersist
	public void onCreate() {
		if(managerPermission == null) {
			managerPermission=0;
		}	
	}
	
	/*@OneToMany 註解表示這是一個一對多的關聯映射，即一個戶籍資料對應多個家庭成員。

		1.cascade 屬性表示對該關聯的操作應當具有的層級。
		在本例中，CascadeType.ALL 表示對戶籍資料的任何操作都會自動套用到其所有的家庭成員上，包括新增、修改、刪除等操作。
		
		2.mappedBy 屬性表示這個關聯映射的反向關聯屬性，即在對方實體中對應這個關聯的屬性名稱。在本例中，"householdData"
		 是 FamilyMember 實體中對應戶籍資料的屬性名稱，它會被用來關聯 FamilyMember 和 HouseholdData 實體。
		
		3.orphanRemoval 屬性表示當一個家庭成員被移除時，是否同時也要移除對應的戶籍資料。在本例中，
		orphanRemoval 被設置為 true，這意味著如果從 FamilyMember 實體中移除一個成員，
		同時也會從 HouseholdData 實體中移除對應的戶籍資料。*/
	//JsonManagedReference 當有序列化的情況 要有一方控管 一對多的情況都是由 一方管理
	//orphanRemoval = true 當List<Feedback>把其中一筆資料刪除，會跟HouseholdData斷掉
	@JsonManagedReference
	@OneToMany(cascade = CascadeType.ALL,mappedBy ="householdData",orphanRemoval = true)
	private List<Feedback> feedback = new ArrayList<>();
	
	@JsonManagedReference
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "feedback",orphanRemoval = true)
	private List<Response> response = new ArrayList<>();
	
	@JsonManagedReference
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "householdData",orphanRemoval = true)
	private List<Activity> activity = new ArrayList<>();


	@OneToMany(cascade = CascadeType.ALL,mappedBy = "householdData",orphanRemoval = true)
	@JsonIgnore
	private List<Applicant> applicant =new ArrayList<>();

	@JsonBackReference //當讀到JsonBackReference 就不會序列化 由另外一邊進行
	@JoinColumn(name = "FK_House_Type_Id", nullable = true ) //該table的對應 主鍵table 的SQL外鍵名稱  nullable = true 可以為NULL  ，foreignKey = 可以幫兩個table之間的外鍵名稱 
	@ManyToOne(cascade = CascadeType.ALL)
	private HouseType houseType;
	
	
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




	public List<Feedback> getFeedback() {
		return feedback;
	}




	public void setFeedback(List<Feedback> feedback) {
		this.feedback = feedback;
	}

	public List<Response> getResponse() {
		return response;
	}

	public void setResponse(List<Response> response) {
		this.response = response;
	}

	public HouseType getHouseType() {
		return houseType;
	}

	public void setHouseType(HouseType houseType) {
		this.houseType = houseType;
	}



	
}