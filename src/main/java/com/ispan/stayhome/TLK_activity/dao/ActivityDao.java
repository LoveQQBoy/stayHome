package com.ispan.stayhome.TLK_activity.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ispan.stayhome.TLK_activity.model.Activity;

public interface ActivityDao extends JpaRepository<Activity, Integer> {
	
	@Query(value="Select applicantNumberFull FROM Activity WHERE P_Id = :pid")
	public Long findApplicantNumberFull(@Param(value="pid") Integer pid);
	
	@Modifying
	@Query(value="UPDATE Activity SET applicantNumber = :applicantNumber WHERE P_Id = :pid")
	public void updateApplicantNnmber(@Param(value="pid")Integer pid,@Param(value="applicantNumber") Integer applicantNumber);
	
	@Query(value="select count(*) from Activity",nativeQuery = true)
	public Integer activityNumber ();
	
	@Modifying
	@Query(value="update Activity set applicantStatus = '已額滿' where P_Id = :pid")
	public void updateActivityStatus (@Param(value="pid")Integer pid);
	
	@Modifying
	@Query(value="update Activity set applicantStatus = '未額滿' where P_Id = :pid")
	public void activityCancleStatus(@Param(value="pid")Integer pid);
	
	@Query(value="SELECT CASE WHEN EXISTS (SELECT applicantStatus FROM Activity  WHERE applicantStatus ='已額滿' AND P_Id=:activityId) then true else false end from Applicant ")
	public String activityApplicantStatusQuery(@Param(value="activityId") Integer activityId);
	
	@Query(value="select P_Id,applicantDeadDate from Activity")
	public List<Object[]> findIdAndApplicantStatus();
	
	@Modifying
	@Query(value="update Activity set applicantStatus = '已截止' where P_Id = :pid")
	public void changeStatusByDate(@Param(value="pid")Integer pid);
	
	@Query(value="select Count from Activity  where P_Id=:activityId", nativeQuery=true)
	public Integer countSearch(@Param(value="activityId")Integer activityId);
	
	@Query(value="SELECT *\r\n"
			+ "FROM Activity\r\n"
			+ "WHERE Title LIKE CONCAT('%',:Title,'%') and ( Applicant_Dead_Date BETWEEN \r\n"
			+ "            (CASE WHEN :startTime = '' THEN '1900-01-01' ELSE :startTime END) \r\n"
			+ "            AND \r\n"
			+ "            (CASE WHEN :endTime = '' THEN '9999-12-31' ELSE :endTime END)) \r\n"
			+ "and Applicant_Status LIKE CONCAT('%',:status,'%')",nativeQuery=true)
	public Page<Activity> searchPage(@Param(value="Title") String title,@Param(value="startTime")String startTime,@Param(value="endTime")String endTime,@Param(value="status")String status,Pageable pageable);
	
	@Query(value="select Applicant_Status from Activity where P_Id = :pid",nativeQuery=true)
	public Optional<String> searchActivityStatus(@Param(value="pid")Integer activityId);
}
