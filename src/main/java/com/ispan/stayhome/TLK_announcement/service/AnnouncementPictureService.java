package com.ispan.stayhome.TLK_announcement.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ispan.stayhome.TLK_announcement.dao.AnnouncementPictureDao;
import com.ispan.stayhome.TLK_announcement.model.AnnouncementPicture;

@Service
public class AnnouncementPictureService {
	
	@Autowired
	private AnnouncementPictureDao announcementPictureDao;
	
	@Transactional
	public void save(AnnouncementPicture announcementPicture) {
		announcementPictureDao.save(announcementPicture);
	}
	
	@Transactional
	public void deleteAll(Integer announcementId) {
		announcementPictureDao.deleteReleatePicture(announcementId);
	}
	
	public List<String> searchReleatePicture(Integer announcementId) {
		List<String> searchPictureName = announcementPictureDao.searchReleatePicture(announcementId);
		if(searchPictureName.isEmpty()) {
			return null;
		}else {
			return searchPictureName;
		}
		
	}

}
