package com.ispan.stayhome.CPC_feedback.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ispan.stayhome.CPC_feedback.dao.FeedbackDao;
import com.ispan.stayhome.CPC_feedback.model.Feedback;

@Service
@Transactional
public class FeedbackService {
	
	@Autowired //依賴注入  重IOC容器裡面拿到，如果沒寫Autowired 就是空的
	private FeedbackDao feedbackDao; //FeedbackDao 是一個接口，代表著一個資料庫操作的類型。通過將這個介面注入到類中，類可以使用該介面的方法來執行資料庫操作，而不需要了解實現細節。
	
	//新增
	public void feedbackadd(Feedback feedback) {//括號裡面是方法的參數列表。在這個方法中，Feedback feedback 是一個表示feedback對象的參數，用來將feedback對象傳遞到方法中，以便該方法可以使用它進行一些操作。
		feedbackDao.save(feedback);//{feedbackDao.save(feedback);}是用來將feedback對象保存到資料庫中，feedbackDao是一個實現了FeedbackDao介面的Hibernate Repository。save方法是CrudRepository提供的方法之一，它將對象保存到資料庫中。這個代碼確保了feedback對象被保存到資料庫中。
	}
	
	
	
	//ID查詢 從資料庫中查找指定 id 的 Feedback 物件，如果找到，則返回該物件，否則返回 null。使用 Optional 類型的物件可以避免空指針異常。
	public Feedback feedbackfinById(Integer feedbackId) {//接收一個整數型別的 feedbackId 參數，返回 Feedback 物件。
		Optional<Feedback> optional = feedbackDao.findById(feedbackId);//使用 feedbackDao 物件來調用 findById 方法，這個方法是用來根據指定的 id 從資料庫中查找對應的 Feedback 物件。回傳的是一個 Optional 類型的物件，這個物件可以包含一個 Feedback 物件或空值。
		
		if(optional.isEmpty()) {//使用 Optional 物件的 isEmpty 方法檢查 Optional 物件是否為空值。如果是，則返回 null。
			return null;
		}
		return optional.get();//如果 Optional 物件不是空值，則使用 Optional 物件的 get 方法返回對應的 Feedback 物件。
	}
	
	
	
	//刪除
	public void feedbackdeleteById(Integer feedbackId) {
		feedbackDao.deleteById(feedbackId);//使用 feedbackDao 物件調用 deleteById 方法，這個方法是用來從資料庫中刪除指定 id 的 Feedback 物件。
	}
	
	
	//從資料庫中刪除指定的 Feedback 物件。
	public void feedbackdeleteByEntity(Feedback feedback) {
		feedbackDao.delete(feedback);
	}
	
	
	//搜尋頁數
	public Page<Feedback> feedbackByPage(Integer pageNumber){//接收一個整數型別的 pageNuInteger 參數，返回一個 Page<Feedback> 物件。
		Pageable pgb= PageRequest.of(pageNumber-1, 5,Sort.Direction.DESC,"feedbackDate");//使用 PageRequest 類建立一個分頁查詢的配置，pageNuInteger 參數代表目前所在的頁數，5 代表每頁顯示的數量，"feedbackDate" 則代表以 feedbackDate 這個欄位作為排序依據，Sort.Direction.DESC 則代表降序排列。
		
		Page<Feedback> page = feedbackDao.findAll(pgb);//使用 feedbackDao 物件調用 findAll 方法，這個方法是用來根據傳入的分頁配置進行查詢，返回一個 Page<Feedback> 物件。
		
		return page;
	}
	
	
	//更新意見內容
	public Feedback feedbackupdateById(Integer feedbackId,String newMsg) {//定義一個名為 feedbackupdateById 的公開方法，接受兩個輸入參數：feedbackId 代表 Feedback 資料的 ID，newMsg 代表要更新的 Feedback 內容。
		Optional<Feedback> optional = feedbackDao.findById(feedbackId);//使用 feedbackDao 物件來調用 findById 方法，這個方法是用來根據指定的 id 從資料庫中查找對應的 Feedback 物件。回傳的是一個 Optional 類型的物件，這個物件可以包含一個 Feedback 物件或空值。
	
		if(optional.isPresent()) {//判斷 Optional 物件中是否有值，如果有進入判斷式區塊。
			Feedback msg = optional.get();//將 Optional 物件中儲存的 Feedback 資料取出，並將其儲存至 msg 變數中。
			msg.setFeedbackContent(newMsg);//將 msg 變數中的 Feedback 內容更新為 newMsg，並將其傳回。
			return msg;
		}
		System.out.println("找不到資料");
		return null;
	}
	
	public Feedback findLatest() {
		return feedbackDao.findFirstFeedbackByOrderByFeedbackDateDesc();
	}
	
	public List<Feedback> searchTopFeedBack(){
		return feedbackDao.searchTopFeedBack();
		
	}

}
