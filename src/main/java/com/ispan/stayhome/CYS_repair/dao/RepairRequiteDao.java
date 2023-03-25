package com.ispan.stayhome.CYS_repair.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ispan.stayhome.CYS_repair.model.RepairRequite;

public interface RepairRequiteDao extends JpaRepository<RepairRequite, Integer>{
			
	@Query(value=" select * from RepairRequite where [FK_Resident_Id] = :r and CAST([Date] AS DATE) >= :d"
			+ " and [Reply_State] like '%' + :rs  ", nativeQuery = true)
	public Page<RepairRequite> residentListByPage(@Param("r") Integer residentId, 
			@Param("d") String date, @Param("rs") String replyState, Pageable pageable);

	
	@Query(value=" select * from RepairRequite "
			+ " where FK_Resident_Id in (select P_Id from HouseholdData where "
			+ " name like '%' + :n + '%' and phone like '%' + :ph + '%') "
			+ " and CAST([Date] AS DATE) >= :d and [Reply_State] like '%' + :rs + '%' ", nativeQuery = true)
	public Page<RepairRequite> backListByPage(@Param("d") String date, @Param("rs") String replyState, 
			@Param("n") String name, @Param("ph") String phone, Pageable pageable);
	
	
//	@Transactional
//	@Modifying
//	@Query(value="UPDATE RepairRequite SET Description = :d where Repair_Id = :r", nativeQuery = true)
//	public void updateDescriptionByRepairId(@Param("d")String description, @Param("r")Integer repairId);

	
//	@Query(value="select COUNT(*) from RepairRequite where FK_resident_id = :r", nativeQuery = true)
//	public Integer totalRecordByResidentId(@Param("r") Integer residentId);	
	
}
