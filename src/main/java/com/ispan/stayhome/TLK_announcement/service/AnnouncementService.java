package com.ispan.stayhome.TLK_announcement.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.ispan.stayhome.CPC_login.model.HouseholdData;
import com.ispan.stayhome.TLK_announcement.dao.AnnouncementDao;
import com.ispan.stayhome.TLK_announcement.dao.MessageDao;
import com.ispan.stayhome.TLK_announcement.model.Announcement;
import com.ispan.stayhome.TLK_announcement.model.Message;

@Service
public class AnnouncementService {

	@Autowired
	private AnnouncementDao announcementDao;
	
	@Autowired
	private MessageDao messageDao;

	@Autowired
	private JavaMailSender mailSender;

	@Transactional
	public Announcement announcementSave(Announcement announcement) {
		return announcementDao.save(announcement);
		
	}

	public Page<Announcement> announcementPage(Integer pageNumber) {
		Pageable pageable = PageRequest.of(pageNumber - 1, 10, Sort.Direction.DESC, "announcementDate");
		Page<Announcement> page = announcementDao.findAll(pageable);
		return page;
	}

	public Announcement announcementInformation(Integer announcementId) {
		Optional<Announcement> optional = announcementDao.findById(announcementId);
		if (optional.isEmpty()) {
			return null;
		}
		return optional.get();
	}

	@Transactional
	public void announcementDelete(Integer announcementId) {
		announcementDao.deleteById(announcementId);
	}

	public Integer announcementNumber() {
		return announcementDao.FindAnnouncementNumber();
	}

	public Announcement findById(Integer announcementId) {
		Optional<Announcement> optional = announcementDao.findById(announcementId);
		if (!optional.isEmpty()) {
			return optional.get();
		}
		return null;
	}

	public void sendEmail(Integer announcementId) {
		SimpleMailMessage message = new SimpleMailMessage();

		List<HouseholdData> householdData = announcementDao.findUserEmail();

		Announcement announcement = findById(announcementId);
		String announcementTitile = announcement.getTitle();
		String announcementText = announcement.getText();

	
			for (HouseholdData userEmailObject : householdData) {
				String email = userEmailObject.getEmail();
				System.out.println(email);
				message.setTo(email);
				message.setSubject(announcementTitile);
				message.setText(announcementText);
				mailSender.send(message);
			}
		
	}
	
	public String checkPictureNameExist(String pictureName) {
		List<String> checkPictureName = announcementDao.checkPictureNameExist(pictureName);
		if(checkPictureName.isEmpty()) {
			return null;
		}else {
			return checkPictureName.get(0);
		}
		
	}
	
	public String searchPictureName(Integer announcementId) {
		List<String> searchPictureName = announcementDao.searchPictureName(announcementId);
		if(searchPictureName.isEmpty()) {
			return null;
		}else {
			return searchPictureName.get(0);
		}
		
	}
	
//	public Page<Announcement> searchCondition(Integer pageNumber ,String startTime,String endTime,String title,Integer lower){
//		Pageable pageable = PageRequest.of(pageNumber-1, 5,Direction.DESC ,"Announcement_Date");
//		Page<Announcement> page=messageDao.searchMessageCount(lower,startTime,endTime,title,pageable);
//		return page;
//	}
//	
//	public Page<Announcement> searchMessageCountNoMessage(Integer pageNumber ,String startTime,String endTime,String title){
//		Pageable pageable = PageRequest.of(pageNumber-1, 5,Direction.DESC ,"Announcement_Date");
//		Page<Announcement> page=messageDao.searchMessageCountNoMessage(startTime,endTime,title,pageable);
//		return page;
//	}
	
}
