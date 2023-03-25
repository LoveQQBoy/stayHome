package com.ispan.stayhome.CPC_feedback.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ispan.stayhome.CPC_feedback.model.Feedback;
import com.ispan.stayhome.CPC_feedback.model.Response;
import com.ispan.stayhome.CPC_feedback.service.FeedbackService;
import com.ispan.stayhome.CPC_feedback.service.ResponseService;
import com.ispan.stayhome.CPC_login.model.HouseholdData;
import com.ispan.stayhome.CPC_login.service.HouseholdService;

@Controller
public class ResponseController {

	@Autowired
	private ResponseService responseService;
	
	@Autowired
	private FeedbackService feedbackService;
	
	@Autowired
	private HouseholdService householdService;
	
	@PostMapping("/response/add")
	public String responseadd(@ModelAttribute("response") Response response,@RequestParam Integer feedbackId,HttpSession session) {
		
		Integer memberId=(Integer)session.getAttribute("pid");
		HouseholdData householdData = householdService.findById(memberId);
		Feedback feedbackid = feedbackService.feedbackfinById(feedbackId);
		Integer feedbackId2 = feedbackid.getFeedbackId();
		
		response.setFeedback(feedbackid);
		response.setHouseholdData(householdData);
		responseService.responseadd(response);
		
		return "redirect:/feedback/replypage/"+feedbackId2;
	}
	
	@ResponseBody
	@GetMapping("/response/findOneResponse")
	public Integer findOneResponse(@RequestParam Integer feedbackId) {
		return responseService.getOneResponse(feedbackId);
	}
}
