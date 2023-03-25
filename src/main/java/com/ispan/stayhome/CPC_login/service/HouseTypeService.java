package com.ispan.stayhome.CPC_login.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ispan.stayhome.CPC_login.dao.HouseTypeDao;
import com.ispan.stayhome.CPC_login.model.HouseType;

@Service
@Transactional
public class HouseTypeService {
	
	@Autowired
	private HouseTypeDao houseTypeDao;
	
	//新增
	public HouseType add(HouseType houseType) {
		return houseTypeDao.save(houseType);
	}
	
	//id查詢
	public HouseType findById(Integer typeId) {
		Optional<HouseType> optional = houseTypeDao.findById(typeId);
		
		if(optional.isEmpty()) { //isEmpty 如果是空值
			return null;
		}
		return optional.get();
	}
	
	public List<HouseType> findAll(){
		return houseTypeDao.findAll();
	}
	
	//ID刪除
	public void deleteById(Integer typeId) {
		houseTypeDao.deleteById(typeId);
		
	}
	
	//更新
	public HouseType updateHouseType(HouseType houseType) {
		return houseTypeDao.save(houseType);
	}
	
	
	

}
