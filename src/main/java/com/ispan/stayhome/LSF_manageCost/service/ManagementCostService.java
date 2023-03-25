package com.ispan.stayhome.LSF_manageCost.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ispan.stayhome.LSF_manageCost.dao.ManageCostListDao;
import com.ispan.stayhome.LSF_manageCost.dao.ManageHouseholdDao;
import com.ispan.stayhome.LSF_manageCost.dao.ManagementCostDao;
import com.ispan.stayhome.LSF_manageCost.model.ManageCostList;
import com.ispan.stayhome.LSF_manageCost.model.ManageHousehold;
import com.ispan.stayhome.LSF_manageCost.model.ManagementCost;

@Service
@Transactional
public class ManagementCostService {
	
	@Autowired
	private ManageCostListDao manageCostListDao;
	
	@Autowired
	private ManagementCostDao managementCostDao;
	
	@Autowired
	private ManageHouseholdDao manageHouseholdDao;
	
	public List<ManagementCost> findAllData() {
		return managementCostDao.findAll();
	}

	public void insertOrUpdateManagementCostList(ManageCostList manageCostList) {
		manageCostListDao.save(manageCostList);
	}
	
	public void CreateManagementCostForAllHousehold() {
		ManageCostList manageCostList = manageCostListDao.findTheLatestManageCostList();
		List<ManageHousehold> households = manageHouseholdDao.findAll();
		
		for (ManageHousehold manageHousehold : households) {
			int mcMoney = calculateManagementCost(manageCostList,manageHousehold);
			ManagementCost managementCost = new ManagementCost();
			managementCost.setMoney(mcMoney);
			managementCost.setPeriod(manageCostList.getMcYear()+"/"+manageCostList.getMcMonth());
			managementCost.setManageCostList(manageCostList);
			managementCost.setManageHousehold(manageHousehold);
			managementCostDao.save(managementCost);
		}
	}
	
	public ManagementCost findById(Integer id) {
		Optional<ManagementCost> optional = managementCostDao.findById(id);
		
		if(optional.isEmpty()) {
			return null;
		}
		
		return optional.get();
	}
	
	public void deleteById(Integer id) {
		managementCostDao.deleteById(id);
	}
	
	public Page<ManageCostList> findAllManageCostListPage(Integer pageNumber){
		Pageable pgb = PageRequest.of(pageNumber-1, 5, Sort.Direction.ASC,"Id");
		
		Page<ManageCostList> page = manageCostListDao.findAll(pgb);
		
		return page;
	}
	
	public List<ManagementCost> findAllManageCostById(Integer manageCostId){
		return managementCostDao.findAllManageCostById(manageCostId);
	}
	
	public Page<ManagementCost> findUserManageCost(Integer pageNumber, Integer userId){
		Pageable pgb = PageRequest.of(pageNumber-1, 5, Sort.Direction.DESC,"MC_Id");
		
		Page<ManagementCost> page = managementCostDao.findUserManageCostById(userId,pgb);
		
		return page;
	}
	
	public ManageCostList findManageCostListById(Integer id) {
		Optional<ManageCostList> optional =manageCostListDao.findById(id);
		
		if(optional.isEmpty()) {
			return null;
		}
		
		return optional.get();
	}
	
	public boolean deleteListById(Integer id) {
		
		Optional<ManageCostList> optional = manageCostListDao.findById(id);
		ManageCostList manageCostList = optional.get();
		boolean nobodyHasPay = true;
		
		for (ManagementCost managementCost : manageCostList.getManagementCosts()) {
			if(managementCost.getPayTime()!=null) {
				nobodyHasPay = false;
				break;
			}
		}
		
		if(nobodyHasPay)
			manageCostListDao.deleteById(id);
		
		return nobodyHasPay;
	}
	
	public Integer calculateManagementCost(ManageCostList manageCostList, ManageHousehold manageHousehold) {
		int sqFeet = manageCostList.getSquareFeet();
		int pFee = manageCostList.getParkingFee();
		int hSize = manageHousehold.getHouseType().getHouseSize();
		int pSpace = manageHousehold.getHouseType().getParkingSpace();
		
		int totleManageCost = (sqFeet * hSize) + (pFee * pSpace);
		
		return totleManageCost;
	}
	
}
