package com.ispan.stayhome.TLK_activity.controller;



import java.text.ParseException;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ispan.stayhome.CPC_login.model.HouseholdData;
import com.ispan.stayhome.CPC_login.service.HouseholdService;
import com.ispan.stayhome.TLK_activity.model.Activity;
import com.ispan.stayhome.TLK_activity.model.Applicant;
import com.ispan.stayhome.TLK_activity.service.ActivityService;
import com.ispan.stayhome.TLK_activity.service.ApplicantService;

@Controller
public class ApplicantController {
	
	@Autowired
	private ActivityService activityService;
	
	@Autowired
	private ApplicantService applicantService;
	
	@Autowired
	private HouseholdService householdService;
	
	
	@GetMapping("activity/applicantFindActivity")
	public String applicantFindActivity(@RequestParam(defaultValue = "1") Integer pageNumber,Model model,RedirectAttributes redirectAttributes,HttpServletRequest request) {
		
		//判斷是否有條件搜尋
		Object searchPage=model.getAttribute("searchPage");
		String searchPageNumberString=request.getParameter("searchPageNumber");
		//第一次條件搜尋不切頁
		if(searchPage != null) {
			model.addAttribute("searchPage", searchPage);
			return "activity/applicantFindActivity";
		}
		//判斷是否有條件搜尋
		//判斷是否是要切換成別頁
		if(searchPageNumberString!= null) {
			System.out.println("cccccccccccccccccccccccccccc");
			Integer searchPageNumber=Integer.parseInt(searchPageNumberString);
			String keyWord=request.getParameter("keyWord");
			String startTime=request.getParameter("startTime");
			String endTime=request.getParameter("endTime");
			String activityStatusString=request.getParameter("activityStatusString");
			model.addAttribute("keyWord", keyWord);
			model.addAttribute("startTime", startTime);
			model.addAttribute("endTime", endTime);
			model.addAttribute("activityStatusString", activityStatusString);
			Page<Activity> page=activityService.searchPage(searchPageNumber, keyWord, startTime, endTime, activityStatusString);
			model.addAttribute("searchPage", page);
			return "activity/applicantFindActivity";
		}
		
		Page<Activity> page = activityService.applicantFindTwentyPage(pageNumber);
		model.addAttribute("page", page);
		
		/////////////當點擊這個頁面會觸發自動檢查是否過期///////////
		try {
			activityService.updateApplicantStatusByTime();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "activity/applicantFindActivity";
		
	}
	
	@GetMapping("activity/applicantFindActivityInformation")
	public String applicantFindActivityInformation(@RequestParam Integer activityId, Model model, HttpSession session) {
		////////////點進活動頁面時會去抓之前的count並+1///////////////
		System.out.println("/////////////////////////////");
		Integer countNew=activityService.countNumber(activityId);
		if(countNew == null) {
			countNew = 1;
			Activity activity=activityService.activityFindById(activityId);
			activity.setCount(countNew);
			activityService.activityInsert(activity);
		}else {
			countNew=countNew+1;
			Activity activity=activityService.activityFindById(activityId);
			activity.setCount(countNew);
			activityService.activityInsert(activity);
		}
		System.out.println("/////////////////////////////");
		////////////點進活動頁面時會去抓之前的count並+1///////////////
		Activity information = activityService.activityFindById(activityId);
		model.addAttribute("activityInformation", information);
		return "activity/applicantFindActivityInformation";
	}
	
	@PostMapping("activity/applicantAddActivity")
	public String applicantAddActivity(@RequestParam Integer activityId,HttpSession session,RedirectAttributes redirectattributes) {
			//抓取活動報名資訊
			Activity activity=activityService.activityFindById(activityId);
			String applicantStatus="已報名";
			//抓取使用者資訊
			Integer householdId=(Integer)session.getAttribute("pid");
			
			HouseholdData householdDataInormation=householdService.findById(householdId);
			//報名成功將其新增近資料庫
			Applicant applicant = new Applicant(applicantStatus,householdDataInormation,activity);
			applicantService.applicantAddActivity(activityId,applicant,redirectattributes);
		return "redirect:/activity/applicantFindActivity";
	}
	

	
	@GetMapping("activity/applicantPersonal")
	public String applicantFindPage(@RequestParam(defaultValue="1") Integer pageNumber,HttpSession session,Model model,RedirectAttributes redirectattributes) {
		Integer memberId=(Integer)session.getAttribute("pid");	
		Page<Applicant> page=applicantService.applicantPageByPersonal(pageNumber,memberId);
		model.addAttribute("page",page);
		return "activity/applicantPersonal";
	}
	
	@PutMapping("activity/applicantCancleActivity")
	public String applicantCancleAvtivity(@RequestParam Integer activityId,RedirectAttributes redirectattributes,HttpSession session) {
		Integer memberId=(Integer)session.getAttribute("pid");
		System.out.println(memberId);
		applicantService.applicantCancleActivity(activityId,memberId,redirectattributes);
		String message="您已取消報名";
		redirectattributes.addFlashAttribute("message", message);
		return "redirect:/activity/applicantPersonal";
	}
	
	@ResponseBody
	@GetMapping("activity/applicantFindAllPage")
	public Page<Applicant> applicantFindActivityPage(@RequestParam(defaultValue="1") Integer pageNumber,@RequestParam Integer activityId,Model model) {
		Page<Applicant> page=applicantService.findPageByActivity(pageNumber, activityId);
		for(Applicant pageone : page) {
			System.out.println(pageone.getHouseholdData().getName());
		}
		return page;
	}
	
	@PutMapping("activity/applicantCancleByManager")
	public String applicantCancleByManager(@RequestParam Integer activityId,@RequestParam Integer memberId,RedirectAttributes redirectattributes) {
		applicantService.applicantCancleActivity(activityId,memberId,redirectattributes);
		String message="您已取消報名";
		redirectattributes.addFlashAttribute("message", message);
		return "redirect:/activity/Information?activityId=" + activityId ;
	}
	
	
	@PostMapping("activity/findPageByTimeAndString")
	public String applicantFindPageByTimeAndString(@RequestParam(defaultValue="1") Integer pageNumber,@RequestParam String keyWord,
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") String startTime,
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") String endTime,
			@RequestParam Optional<String> activityStatus,RedirectAttributes redirectAttributes) {
		///////// 因為radio若沒勾選傳回來會有null因此需要做資料處裡
		String activityStatusString;
		if(activityStatus.isEmpty()) {
			activityStatusString = "";
		}else {
			activityStatusString =activityStatus.get();
		}
		///////// 因為radio若沒勾選傳回來會有null因此需要做資料處裡
		
		//因為要保持轉到第二頁時仍然有搜尋的條件，所以條件也要一起轉發
		Page<Activity> page=activityService.searchPage(pageNumber, keyWord, startTime, endTime, activityStatusString);
		redirectAttributes.addFlashAttribute("searchPage", page);
		redirectAttributes.addFlashAttribute("keyWord", keyWord);
		redirectAttributes.addFlashAttribute("startTime", startTime);
		redirectAttributes.addFlashAttribute("endTime", endTime);
		redirectAttributes.addFlashAttribute("activityStatusString", activityStatusString);
		return "redirect:/activity/applicantFindActivity";
	}
	
}
