package com.ispan.stayhome.LSF_vote.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ispan.stayhome.LSF_vote.dao.VoteActivityDao;
import com.ispan.stayhome.LSF_vote.dao.VoteHouseholdDao;
import com.ispan.stayhome.LSF_vote.dao.VoteRecordDao;
import com.ispan.stayhome.LSF_vote.model.VoteActivity;
import com.ispan.stayhome.LSF_vote.model.VoteHousehold;
import com.ispan.stayhome.LSF_vote.model.VoteRecord;

@Service
@Transactional
public class VoteActivityService {

	@Autowired
	private VoteActivityDao voteActivityDao;//投票活動
	
	@Autowired
	private VoteRecordDao voteRecordDao;//投票紀錄
	
	@Autowired
	private VoteHouseholdDao voteHouseholdDao;//住戶
	
	//沒有id則新增住戶，有id則更新住戶
	public void insertVote(VoteActivity voteActivity) {
		voteActivityDao.save(voteActivity);
	}
	
	//請求前台頁面，準備要顯示的資料，有分頁功能、狀態查詢功能
	public Page<VoteActivity> findAllForFrontPage(Integer pageNumber, Integer useState){
		
		Page<VoteActivity> page = null;
		//分頁功能設定，一次五筆資料，照開始時間由小到大排序
		Pageable pgb = PageRequest.of(pageNumber-1,5,Sort.Direction.ASC,"Start_Date");
		
		if(useState==null) {//查詢所有投票，不包含[待啟用]		
			page = voteActivityDao.findAllForFrontPage(pgb);
		}else {//查詢[已結束]、[投票中]狀態的投票活動
			if(useState==1)
				page = voteActivityDao.finAllUsingForFront(pgb);
			else
				page = voteActivityDao.finAllUsedForFront(pgb);
		}
		
		return page;
	}
	
	//請求後台頁面，準備要顯示的資料，有分頁功能
	public Page<VoteActivity> findByBackPage(Integer pageNumber){
		
		Pageable pgb = PageRequest.of(pageNumber-1,5,Sort.Direction.ASC,"startDate");
		
		Page<VoteActivity> page = voteActivityDao.findAll(pgb);
		
		return page;
	}
	
	//後台頁面，使用"標題關鍵字"或是"狀態"查詢功能
	public Page<VoteActivity> findAllByCondition(Integer pageNumber,String title, Integer useState){
		
		Pageable pgb = PageRequest.of(pageNumber-1,5,Sort.Direction.ASC,"Start_Date");
		Page<VoteActivity> page = null;
		
		if(title.equals("noEnter"))//標題欄位未輸入，初始化為空值
			title="";
		
		if(useState==1) {//狀態欄位，分[待啟用]、[投票中]、[已結束]
			page = voteActivityDao.findUnusedByTitle(title, pgb);
		}else if(useState==2) {
			page = voteActivityDao.findUsingByTitle(title, pgb);
		}else if(useState==3){
			page = voteActivityDao.findUsedByTitle(title, pgb);
		}else {
			page = voteActivityDao.findAllByTitle(title, pgb);
		}
		
		return page;
	}
	
	//根據id查詢對應的投票活動
	public VoteActivity findById(Integer id) {
		
		Optional<VoteActivity> optional = voteActivityDao.findById(id);
		
		if(optional.isEmpty()) {
			return null;
		}
		
		return optional.get();
	}
	
	//根據id刪除對應的投票活動
	public void deleteById(Integer id) {
		
		voteActivityDao.deleteById(id);
	}
	
	//前台有使用者進行投票，則新增對應投票紀錄
	public void insertVoteRecord(VoteRecord voteRecord, Integer voteId, Integer userId) {
		
		VoteActivity voteActivity = findById(voteId);
		voteRecord.setVoteActivity(voteActivity);
		
		VoteHousehold voteHousehold = voteHouseholdDao.findById(userId).get();
		voteRecord.setVoteHousehold(voteHousehold);
		
		voteRecordDao.save(voteRecord);
	}
	
	/*根據使用者找出是否需要進行投票的資料
	 * 已結束或者已經投過票都不需再進行投票*/
	public boolean[] findNeedToVote(HttpSession session, Page<VoteActivity> voteActivityPage) {
		
		int i=0; //索引值
		int userId = (int) session.getAttribute("pid");	//使用者ID
		boolean[] needVote = new boolean[5]; //每一頁只有五比投票活動，對應是否需要進行投票
		Date today = new Date(); //今天日期
		List<VoteActivity> voteActivities = voteActivityPage.getContent();
		
		for (VoteActivity voteActivity : voteActivities) {
			if(today.after(voteActivity.getEndDate())) { //超過投票結束日期，則不進行投票
				needVote[i] = false;
				i++;
				continue;
			}else {
				needVote[i] = true;
				for (VoteRecord voteRecord : voteActivity.getRecords()) {
					//有找到對應的投票紀錄，代表已經投過票了
					if(userId == voteRecord.getVoteHousehold().getId()) {
						needVote[i]=false;
						break;
					}
				}
				i++;
			}
		}
		
		return needVote;
	}
	
	//找出投票總人數、各選項的投票人數
	public int[] findVoteCount(VoteActivity voteActivity) {
		Set<VoteRecord> records = voteActivity.getRecords();
		int[] voteCount = new int[6];//儲存(總人數、各選項人數)
		
		//總人數
		List<VoteHousehold> voteHouseholds = voteHouseholdDao.findAll();
		voteCount[0] = voteHouseholds.size();
		
		//各選項人數
		for (VoteRecord voteRecord : records) {
			voteCount[voteRecord.getChoose()]++;
		}
		return voteCount;
	}
	
	//找出所有的投票活動總數
	public Integer findTotalCountVoteActivity() {
		List<VoteActivity> voteActivities = voteActivityDao.findAll();
		
		int totalCountVA = voteActivities.size();
		
		return totalCountVA;
	}
	
	////TLK前端頁面顯示最新活動
public Page<VoteActivity> findPageForHomePage(Integer pageNumber){
		
		Pageable pgb = PageRequest.of(pageNumber-1,5,Sort.Direction.DESC,"startDate");
		
		Page<VoteActivity> page = voteActivityDao.findAll(pgb);
		
		return page;
	}
}
