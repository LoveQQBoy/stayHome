package com.ispan.stayhome.TLK_announcement.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ispan.stayhome.CPC_login.model.HouseholdData;
import com.ispan.stayhome.CPC_login.service.HouseholdService;
import com.ispan.stayhome.TLK_announcement.model.Announcement;
import com.ispan.stayhome.TLK_announcement.model.Message;
import com.ispan.stayhome.TLK_announcement.service.AnnouncementService;
import com.ispan.stayhome.TLK_announcement.service.MessageService;

@Controller
public class MessageController {
	
	@Autowired
	private MessageService messageService;
	
	@Autowired
	private HouseholdService householdService;
	
	@Autowired
	private AnnouncementService announcementService;

	@PostMapping("message")
	public String messageSave(@RequestParam String messageResponse,
			@RequestParam String messageDate,@RequestParam Integer responseId,
			HttpSession session) throws ParseException {
		
		Integer memberId=(Integer)session.getAttribute("pid");
		HouseholdData householdData=householdService.findById(memberId);
		Announcement announcement=announcementService.announcementInformation(responseId);
		///////////////轉換成String///////////////
		String announcementIDString=responseId.toString();
		////////////////找時間////////////////
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date messageDateFormat=format.parse(messageDate);
		Message message=new Message(messageResponse,householdData,messageDateFormat,announcement);
		messageService.messageSave(message);
		return "redirect:/announcement/announcementRead/"+announcementIDString;
	}
	

}
