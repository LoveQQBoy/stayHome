package com.ispan.stayhome.TLK_activity.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ispan.stayhome.CPC_login.model.HouseholdData;
import com.ispan.stayhome.TLK_activity.model.Applicant;

public interface ApplicantDao extends JpaRepository<Applicant, Integer>, JpaSpecificationExecutor<Applicant> {
	
	@Query(value="SELECT COUNT (*) FROM Applicant WHERE applicantStatus = '已報名' and activity.P_Id = :pid")
	public Long applicantNumber(@Param(value="pid") Integer pid);
	
	@Query(value="SELECT COUNT (*) FROM Applicant WHERE activity.P_Id = :pid")
	public Long applicantNumberAll(@Param(value="pid") Integer pid);
	
	@Query(value="SELECT CASE WHEN EXISTS (SELECT a.applicantStatus FROM Applicant a WHERE a.applicantStatus ='已報名' AND a.activity.P_Id =:activityId AND a.householdData.id = :householdDataId) then true else false end from Applicant ")
	public String applicantStatus(@Param(value="activityId") Integer activityId,@Param(value="householdDataId") Integer householdDataId);
	
	@Query(value="SELECT a.*, b.Title "
			+ "FROM Applicant as a "
			+ "INNER JOIN Activity as b ON b.P_Id = a.Applicant_Id "
			+ "WHERE a.Householder_Id = :pid "
			+"order by Registration_Date DESC",nativeQuery = true)
	public List<Object> applicantInnerJoinActivity(@Param(value="pid") Integer pid);
	
	@Modifying
	@Query(value="UPDATE Applicant a set applicantStatus = '已取消' where a.activity.P_Id = :pid and a.householdData.id = :memberId")
	public void applicantCancleActivity(@Param(value="pid")Integer pid,@Param(value="memberId") Integer memberId);
	
	@Modifying
	@Query(value=" delete a from Applicant  a "
			+ "  inner join Activity  b on a.Applicant_Id =b.P_Id "
			+ "  where a.Applicant_Id = :pid",nativeQuery = true)
	public void applicantDeleteActivity(@Param(value="pid")Integer pid);
	
	@Query(value="select * from Applicant where Householder_Id = :pid",nativeQuery =true)
	public Page<Applicant> findPageByPersonal(@Param(value="pid") Integer pid,Pageable pageable);
	
	@Query(value="select * from Applicant where Applicant_Id = :pid",nativeQuery =true)
	public Page<Applicant> findPageByActivity(@Param(value="pid") Integer pid,Pageable pageable);
	
	
}
