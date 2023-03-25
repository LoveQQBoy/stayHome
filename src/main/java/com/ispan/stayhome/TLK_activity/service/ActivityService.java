package com.ispan.stayhome.TLK_activity.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ispan.stayhome.TLK_activity.dao.ActivityDao;
import com.ispan.stayhome.TLK_activity.model.Activity;

@Service
public class ActivityService {
	
	@Autowired
	private ActivityDao activityDao;
	 @Autowired
	 private Validator validator;
	
	/////外加入member前
//	@Transactional
//	public void activityInsert(Activity activity) {
//		activityDao.save(activity);
//	}
	
	@Transactional
	public void activityInsert(Activity activity) {
		activityDao.save(activity);
	}
	
	public Activity activityInsertReturnActivity(Activity activity) {
		return activityDao.save(activity);
	}
	
	public Activity activityFindById(Integer Id) {
		Optional<Activity> activityOptional=activityDao.findById(Id);
		if(activityOptional.isEmpty()) {
			return null;
		}
		return activityOptional.get();
	}
	
	public Page<Activity> activityFindPage(Integer pageNumber){
		Pageable activityPage=PageRequest.of(pageNumber-1, 10,Sort.Direction.DESC,"applicantDeadDate");
		Page<Activity> page=activityDao.findAll(activityPage);
		return page;
	}
	
	public void deleteById(Integer id) {
		activityDao.deleteById(id);
	}
	
	public Page<Activity> applicantFindTwentyPage(Integer pageNumber){
		Pageable activityPage=PageRequest.of(pageNumber-1, 6,Sort.Direction.DESC,"applicantDeadDate");
		Page<Activity> page=activityDao.findAll(activityPage);
		return page;
	}
	
	public Integer activityNumber() {
		return activityDao.activityNumber();
	}
	
	@Transactional
	public void updateApplicantStatusByTime() throws ParseException {
		List<Object[]> activityIdAndStatus=activityDao.findIdAndApplicantStatus();	
		for(Object[] activity:activityIdAndStatus) {
			//////////////抓取活動並轉成文字////////////////////
			Integer activityId =Integer.parseInt(activity[0].toString());
			String activityTimeString = activity[1].toString();
			//////////////抓取活動並轉成文字////////////////////
			//////////////將文字轉成相同時間格式////////////////////
			SimpleDateFormat timeformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String nowTimeformatString=timeformat.format(new Date());
			Date nowTimeformatNew =timeformat.parse(nowTimeformatString);
			Date activityTime =timeformat.parse(activityTimeString);
			//////////////將文字轉成相同時間格式////////////////////
			//////////////條件判斷,如果現在時間>=活動截止日則將活動更改成報名截止////////////////////
			if(nowTimeformatNew.after(activityTime) ||  nowTimeformatNew.equals(activityTime)){
				activityDao.changeStatusByDate(activityId);
				System.out.println("///////////////////////////");
			}
		}
	}
	
	public Integer countNumber(Integer activityId) {
		Integer count=activityDao.countSearch(activityId);
		return count;
	}
	
	public Page<Activity> searchPage(Integer pageNumber,String keyWord,String startTime,String endTime,String status){
		System.out.println(keyWord);
		System.out.println(startTime);
		System.out.println(endTime);
		System.out.println(status);
		
		Pageable pageable=PageRequest.of(pageNumber-1, 6,Direction.DESC,"Applicant_Dead_Date");
		Page<Activity> page=activityDao.searchPage(keyWord, startTime, endTime, status, pageable);
		System.out.println(page.toString());
		return page;
	}
	
	public String searchActivityStatus(Integer activityId) {
		Optional<String> optional=activityDao.searchActivityStatus(activityId);
		if(!optional.isEmpty()) {
			return optional.get();
		}
		return null;
	}
}
