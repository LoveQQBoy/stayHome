package com.ispan.stayhome.CPC_feedback.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ispan.stayhome.CPC_feedback.model.Feedback;
import com.ispan.stayhome.CPC_feedback.model.Response;
import com.ispan.stayhome.CPC_feedback.service.FeedbackService;
import com.ispan.stayhome.CPC_feedback.service.ResponseService;
import com.ispan.stayhome.CPC_login.model.HouseholdData;
import com.ispan.stayhome.CPC_login.service.HouseholdService;

@Controller
public class FeedbackController {
	
	@Autowired
	private FeedbackService feedbackService;
	
	@Autowired
	private HouseholdService householdService;
	
	@Autowired
	private ResponseService responseService;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	//跳轉意見頁面
	@GetMapping("/feedback/add") //當用戶訪問"/feedback/add"路徑時，這個方法會被調用。
	public String addFeedbackpage(Model model) {//有一個Model對象作為參數，用於傳遞數據到前端頁面。
		Feedback feedback = new Feedback();//實例化一個Feedback對象，用於儲存前端表單提交的數據。
		model.addAttribute("feedback", feedback);//將Feedback對象添加到Model對象中，並指定屬性名"feedback"。
		
		return "feedback/addFeedback";//表示導向到"feedback/addFeedback.JSP"頁面。
		
	}
	//@ModelAttribute("feedback")@Valid  Feedback feedback,BindingResult bindingResultt 要連一起 不然會報錯
	//新增意見輸入
	@PostMapping("/feedback/post")
	public String addFeedbackPost(@ModelAttribute("feedback")@Valid  Feedback feedback,BindingResult bindingResult,HttpSession session,Model model,RedirectAttributes redirectAttributes) {
		
		if (bindingResult.hasErrors()) {
			
			return "feedback/addFeedback";
		}
		
		
		/////////////取會員/////////////
		Integer memberId=(Integer)session.getAttribute("pid"); //從 session 中取得 pid 屬性的值，並將其轉換為 Integer 型別的 memberId 變數。假設 session 中有名為 pid 的屬性，並且該屬性的值是整數，那麼 memberId 變數就會被賦值為該整數。否則，memberId 變數將被設置為 null
		HouseholdData householdData=householdService.findById(memberId);//通過 memberId 從資料庫中取得該成員的戶籍資料。它呼叫 householdService 物件的 findById 方法，並傳遞 memberId 參數。如果 memberId 為 null 或在資料庫中找不到該成員的戶籍資料，則 householdData 變數將被設置為 null
		feedback.setHouseholdData(householdData);//將 householdData 賦值給 feedback 物件的 houseHoldData 屬性。feedback 物件是用來儲存用戶回饋的資料，而 houseHoldData 屬性是用來儲存該用戶所屬的戶籍資料。

		
		feedbackService.feedbackadd(feedback);
		redirectAttributes.addFlashAttribute("success", "發表成功！");
		
//		Feedback latestMsg = feedbackService.findLatest();
//		model.addAttribute("latestMsg", latestMsg);
		
		
		return "redirect:/feedback/showfeedback";  //redirect: 吃的是controller的URL 而不是頁面路徑
	}
	
	//跳轉留言板頁面
	@GetMapping("/feedback/showfeedback")
	public String showFeedbackPage(@RequestParam(name = "p",defaultValue = "1") Integer pageNumber,Model model) {
		Page<Feedback> feedbackByPage = feedbackService.feedbackByPage(pageNumber);
		model.addAttribute("feedbackByPage", feedbackByPage);
		
		return "feedback/showFeedback";
	}
	
	//跳轉編輯頁面
	@GetMapping("/feedback/editpage")
	public String editFeedbackPage(@RequestParam Integer id,Model model) { // showFeedback.jsp頁面 編輯 會送參數id過來，再用@RequestParam Integer id接住

		Feedback msg = feedbackService.feedbackfinById(id); //透過Service的finById方法 取得feedback的id，會回傳一個物件(msg 自己取名稱)

		model.addAttribute("msg", msg);//再利用Model的model.addAttribute 把 msg包起來送過去前端
		
		//這段程式碼是一個使用@GetMapping註釋的方法，當使用者訪問"/feedback/editpage"路徑時會呼叫此方法。這個方法需要傳遞一個名為id的@RequestParam註釋的整數參數，並使用Model對象傳遞數據到視圖中。
		//在方法中，它首先呼叫feedbackService中的feedbackfinById方法並傳遞id作為參數來獲取特定的Feedback對象。接著將這個Feedback對象儲存在Model中，並使用視圖名稱"msg"作為模型屬性的名稱，最後將模型返回給視圖。
		//這段程式碼的作用是為了在編輯Feedback時，將需要編輯的Feedback對象的信息傳遞到視圖中，以供用戶進行修改。
		
		return "feedback/editFeedback";
	}
	
	//送出編輯資料
	@PostMapping("/feedback/editpost")
	public String postEdit(@ModelAttribute("msg") Feedback msg,HttpSession session) {//editFeedback.jsp提交的表單數據，這裡用 @ModelAttribute 註釋可以將表單中提交的數據自動綁定，並註明名稱 Feedback 物件上。這樣就可以在這個方法中直接使用這個 Feedback 物件來處理表單數據了。
		
		Integer memberId=(Integer)session.getAttribute("pid");
		HouseholdData householdData=householdService.findById(memberId);
		msg.setHouseholdData(householdData);
		
		feedbackService.feedbackadd(msg);
		
		return "redirect:/feedback/showfeedback"; //redirect: 吃的是controller的URL 而不是頁面路徑
	}
	
	//刪除
	@DeleteMapping("/feedback/deletefeedback")
	public String deleteFeedback(@RequestParam Integer id) {
		feedbackService.feedbackdeleteById(id);
		
		
		return "redirect:/feedback/showfeedback";
	}
	
	
	//跳轉回覆頁面
	/*@PathVariable 是 Spring Framework 提供的一個注解（Annotation），用於處理 RESTful Web Service 中 URL 的變數（variable）。
	當我們在 RESTful Web Service 的 URL 中定義了變數時，例如：/users/{userId}，我們可以使用 @PathVariable 注解將 {userId} 這個變數的值取出來，並作為方法參數使用。
	舉例來說，若我們有一個 RESTful Web Service 的 URL 是 /users/{userId}，我們可以使用 @PathVariable 將 {userId} 這個變數取出來：
	這樣就可以在方法內使用 userId 這個參數，例如根據該 userId 從資料庫中查詢出對應的用戶資料，最後返回該用戶的資料。
	總之，@PathVariable 可以讓我們方便地從 RESTful Web Service 的 URL 中取出變數值，並作為方法參數使用，便於開發 RESTful Web Service。*/
	@GetMapping("/feedback/replypage/{feedbackId}")
	public String replyFeedbackPage(@PathVariable("feedbackId") Integer feedbackId,@RequestParam(name = "p",defaultValue = "1") Integer pageNumber,Model model) {
		Feedback feedbackfinById = feedbackService.feedbackfinById(feedbackId);
		model.addAttribute("feedback", feedbackfinById);
		
		//Page<Feedback> feedbackByPage = feedbackService.feedbackByPage(pageNumber);
		//model.addAttribute("feedbackByPage", feedbackByPage);
		
		//////////////Response回覆資訊////////////////
		Response response = new Response();
		model.addAttribute("response", response);
		
		/////////////Response查詢頁面////////////////
		Pageable pgb =PageRequest.of(pageNumber-1,6,Sort.Direction.DESC,"responseDate");
		
		Page<Response> responseByPage = responseService.findByfeedbackId(feedbackId, pgb);
		model.addAttribute("responseByPage", responseByPage);
		
		return "feedback/replyFeedback";
	}
	
	@ResponseBody
	@GetMapping("/feedback/showfeedbackJson")
	public Page<Feedback> showFeedbackPageJson(@RequestParam(name = "p",defaultValue = "1") Integer pageNumber,Model model) {
		Page<Feedback> feedbackByPage = feedbackService.feedbackByPage(pageNumber);
		return feedbackByPage;
	}
	
//	@ResponseBody
//	@GetMapping("feedback/chooseTop")
//	public void  chooseTop (@RequestParam Integer responseId,@RequestParam String feedbackName)  {
//		feedbackName = feedbackName+ "-TOP&&&";
//		Feedback feedback=feedbackService.feedbackfinById(responseId);
//		feedback.setFeedbackId(responseId);
//		feedback.setFeedbackName(feedbackName);
//		feedbackService.feedbackadd(feedback);
//	}
//	
//	@ResponseBody
//	@GetMapping("feedback/searchTopFeedBack")
//	public List<Feedback> searchTopFeedBack() {
//		List<Feedback> feedback=feedbackService.searchTopFeedBack();
//		if(feedback.get(0) != null) {
//			return feedback;
//		}
//		return null ;
//	}
	
	

}
