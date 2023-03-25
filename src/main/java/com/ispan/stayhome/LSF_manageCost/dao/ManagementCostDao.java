package com.ispan.stayhome.LSF_manageCost.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ispan.stayhome.LSF_manageCost.model.ManagementCost;

public interface ManagementCostDao extends JpaRepository<ManagementCost, Integer> {

	@Query(value="SELECT * FROM ManagementCost WHERE MC_List_Id = :id",nativeQuery = true)
	public List<ManagementCost> findAllManageCostById(@Param(value="id")Integer manageCostId);
	
	@Query(value="SELECT * FROM ManagementCost WHERE User_ID = :id",nativeQuery = true)
	public Page<ManagementCost> findUserManageCostById(@Param(value="id")Integer userId, Pageable pageable);
}
