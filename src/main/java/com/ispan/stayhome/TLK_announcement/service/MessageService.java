package com.ispan.stayhome.TLK_announcement.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.ispan.stayhome.TLK_announcement.dao.MessageDao;
import com.ispan.stayhome.TLK_announcement.model.Message;

@Service
public class MessageService {

	@Autowired
	private MessageDao messageDao;
	
	@Transactional
	public void messageSave(Message message) {
		messageDao.save(message);
	}
	
	public Page<Message> messagePage(Integer pageNumber){
		Pageable pageable=PageRequest.of(pageNumber-1,5,Sort.Direction.DESC,"messageDate");
		Page<Message> page=messageDao.findAll(pageable);
		return page;
	}
	@Transactional
	public void messageDeleteAnnouncement(Integer announcementId) {
		messageDao.applicantDeleteActivity(announcementId);
	}
	public Integer messageNumberAll(Integer announcementId) {
		Integer messageNumber=messageDao.messageNumberAll(announcementId).intValue();
		return messageNumber;
	}
	
	public Page<Message> messagePageByOne(Integer pageNumber,Integer announcementId){
		Pageable pageable = PageRequest.of(pageNumber-1, 10,Direction.DESC,"Message_Date");
		Page<Message> page = messageDao.findPageByOne(announcementId, pageable);
		return page;
	}
	
	
}
