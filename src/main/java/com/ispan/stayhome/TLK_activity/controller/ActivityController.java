package com.ispan.stayhome.TLK_activity.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ispan.stayhome.CPC_login.dao.HouseholdDao;
import com.ispan.stayhome.CPC_login.model.HouseholdData;
import com.ispan.stayhome.CPC_login.service.HouseholdService;
import com.ispan.stayhome.TLK_activity.model.Activity;
import com.ispan.stayhome.TLK_activity.service.ActivityService;
import com.ispan.stayhome.TLK_activity.service.ApplicantService;

@Controller
@EnableScheduling
public class ActivityController {

	@Autowired
	public ActivityService activityService;
	@Autowired
	public HouseholdService householdService;
	@Autowired
	public ApplicantService applicantService;

	///////////////////// 等會員登入完成後之後要拿掉///////////////////////
	@Autowired
	public HouseholdDao householdDao;

	@GetMapping("memberLogin")
	public String login() {
		return "loginTest/loginTest";
	}

	@GetMapping("memberLogin/login")
	public String memberlogin(@RequestParam String email, @RequestParam String password, HttpSession session) {
		HouseholdData data = householdDao.findHouseholdDataByEmailAndPassword(email, password);
		Integer pid = data.getId();
		String emailData = data.getEmail();
		String name = data.getName();
		session.setAttribute("pid", pid);
		session.setAttribute("email", emailData);
		session.setAttribute("name", name);

		return "loginTest/loginTest";
	}

	///////////////////// 等會員登入完成後之後要拿掉///////////////////////

	@GetMapping("activity/addActivityPage")
	public String addActivityPage() {
		return "activity/addActivity";
	}

	// 未加入member前
//	@PostMapping("/activity/insertActivity")
//	public String insertActivity(@RequestParam Integer managerId,
//			@RequestParam String managerName,
//			@RequestParam String title,@RequestParam String text,
//			@RequestParam String activityTeacher,@RequestParam String place,
//			@RequestParam String teacherPhone,@RequestParam Integer applicantNumber,
//			@RequestParam Integer applicantNumberFull ,@RequestParam String applicantStatus,
//			@RequestParam Date applicantCreateDate,@RequestParam Date applicantDeadDate,
//			@RequestParam MultipartFile picture) throws IOException {
//			
//		Activity activity =new Activity(managerId,managerName,title,text,activityTeacher,place
//				,teacherPhone,applicantNumber,applicantNumberFull,applicantStatus,applicantCreateDate,
//				applicantDeadDate);
//		
//		byte[] photoByte=picture.getBytes();
//		activity.setPicture(photoByte);
//		activityService.activityInsert(activity);
//		return "activity/addActivity";
//	}

	@PostMapping("/activity/insertActivity")
	public String insertActivity(@RequestParam String title, @RequestParam String text,
			@RequestParam String activityTeacher, @RequestParam String place, @RequestParam String teacherPhone,
			@RequestParam Integer applicantNumber, @RequestParam Integer applicantNumberFull,
			@RequestParam String applicantStatus, @RequestParam("applicantCreateDate") String applicantCreateDate,
			@RequestParam("applicantDeadDate") String applicantDeadDate, @RequestParam MultipartFile picture,
			HttpSession session, Model model) throws IOException, ParseException {

		//////////// 前端送進來先進行卡控判斷/////////////
		Map<String, String> errorMessage = new HashMap<>();
		Map<String, String> rememberMessage = new HashMap<>();

		////////// 正則只能填寫中文和英文///////////
		String patternOnlyStringString = "[^\\u4e00-\\u9fa5a-zA-Z]+";
		Pattern patternOnlyString = Pattern.compile(patternOnlyStringString);
		Matcher teacherMatch = patternOnlyString.matcher(activityTeacher);

		String applicantNumberFullString = Integer.toString(applicantNumberFull);

		rememberMessage.put("title", title);
		rememberMessage.put("text", text);
		rememberMessage.put("activityTeacher", activityTeacher);
		rememberMessage.put("place", place);
		rememberMessage.put("teacherPhone", teacherPhone);
		rememberMessage.put("applicantNumberFull", applicantNumberFullString);
		rememberMessage.put("applicantDeadDate", applicantDeadDate);
		model.addAttribute("rememberMessage", rememberMessage);

		if (title.trim().length() == 0) {
			errorMessage.put("title", "請勿空白");
		} else if (title.length() > 20) {
			errorMessage.put("title", "您輸入字元大於20");
			rememberMessage.put("title", "");
		}

		if (text.trim().length() == 0) {
			errorMessage.put("text", "請勿空白");
		}

		if (activityTeacher.trim().length() == 0) {
			errorMessage.put("activityTeacher", "請勿空白");
		} else if (teacherMatch.find()) {
			errorMessage.put("activityTeacher", "只能填寫文字");

		}

		if (place.trim().length() == 0) {
			errorMessage.put("place", "請勿空白");
		}
		if (applicantNumberFullString.trim().length() == 0) {
			errorMessage.put("applicantNumberFull", "請填寫人數");
		}

		/////////// 時間轉換/////////

		if (applicantDeadDate.trim().equals("Invalid date")) {
			errorMessage.put("applicantDeadDate", "請更改日期");
		}
		if (!applicantDeadDate.trim().equals("Invalid date")) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date DeadDate = format.parse(applicantDeadDate);
			String nowDateString = format.format(new Date());
			Date nowDate = format.parse(nowDateString);
			if (DeadDate.before(nowDate)) {
				errorMessage.put("applicantDeadDate", "請選擇今天之後的時間");
			}

		}

		if (teacherPhone.trim().length() == 0) {
			errorMessage.put("teacherPhone", "請輸入剛好10個號碼");
		} else if (teacherPhone.length() > 10) {
			errorMessage.put("teacherPhone", "請輸入剛好10個字元");
		} else if (teacherPhone.length() < 10) {
			errorMessage.put("teacherPhone", "請輸入剛好10個字元");
		}

		if (!errorMessage.isEmpty()) {
			model.addAttribute("errorMessage", errorMessage);
			model.addAttribute("rememberMessage", rememberMessage);

			return "activity/addActivity";
		}
		//////////// 前端送進來先進行卡控判斷/////////////
		//////////// 前端若是都正常則執行下面步驟/////////////
		Integer pid = (Integer) session.getAttribute("pid");
		HouseholdData householdData = householdService.findById(pid);
		/////////// 時間轉換/////////////////
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date applicantCreateDateFormat = formatter.parse(applicantCreateDate);
		Date applicantDeadDateFormat = formatter.parse(applicantDeadDate);

		Activity activity = new Activity(title, text, activityTeacher, place, teacherPhone, applicantNumber,
				applicantNumberFull, applicantStatus, applicantCreateDateFormat, applicantDeadDateFormat,
				householdData);

		byte[] photoByte = picture.getBytes();
		activity.setPicture(photoByte);
		activityService.activityInsert(activity);
		return "redirect:/activity/activityFindPage";
	}

	@GetMapping("activity/activityFindPage")
	public String activityFindPage(@RequestParam(name = "pageNumber", defaultValue = "1") Integer pageNumber,
			Model model, HttpServletRequest request) {
		// 判斷是否有條件搜尋
		Object searchPage = model.getAttribute("searchPage");
		String searchPageNumberString = request.getParameter("searchPageNumber");
		// 第一次條件搜尋不切頁
		if (searchPage != null) {
			model.addAttribute("searchPage", searchPage);
			return "activity/activityPage";
		}
		// 判斷是否有條件搜尋
		// 判斷是否是要切換成別頁
		if (searchPageNumberString != null) {
			System.out.println("cccccccccccccccccccccccccccc");
			Integer searchPageNumber = Integer.parseInt(searchPageNumberString);
			String keyWord = request.getParameter("keyWord");
			String startTime = request.getParameter("startTime");
			String endTime = request.getParameter("endTime");
			String activityStatusString = request.getParameter("activityStatusString");
			model.addAttribute("keyWord", keyWord);
			model.addAttribute("startTime", startTime);
			model.addAttribute("endTime", endTime);
			model.addAttribute("activityStatusString", activityStatusString);
			Page<Activity> page = activityService.searchPage(searchPageNumber, keyWord, startTime, endTime,
					activityStatusString);
			model.addAttribute("searchPage", page);
			return "activity/activityPage";
		}

		Page<Activity> page = activityService.activityFindPage(pageNumber);
		model.addAttribute("page", page);
		return "activity/activityPage";
	}

	@PostMapping("activity/activityFindPageByTimeAndString")
	public String activityFindPageByTimeAndString(@RequestParam(defaultValue = "1") Integer pageNumber,
			@RequestParam String keyWord, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") String startTime,
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") String endTime,
			@RequestParam Optional<String> activityStatus, RedirectAttributes redirectAttributes) {
		///////// 因為radio若沒勾選傳回來會有null因此需要做資料處裡
		String activityStatusString;
		if (activityStatus.isEmpty()) {
			activityStatusString = "";
		} else {
			activityStatusString = activityStatus.get();
		}
		///////// 因為radio若沒勾選傳回來會有null因此需要做資料處裡
		// 因為要保持轉到第二頁時仍然有搜尋的條件，所以條件也要一起轉發
		Page<Activity> page = activityService.searchPage(pageNumber, keyWord, startTime, endTime, activityStatusString);
		redirectAttributes.addFlashAttribute("searchPage", page);
		redirectAttributes.addFlashAttribute("keyWord", keyWord);
		redirectAttributes.addFlashAttribute("startTime", startTime);
		redirectAttributes.addFlashAttribute("endTime", endTime);
		redirectAttributes.addFlashAttribute("activityStatusString", activityStatusString);
		return "redirect:/activity/activityFindPage";
	}

	@GetMapping("activity/Information")
	public String Information(@RequestParam Integer activityId, Model model, HttpSession session) {
		Activity information = activityService.activityFindById(activityId);
		model.addAttribute("activityInformation", information);
		return "activity/activityInformation";
	}

	@ResponseBody()
	@GetMapping("activity/activityPicture")
	public ResponseEntity<byte[]> activityPicture(@RequestParam Integer activityId) {
		// ResponseEntity是Spring框架中的一個泛型類型，它表示HTTP響應的實體，可以包含HTTP響應的HTTP狀態碼、HTTP標頭以及HTTP響應的內容。
		// ResponseEntity<byte[]>表示HTTP響應的實體是一個字節數組。它通常用於返回二進制數據，例如下載文件或圖像等。
		// 使用ResponseEntity可以定制HTTP響應的HTTP狀態碼、HTTP標頭以及HTTP響應的內容。例如，可以使用ResponseEntity<byte[]>返回一個圖像文件，
		// 同時設置HTTP標頭中的Content-Type屬性，告訴瀏覽器要如何顯示這個圖像文件。
		Activity information = activityService.activityFindById(activityId);
		byte[] picture = information.getPicture();
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.IMAGE_JPEG);
		return new ResponseEntity<byte[]>(picture, httpHeaders, HttpStatus.OK);
	}

	@GetMapping("activity/update")
	public String activityUpdate(@RequestParam Integer id, Model model) {
		Activity activityInformation = activityService.activityFindById(id);
		model.addAttribute("activityInformation", activityInformation);
		return "activity/activityUpdate";
	}

	@PostMapping("activity/updateFinish")
	public String activityFinish(@RequestParam Integer activityPid, @RequestParam String title,
			@RequestParam String text, @RequestParam String activityTeacher, @RequestParam String place,
			@RequestParam String teacherPhone, @RequestParam Integer applicantNumber,
			@RequestParam Integer applicantNumberFull, @RequestParam String applicantStatus,
			@RequestParam String applicantCreateDate, @RequestParam String applicantDeadDate,
			@RequestPart MultipartFile picture, HttpSession session, Model model) throws IOException, ParseException {

		////////////前端送進來先進行卡控判斷/////////////
		Map<String, String> errorMessage = new HashMap<>();
		Map<String, String> rememberMessage = new HashMap<>();

		//////////正則只能填寫中文和英文///////////
		String patternOnlyStringString = "[^\\u4e00-\\u9fa5a-zA-Z]+";
		Pattern patternOnlyString = Pattern.compile(patternOnlyStringString);
		Matcher teacherMatch = patternOnlyString.matcher(activityTeacher);
		///////////數字轉字串////////////////
		String applicantNumberFullString = Integer.toString(applicantNumberFull);
		String activityPidString = Integer.toString(activityPid);
		String applicantNumberString = Integer.toString(applicantNumber);
		rememberMessage.put("title", title);
		rememberMessage.put("text", text);
		rememberMessage.put("activityTeacher", activityTeacher);
		rememberMessage.put("place", place);
		rememberMessage.put("teacherPhone", teacherPhone);
		rememberMessage.put("applicantNumber", applicantNumberString);
		rememberMessage.put("applicantNumberFull", applicantNumberFullString);
		rememberMessage.put("applicantStatus", applicantStatus);
		rememberMessage.put("applicantCreateDate", applicantCreateDate);
		rememberMessage.put("applicantDeadDate", applicantDeadDate);
		rememberMessage.put("activityPid", activityPidString);
		model.addAttribute("rememberMessage", rememberMessage);

		if (title.trim().length() == 0) {
			errorMessage.put("title", "請勿空白");
		} else if (title.length() > 20) {
			errorMessage.put("title", "您輸入字元大於20");
			rememberMessage.put("title", "");
		}

		if (text.trim().length() == 0) {
			errorMessage.put("text", "請勿空白");
		}

		if (activityTeacher.trim().length() == 0) {
			errorMessage.put("activityTeacher", "請勿空白");
		} else if (teacherMatch.find()) {
			errorMessage.put("activityTeacher", "只能填寫文字");

		}

		if (place.trim().length() == 0) {
			errorMessage.put("place", "請勿空白");
		}
		if (applicantNumberFullString.trim().length() == 0) {
			errorMessage.put("applicantNumberFull", "請填寫人數");
		}
		String applicantNumberCheckResult=applicantService.checkActivityStatus(activityPid,applicantNumberFull);
		if (applicantNumberCheckResult.equals("false")) {
			errorMessage.put("applicantNumberFull", "活動最大人數不得小於目前報名人數");
		}else if(applicantNumberCheckResult.equals("true")){
			applicantStatus = "未額滿";
		}else {
			applicantStatus = "已額滿";
		}

		///////////時間轉換/////////

		if (applicantDeadDate.trim().equals("Invalid date")) {
			errorMessage.put("applicantDeadDate", "請更改日期");
		}
		if (!applicantDeadDate.trim().equals("Invalid date")) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date DeadDate = format.parse(applicantDeadDate);
			String nowDateString = format.format(new Date());
			Date nowDate = format.parse(nowDateString);
			if (DeadDate.before(nowDate)) {
				errorMessage.put("applicantDeadDate", "請選擇今天之後的時間");
			}

		}

		if (teacherPhone.trim().length() == 0) {
			errorMessage.put("teacherPhone", "請輸入剛好10個號碼");
		} else if (teacherPhone.length() > 10) {
			errorMessage.put("teacherPhone", "請輸入剛好10個字元");
		} else if (teacherPhone.length() < 10) {
			errorMessage.put("teacherPhone", "請輸入剛好10個字元");
		}

		if (!errorMessage.isEmpty()) {
			model.addAttribute("errorMessage", errorMessage);
			model.addAttribute("rememberMessage", rememberMessage);

			return "activity/activityUpdate";
		}
////////////前端送進來先進行卡控判斷/////////////
////////////前端若是都正常則執行下面步驟/////////////

		byte[] photoByte = null;
		Integer memberPid = (Integer) session.getAttribute("pid");
		HouseholdData householdData = householdService.findById(memberPid);
		///////////時間//////////////////////
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date applicantCreateDateFormat = format.parse(applicantCreateDate);
		Date applicantDeadDateFormat = format.parse(applicantDeadDate);

		Activity activity = new Activity(activityPid, title, text, activityTeacher, place, teacherPhone,
				applicantNumber, applicantNumberFull, applicantStatus, applicantCreateDateFormat,
				applicantDeadDateFormat, householdData);
		/////////照片處裡//////////////
		if (picture.isEmpty()) {
			Activity activityInformation = activityService.activityFindById(activityPid);
			photoByte = activityInformation.getPicture();
		} else {
			photoByte = picture.getBytes();
		}
		activity.setPicture(photoByte);
		activityService.activityInsert(activity);
		
		
		return "redirect:/activity/activityFindPage";
	}

	@DeleteMapping("activity/activityDelete")
	public String activityDelete(@RequestParam Integer id) {
		if (applicantService.applicantNumberAll(id) > 0) {

			applicantService.applicantDeleteActivity(id);
			activityService.deleteById(id);
		} else {
			activityService.deleteById(id);
		}
		return "redirect:/activity/activityFindPage";
	}

//	每隔一段時間自動觸發的功能 5000=5秒
	@Scheduled(fixedDelay = 5000)
	public void autoUpdateStatusByDate () {
		try {
			activityService.updateApplicantStatusByTime();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
