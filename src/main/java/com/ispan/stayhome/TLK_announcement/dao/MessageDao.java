package com.ispan.stayhome.TLK_announcement.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ispan.stayhome.TLK_announcement.model.Announcement;
import com.ispan.stayhome.TLK_announcement.model.Message;

public interface MessageDao extends JpaRepository<Message, Integer> {
	
	@Query(value="SELECT COUNT (*) FROM Message WHERE announcement.P_Id = :pid")
	public Long messageNumberAll(@Param(value="pid") Integer pid);
	
	@Modifying
	@Query(value=" delete a from Message  a "
			+ "  inner join Announcement  b on a.Response_Id =b.P_Id "
			+ "  where a.Response_Id = :pid",nativeQuery = true)
	public void applicantDeleteActivity(@Param(value="pid")Integer pid);
	
	@Query(value="select * from Message where Response_Id = :announcementId",nativeQuery=true)
	public Page<Message> findPageByOne(@Param(value="announcementId") Integer announcementId,Pageable pageable);
	
//	@Query(value=" select  *  from Announcement \r\n"
//			+ "  where Title like CONCAT('%',:title,'%') and (Announcement_Date between \r\n"
//			+ "  (Case when :startTime = '' then '1900-01-01' else :startTime end) \r\n"
//			+ "  and\r\n"
//			+ "  (Case when :endTime = '' then '9999-01-01' else :endTime end))\r\n"
//			+ "  and\r\n"
//			+ "  P_Id in  (select Response_Id  from [dbo].[Message] \r\n"
//			+ "  group by Response_Id  \r\n"
//			+ "  having count(*) >=:lower )"
//			,nativeQuery=true)
//	public Page<Announcement> searchMessageCount(@Param(value="lower") Integer lower,@Param(value="startTime") String startTime,@Param(value="endTime") String endTime,@Param(value="title") String title,Pageable pageable);
//	
//	@Query(value=" select  *  from Announcement \r\n"
//			+ "  where Title like CONCAT('%',:title,'%') and (Announcement_Date between \r\n"
//			+ "  (Case when :startTime = '' then '1900-01-01' else :startTime end) \r\n"
//			+ "  and\r\n"
//			+ "  (Case when :endTime = '' then '9999-01-01' else :endTime end))\r\n"
//			,nativeQuery=true)
//	public Page<Announcement> searchMessageCountNoMessage(@Param(value="startTime") String startTime,@Param(value="endTime") String endTime,@Param(value="title") String title,Pageable pageable);
}
