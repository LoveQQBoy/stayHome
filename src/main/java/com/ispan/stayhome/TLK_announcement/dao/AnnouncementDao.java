package com.ispan.stayhome.TLK_announcement.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ispan.stayhome.CPC_login.model.HouseholdData;
import com.ispan.stayhome.TLK_announcement.model.Announcement;

public interface AnnouncementDao extends JpaRepository<Announcement, Integer> {
	
	@Query(value="select count(*) from Announcement" ,nativeQuery = true)
	public Integer FindAnnouncementNumber();
	
	@Query(value = "from HouseholdData")
	public List<HouseholdData> findUserEmail();
	
	@Query(value = "select a.pictureName From Announcement a where a.pictureName = :pictureName")
	public List<String> checkPictureNameExist(@Param(value="pictureName") String pictureName);
	
	@Query(value = "select a.pictureName From Announcement a where a.P_Id = :announcementId")
	public List<String> searchPictureName(@Param(value="announcementId") Integer announcementId);
}
