package com.ispan.stayhome.CPC_login.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ispan.stayhome.CPC_login.model.HouseType;

//JpaRepository<HouseType, Integer> 框格裡面第一個是物件的型別、第二個是物件的id資料型別
public interface HouseTypeDao extends JpaRepository<HouseType, Integer> {

}
