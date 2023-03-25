package com.ispan.stayhome.CPC_login.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ispan.stayhome.CPC_login.model.HouseholdData;

//JpaRepository<HouseholdData, Integer> 框格裡面第一個是物件的型別、第二個是物件的id資料型別
//JpaRepository增删改查
public interface HouseholdDao extends JpaRepository<HouseholdData, Integer> {

	//Query HQL+from(對類別)  SQL+select(對資料庫) 要加nativeQuery = true
	@Query(value=" from HouseholdData where email = :email and password = :pass")
	 public HouseholdData findHouseholdDataByEmailAndPassword(
	   @Param(value="email") String email,@Param(value="pass") String password);
	
	@Query(value=" from HouseholdData where email = :email")
	 public HouseholdData findByEmail(
	   @Param(value="email") String email);
	
	@Query(value=" from HouseholdData where phone = :phone")
	 public HouseholdData findByPhone(
	   @Param(value="phone") String phone);

	@Query(value=" select * from HouseholdData where P_Id =:householdId and FK_House_Type_Id is not null",nativeQuery=true)
	public Optional<HouseholdData> checkHouseType(@Param(value="householdId")Integer householdId);

}