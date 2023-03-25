package com.ispan.stayhome.TLK_activity.service;



import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.Join;

import org.apache.catalina.connector.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ispan.stayhome.CPC_login.model.HouseholdData;
import com.ispan.stayhome.TLK_activity.dao.ActivityDao;
import com.ispan.stayhome.TLK_activity.dao.ApplicantDao;
import com.ispan.stayhome.TLK_activity.model.Applicant;

@Service
public class ApplicantService {
	@Autowired
	private ApplicantDao applicantDao;
	@Autowired
	private ActivityDao activityDao;
	
	@Transactional
	public void applicantInsert(Applicant applicant) {
		applicantDao.save(applicant);
	}
	
	public Applicant applicantFindById(Integer id) {
		Optional<Applicant> applicantOptional= applicantDao.findById(id);
		if(applicantOptional.isEmpty()) {
			return null;
		}
		return applicantOptional.get();
	}
	
	@Transactional
	public void applicantAddActivity(Integer activityId,Applicant saveApplicant,RedirectAttributes redirectAttributes) {
		/////////////////取出活動最大報名人數 & 報名人數////////////////////
		int applicantNumber = applicantDao.applicantNumber(activityId).intValue();
		int activityNumberFull = activityDao.findApplicantNumberFull(activityId).intValue();
		Integer householdId=saveApplicant.getHouseholdData().getId();
		/////////////////取出活動最大報名人數 & 報名人數////////////////////
		/////////////////條件判斷這個人是否重複報名////////////////////
		boolean applicantStatus=Boolean.parseBoolean(applicantDao.applicantStatus(activityId,householdId));
		System.out.println("=============================================");
		System.out.println(applicantStatus);
		if(applicantStatus) {
			String message = "你已經報名過了";
			redirectAttributes.addFlashAttribute("message", message);
			return ;
		}
		/////////////////條件判斷這個人是否重複報名////////////////////
		/////////////////條件判斷活動最大人數>報名者////////////////////
		if(applicantNumber < activityNumberFull) {
			applicantDao.save(saveApplicant);
			int updateApplicantNumber = applicantDao.applicantNumber(activityId).intValue();
			System.out.println("=============================================");
			System.out.println(updateApplicantNumber);
			System.out.println(activityId);
			activityDao.updateApplicantNnmber(activityId,updateApplicantNumber);
			String message = "報名成功";
			redirectAttributes.addFlashAttribute("message", message);
			/////////////////條件判斷儲存後若人數=活動最大人數則將活動狀態修改成已額滿////////////////////
			int applicantNumberUpdate = applicantDao.applicantNumber(activityId).intValue();
			if(applicantNumberUpdate == activityNumberFull) {
				activityDao.updateActivityStatus(activityId);
			}
			return;
		}
		/////////////////條件判斷活動最大人數==報名者則發出額滿訊息////////////////////
		if(applicantNumber == activityNumberFull) {
			String message = "活動已額滿";
			redirectAttributes.addFlashAttribute("message", message);
			return ;
		}
	}
	
	public List<Object> applicantInnerJoinActivity(Integer applicantId){
		List<Object> applicantInnerJoin=applicantDao.applicantInnerJoinActivity(applicantId);
		return applicantInnerJoin;
	}
	
	@Transactional
	public void applicantCancleActivity(Integer activityId,Integer memberId,RedirectAttributes redirectattributes) {
		System.out.println("zzzzzzzzzzzzzzzz");
		System.out.println(activityId);
		System.out.println(memberId);
		
		applicantDao.applicantCancleActivity(activityId,memberId);
		int applicantNumber=applicantDao.applicantNumber(activityId).intValue();
		activityDao.updateApplicantNnmber(activityId, applicantNumber);

		checkActivityStatus(activityId);

		
	}
	
	//條件判斷如果活動是已額滿就會修改成未額滿
	@Transactional
	public void checkActivityStatus(Integer activityId) {
		int applicantNumber=applicantDao.applicantNumber(activityId).intValue();
		int applicantNumberFull=activityDao.findApplicantNumberFull(activityId).intValue();
		//活動最大人數>報名人數 就執行取消
		if(applicantNumberFull > applicantNumber) {
			boolean applicantStatus=Boolean.parseBoolean(activityDao.activityApplicantStatusQuery(activityId));
			if(applicantStatus) {
				activityDao.activityCancleStatus(activityId);
			}
		}else if(applicantNumberFull == applicantNumber) {
			boolean applicantStatus=Boolean.parseBoolean(activityDao.activityApplicantStatusQuery(activityId));
			if(applicantStatus) {
				activityDao.activityCancleStatus(activityId);
			}
		}else {
			
		}
	}
	
	@Transactional
	public String checkActivityStatus(Integer activityId,Integer applicantNumberFull) {
		int applicantNumber=applicantDao.applicantNumber(activityId).intValue();
		System.out.println("cccccccccccccccccc");
		System.out.println(applicantNumber);
		//活動最大人數>報名人數 就執行取消
		if(applicantNumberFull > applicantNumber) {
				return "true";
		}else if(applicantNumberFull == applicantNumber) {
				return "equal";
		}else {
		}
		return "false";
	}
	
	@Transactional
	public void applicantDeleteActivity(Integer activityId) {
		applicantDao.applicantDeleteActivity(activityId);
	}
	
	public Integer applicantNumber(Integer activityId) {
		Integer applicantNumber = applicantDao.applicantNumber(activityId).intValue();
		return applicantNumber;
	}
	
	public Integer applicantNumberAll(Integer activityId) {
		Integer applicantNumberAll=applicantDao.applicantNumberAll(activityId).intValue();
		return applicantNumberAll;
	}
	
	public Page<Applicant> applicantPage(Integer pageNumber){
		Pageable pageable=PageRequest.of(pageNumber-1,10,Direction.DESC,"registrationDate");
		Page<Applicant> page=applicantDao.findAll(pageable);
		return page;
		
	}
	
	public Page<Applicant> applicantPageByPersonal(Integer pageNumber,Integer householdId){
		Pageable pageable=PageRequest.of(pageNumber-1, 10,Direction.DESC,"Registration_Date");
		Page<Applicant> page=applicantDao.findPageByPersonal(householdId, pageable);
		return page;
	}
	
	public Page<Applicant> findPageByAll(Integer pageNumber){
		Pageable pageable=PageRequest.of(pageNumber-1, 1,Direction.DESC,"registrationDate");
		Page<Applicant> page=applicantDao.findAll(pageable);
		return page;
	}
	
	public Page<Applicant> findPageByActivity(Integer pageNumber,Integer activityId){
		Pageable pageable=PageRequest.of(pageNumber-1, 5,Direction.DESC,"Registration_Date");
		Page<Applicant> page=applicantDao.findPageByActivity(activityId, pageable);
		return page;
	}
	

}
