package com.ispan.stayhome.HYL_collectParcel.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ispan.stayhome.HYL_collectParcel.model.PackHouseholdData;

//JpaRepository<HouseholdData, Integer> 框格裡面第一個是物件的型別、第二個是物件的id資料型別
//JpaRepository增删改查
public interface PackHouseholdDao extends JpaRepository<PackHouseholdData, Integer> {

	//Query HQL+from(對類別)  SQL+select(對資料庫) 要加nativeQuery = true
	@Query(value=" from HouseholdData where email = :email and password = :pass")
	 public PackHouseholdData findHouseholdDataByEmailAndPassword(
	   @Param(value="email") String email,@Param(value="pass") String password);
	
	@Query(value=" from HouseholdData where email = :email")
	 public PackHouseholdData findByEmail(
	   @Param(value="email") String email);
	
	@Query(value=" from HouseholdData where phone = :phone")
	 public PackHouseholdData findByPhone(
	   @Param(value="phone") String phone);
	
	@Query(value="select * from HouseholdData where Name = :userName",nativeQuery = true)
	public PackHouseholdData findByName(@Param(value="userName") String userName);


}