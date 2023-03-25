package com.ispan.stayhome.CYS_repair.sevice;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ispan.stayhome.CYS_repair.dao.HouseholdDataDao;
import com.ispan.stayhome.CYS_repair.model.HouseholdData;


@Service
@Transactional
public class HouseholdDataService {

	@Autowired
	private HouseholdDataDao householdDataDao;
	
	
	public  HouseholdData findById(Integer id) {
		Optional<HouseholdData> optional= householdDataDao.findById(id);
		
//		if(optional.isEmpty()) {
//			return null;
//		}
		
		return optional.get();
	}
	
		

	
		
	
}