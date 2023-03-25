package com.ispan.stayhome.TLK_activity.controller;

import java.text.ParseException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ispan.stayhome.CPC_feedback.service.ResponseService;
import com.ispan.stayhome.CPC_login.model.HouseholdData;
import com.ispan.stayhome.LSF_vote.model.VoteActivity;
import com.ispan.stayhome.LSF_vote.service.VoteActivityService;
import com.ispan.stayhome.TLK_activity.model.Activity;
import com.ispan.stayhome.TLK_activity.service.ActivityService;
import com.ispan.stayhome.TLK_announcement.model.Announcement;
import com.ispan.stayhome.TLK_announcement.service.AnnouncementService;

@Controller
public class HomeController {
	
	@Autowired
	private AnnouncementService announcementService;
	@Autowired
	private ActivityService activityService;
	@Autowired
	private VoteActivityService voteActivityService;
	@Autowired
	private ResponseService responseService;

	
	@GetMapping("/")
	public String home(Model model,HttpSession session) throws ParseException {
		
		//////////////////抓取會員//////////////////
		Integer memberId=(Integer)session.getAttribute("pid");
		if(memberId == null) {
			return "redirect:/household/loginpage";
		}
		//////////////////抓取會員//////////////////
		
		//////////////////公告//////////////////////
		int pageNumber = 1;
		Page<Announcement> pageAnnouncement=announcementService.announcementPage(pageNumber);
		model.addAttribute("pageAnnouncement", pageAnnouncement);
		//////////////////公告//////////////////////
		
		////////////////公告數量/////////////////////
		Integer announcementNumber=announcementService.announcementNumber();
		model.addAttribute("announcementNumber", announcementNumber);
		////////////////公告數量/////////////////////
		
		////////////////抓取投票資料/////////////////
		Page<VoteActivity> pageVoteActivity=voteActivityService.findPageForHomePage(pageNumber);		
		model.addAttribute("pageVoteActivity", pageVoteActivity);
		////////////////抓取投票資料/////////////////
		
		////////////////抓取投票數量/////////////////
		Integer  pageVoteNumber=voteActivityService.findTotalCountVoteActivity();		
		model.addAttribute("pageVoteNumber", pageVoteNumber);
		////////////////抓取投票數量/////////////////
		
		////////////////活動數量/////////////////////
		Integer activityNumber=activityService.activityNumber();
		model.addAttribute("activityNumber", activityNumber);
		////////////////活動數量/////////////////////
		
		////////////////熱門活動/////////////////////
		Page<Activity> pageActivity = activityService.activityFindPage(pageNumber);
		model.addAttribute("pageActivity", pageActivity);
		////////////////熱門活動/////////////////////
		
		////////////////留言板討論數量/////////////////////
		Integer totalResponse = responseService.getTotalResponse();
		model.addAttribute("totalResponse", totalResponse);
		////////////////留言板討論數量/////////////////////
		
		////////////////帳號登入資訊/////////////////////
		HouseholdData householdData = new HouseholdData();
		model.addAttribute("householdData",householdData);
		////////////////帳號登入資訊/////////////////////
		
		///////////////測試////////////////////////////
		activityService.updateApplicantStatusByTime();
		///////////////測試////////////////////////////
		return "Home";
	}
	
	
	@GetMapping("/manage")
	public String manage() {
		return "layout/sideNavbar";
	}
	
}
