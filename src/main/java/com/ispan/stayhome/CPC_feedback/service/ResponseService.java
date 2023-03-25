package com.ispan.stayhome.CPC_feedback.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ispan.stayhome.CPC_feedback.dao.ResponseDao;
import com.ispan.stayhome.CPC_feedback.model.Response;

@Service
@Transactional
public class ResponseService {
	
	@Autowired  //依賴注入  重IOC容器裡面拿到，如果沒寫Autowired 就是空的
	private ResponseDao responseDao;
	
	
	//新增
	public void responseadd(Response response) {
		responseDao.save(response);
	}

	
	
	//id查詢
	public Response finById(Integer responseId) {
		Optional<Response> optional = responseDao.findById(responseId);
		
		if(optional.isEmpty()) {
			return null;
		}
		return optional.get();
	}
	
	//刪除
	public void deleteById(Integer responseId) {
		responseDao.deleteById(responseId);
	}
	
	
	//搜尋頁面
	public Page<Response> finByPage(Integer pageNumber){
		Pageable pgb =PageRequest.of(pageNumber-1,6,Sort.Direction.DESC,"responseDate");
		
		Page<Response> page = responseDao.findAll(pgb);
		
		return page;
	}
	
	
	//更新
	public Response updateById(Integer responseId,String newMsg) {
		Optional<Response> optional = responseDao.findById(responseId);
		
		if(optional.isPresent()) {
			Response msg = optional.get();
			msg.setResponseContent(newMsg);
			
			return msg;
		}
		System.out.println("找不到資料");
		return null;
	}
	
	public Response findLatest() {
		return responseDao.findFirstResponseByOrderByResponseDateDesc();
		
	}
	
	public Page<Response> findByfeedbackId(Integer feedbackId,Pageable pageable){
		
		return responseDao.findResponseByfeedbackId(feedbackId,pageable);
	}

	
	//取得回覆總筆數
	public Integer getTotalResponse() {
		return responseDao.getTotalResponse();
	}
	
	public Integer getOneResponse(Integer feedbackId) {
		return  responseDao.getOneResponse(feedbackId);
	}
}
