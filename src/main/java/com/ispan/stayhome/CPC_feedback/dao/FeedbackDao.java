package com.ispan.stayhome.CPC_feedback.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ispan.stayhome.CPC_feedback.model.Feedback;

//JpaRepository<Feedback, Integer> 框格裡面第一個是物件的型別、第二個是物件的id資料型別
//JpaRepository增删改查
public interface FeedbackDao extends JpaRepository<Feedback, Integer> { 
	
	//findFirst  (Feedback) Entity的名稱   ByOrderBy  (FeedbackDate) 裡面的屬性名  Desc
	public Feedback findFirstFeedbackByOrderByFeedbackDateDesc();
	
	@Query(value="  select * from Feedback where Feedback_Name like '%-TOP&&&' order by Feedback_Content desc" ,nativeQuery=true)
	public List<Feedback> searchTopFeedBack();
	
}
