package com.ispan.stayhome.CPC_login.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ispan.stayhome.CPC_login.dao.HouseholdDao;
import com.ispan.stayhome.CPC_login.model.HouseholdData;

@Service
@Transactional
public class HouseholdService {
	
	@Autowired
	private HouseholdDao householdDao;
	
	
	///新增
	public HouseholdData add(HouseholdData hou) {
//		String name =hou.getName();
//		String phone = hou.getPhone();
//		String password = hou.getPassword();
//		String email = hou.getEmail();
//		
//		if(name ==null||name.isEmpty()) {
//			throw new RuntimeException("姓名輸入不得為空");
//		}
//		if(phone ==null||phone.isEmpty()) {
//			throw new RuntimeException("電話輸入不得為空");
//		}
//		if(password ==null||password.isEmpty()) {
//			throw new RuntimeException("密碼輸入不得為空");
//		}
//		if(email ==null||email.isEmpty()) {
//			throw new RuntimeException("E-MAIL輸入不得為空");
//		}
//		
		//判斷EMAIL 是否存在
		HouseholdData existingHouseByEmail  =householdDao.findByEmail(hou.getEmail());
		if(existingHouseByEmail !=null) {
			throw new RuntimeException("Email已存在");
		}
		//判斷Phone 是否存在
		HouseholdData existingHouseByPhone =householdDao.findByPhone(hou.getPhone());
		if(existingHouseByPhone != null) {
			 throw new RuntimeException("Phone已存在");
		}
		return householdDao.save(hou);
	}
	
	

	/*透過ID找到optional物件
	 *Optional<HouseholdData> optional = householdDao.findById(id)
	 *optional 說明:可能包含也可能不包含，通常用NULL替代，但語意不清，系統報錯時有多種可能
	 *造成DEBUG困難
	 *Optional物件的 isPresent(如果存在)、isEmpty(如果空值)方法，可以判對是否拿到該物件
	 *Optional物件的get方法  可以拿到該物件*/
	

		///id查詢
	public HouseholdData findById(Integer id) {
		Optional<HouseholdData> optional = householdDao.findById(id);
		
		if(optional.isEmpty()) {  //isEmpty 如果是空值
			return null;
		}
		return optional.get();		
	}
	
	
	
	
	
	public List<HouseholdData> findAll() {
		return householdDao.findAll();	
	}
	
	
	///id刪除
	public void deleteById(Integer id) {
		householdDao.deleteById(id);
	}
	
	
	//物件刪除
	public void deleteByEntity(HouseholdData hou) {
		householdDao.delete(hou);
	}
	
	///更新
	public HouseholdData updateHouseholdData(HouseholdData hou) {
		return householdDao.save(hou);
	}	
	
	
	
	//帳號密碼檢查
	public HouseholdData findByEmailAndPassword(String email,String password) {
		HouseholdData findByEmailAndPassword =householdDao.findHouseholdDataByEmailAndPassword(email, password);
		return findByEmailAndPassword;
	}
	

	public HouseholdData checkHouseType(Integer householdId) {
		Optional<HouseholdData> optional=householdDao.checkHouseType(householdId);
		if(!optional.isEmpty()) {
			return optional.get();
		}
		return null;
	}
}