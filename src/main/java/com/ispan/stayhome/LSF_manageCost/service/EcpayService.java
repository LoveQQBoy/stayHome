package com.ispan.stayhome.LSF_manageCost.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ispan.stayhome.LSF_manageCost.dao.ManagementCostDao;
import com.ispan.stayhome.LSF_manageCost.model.ManagementCost;

@Service
@Transactional
public class EcpayService {

	@Autowired
	private ManagementCostDao managementCostDao;
	
	//付款成功更新付款時間
	public void createPayTimeWhenPaySuccess(Integer mcId) {
		
		Optional<ManagementCost> optional = managementCostDao.findById(mcId);
		ManagementCost managementCost = optional.get();
		
		Date date = new Date();
		managementCost.setPayTime(date);
		
		managementCostDao.save(managementCost);
	}	
	
}
