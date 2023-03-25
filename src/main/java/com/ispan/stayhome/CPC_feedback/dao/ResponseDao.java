package com.ispan.stayhome.CPC_feedback.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ispan.stayhome.CPC_feedback.model.Response;

//JpaRepository<Feedback, Integer> 框格裡面第一個是物件的型別、第二個是物件的id資料型別
//JpaRepository增删改查
public interface ResponseDao extends JpaRepository<Response, Integer> {
	
	//findFirst  (Response) Entity的名稱   ByOrderBy  (ResponseDate) 裡面的屬性名  Desc
		public Response findFirstResponseByOrderByResponseDateDesc();
		
		

		//Query HQL+from(對類別)  SQL+select(對資料庫) 要加nativeQuery = true ，是可以执行原生sql语句
		@Query(value=" from Response r where r.feedback.feedbackId = :feedbackId")
		 public Page<Response> findResponseByfeedbackId(@Param(value="feedbackId") Integer feedbackId,Pageable pageable);
		
		
		@Query(value="SELECT COUNT(*) FROM Response" ,nativeQuery = true)
	    public Integer getTotalResponse();
		
		@Query(value="select count(*) from Response where FK_Feedback_Id  = :feedbackId" ,nativeQuery = true)
		public Integer getOneResponse(@Param(value="feedbackId") Integer feedbackId);
}
