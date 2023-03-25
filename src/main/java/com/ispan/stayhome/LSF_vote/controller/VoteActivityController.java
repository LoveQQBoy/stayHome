package com.ispan.stayhome.LSF_vote.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ispan.stayhome.LSF_vote.model.VoteActivity;
import com.ispan.stayhome.LSF_vote.model.VoteRecord;
import com.ispan.stayhome.LSF_vote.service.VoteActivityService;

@Controller
public class VoteActivityController {

	@Autowired
	private VoteActivityService voteActivityService;
	
	//進入投票前台頁面
	@GetMapping("/vote/voteFrontPage")
	public String visitVoteFrontPage(
			@RequestParam(name="page",defaultValue = "1")Integer pageNumber,
			@RequestParam(name="useState", defaultValue="0")Integer useState,
			Model model,HttpSession session) {
		
		Page<VoteActivity> vaPage = voteActivityService.findAllForFrontPage(pageNumber, useState);
		model.addAttribute("vaPage",vaPage);
		
		boolean[] goVote = voteActivityService.findNeedToVote(session, vaPage);
		model.addAttribute("goVote",goVote);

		Date compareDate = new Date();
		model.addAttribute("compareDate", compareDate);
		
		if(useState!=null) {
			model.addAttribute("useState",useState);
		}
		
		return "vote/showVoteActivityFront";
	}
	
	//進入投票後台頁面
	@GetMapping("/vote/voteBackPage")
	public String visitVoteBackPage(@RequestParam(name="page",defaultValue="1")Integer pageNumber,Model model,
			@RequestParam(name="title", defaultValue="noEnter")String title,
			@RequestParam(name="useState", defaultValue="0")Integer useState) {
		Page<VoteActivity> vaPage=null;
		if( !title.equals("noEnter") || useState!=0 ) {
			vaPage = voteActivityService.findAllByCondition(pageNumber, title, useState);
			model.addAttribute("title",title);
			model.addAttribute("useState",useState);
		}else {
			vaPage = voteActivityService.findByBackPage(pageNumber);
		}
		model.addAttribute("vaPage",vaPage);
		
		Date compareDate = new Date();
		model.addAttribute("compareDate", compareDate);
		
		return "vote/showVoteActivityBack";
	}
	
	//進入新增投票頁面
	@GetMapping("/vote/createVotePage")
	public String visitCreateVotePage() {
		return "vote/createVoteActivity";
	}

	// 進入修改投票頁面
	@GetMapping("/vote/editVotePage")
	public String visiteditPage(@RequestParam Integer id, Model model) {
		VoteActivity voteActivity = voteActivityService.findById(id);

		int selectNum;

		if (voteActivity.getSelect5() != null)
			selectNum = 5;
		else if (voteActivity.getSelect4() != null)
			selectNum = 4;
		else if (voteActivity.getSelect3() != null)
			selectNum = 3;
		else
			selectNum = 2;

		model.addAttribute("voteActivity", voteActivity);
		model.addAttribute("selectNum", selectNum);

		return "vote/editVoteActivity";
	}

	// 新增投票活動
	@PostMapping("/vote/createVote")
	public String addVoteActivity(@RequestParam("title") String title, @RequestParam("context") String context,
			@RequestParam("startDate") String startD, @RequestParam("endDate") String endD,
			@RequestParam("select1") String s1, @RequestParam("select2") String s2,
			@RequestParam(name = "select3", required = false) String s3,
			@RequestParam(name = "select4", required = false) String s4,
			@RequestParam(name = "select5", required = false) String s5) {
		VoteActivity voteActivity = new VoteActivity();
		voteActivity.setTitle(title);
		voteActivity.setContext(context);
		//System.out.println(startD);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = format.parse(startD);
			voteActivity.setStartDate(date);
			date = format.parse(endD);
			voteActivity.setEndDate(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		voteActivity.setSelect1(s1);
		voteActivity.setSelect2(s2);
		voteActivity.setSelect3(s3);
		voteActivity.setSelect4(s4);
		voteActivity.setSelect5(s5);
		voteActivityService.insertVote(voteActivity);
		return "redirect:/vote/voteBackPage";
	}

	// 更新投票活動
	@PutMapping("/vote/editVote")
	public String editVoteActivity(@RequestParam("id") Integer id, @RequestParam("title") String title,
			@RequestParam("context") String context, @RequestParam("startDate") String startD,
			@RequestParam("endDate") String endD, @RequestParam("select1") String s1,
			@RequestParam("select2") String s2, @RequestParam(name = "select3", required = false) String s3,
			@RequestParam(name = "select4", required = false) String s4,
			@RequestParam(name = "select5", required = false) String s5) {
		VoteActivity voteActivity = new VoteActivity();
		voteActivity.setId(id);
		voteActivity.setTitle(title);
		voteActivity.setContext(context);
		System.out.println(startD);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = format.parse(startD);
			voteActivity.setStartDate(date);
			date = format.parse(endD);
			voteActivity.setEndDate(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		voteActivity.setSelect1(s1);
		voteActivity.setSelect2(s2);
		voteActivity.setSelect3(s3);
		voteActivity.setSelect4(s4);
		voteActivity.setSelect5(s5);
		voteActivityService.insertVote(voteActivity);
		return "redirect:/vote/voteBackPage";
	}

	// 刪除投票活動
	@DeleteMapping("/vote/deleteVote")
	public String deleteVote(@RequestParam Integer id) {
		voteActivityService.deleteById(id);

		return "redirect:/vote/voteBackPage";
	}

	// 進入投票頁面
	@GetMapping("/vote/doVotePage")
	public String visitDoVotePage(@RequestParam(name="voteid")Integer voteId, Model model) {
		VoteActivity voteActivity=voteActivityService.findById(voteId);
		
		model.addAttribute("voteActivity",voteActivity);
		
		return "vote/doVoteActivity";
	}
	
	//進行投票並新增記錄
	@PostMapping("/vote/addVoteRecord")
	public String addDoVoteRecord(@RequestParam(name="voteid")Integer voteId,
			@RequestParam(name="userid")Integer householdId,
			@RequestParam(name="choose")Integer choose) {
		VoteRecord voteRecord = new VoteRecord();
		
		voteRecord.setChoose(choose);
		voteRecord.setVoteDate(new Date());
		voteActivityService.insertVoteRecord(voteRecord, voteId, householdId);
		
		return "redirect:/vote/voteFrontPage";
	}
	
	//進入投票活動詳細頁面
	@GetMapping("/vote/voteDetailPage")
	public String visitVoteDetailPage(@RequestParam(name="voteid")Integer voteId, Model model) {
		VoteActivity voteActivity=voteActivityService.findById(voteId);
		int[] voteCount = voteActivityService.findVoteCount(voteActivity);
		
		model.addAttribute("voteActivity", voteActivity);
		model.addAttribute("voteCount", voteCount);
		
		return "vote/showVoteActivityDetail";
	}
}
