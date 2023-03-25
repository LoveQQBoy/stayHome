package com.ispan.stayhome.TLK_announcement.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ispan.stayhome.TLK_announcement.model.AnnouncementPicture;

public interface AnnouncementPictureDao extends JpaRepository<AnnouncementPicture, Integer> {

	@Modifying
	@Query(value="delete AnnouncementPicture where FKAnnouncement = :FKAnnouncementId" ,nativeQuery=true)
	public void deleteReleatePicture(@Param(value="FKAnnouncementId") Integer FKAnnouncementId);
	
	
	@Query(value="select a.pictureNameMany from AnnouncementPicture a where a.announcement.P_Id = :FKAnnouncementId")
	public List<String> searchReleatePicture(@Param(value="FKAnnouncementId") Integer FKAnnouncementId);
	
}
