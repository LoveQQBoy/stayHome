package com.ispan.stayhome.LSF_manageCost.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ispan.stayhome.LSF_manageCost.model.ManageCostList;

public interface ManageCostListDao extends JpaRepository<ManageCostList, Integer> {

	@Query(value="SELECT TOP (1) * FROM ManageCostList ORDER BY Id DESC",nativeQuery = true)
	public ManageCostList findTheLatestManageCostList();
}
