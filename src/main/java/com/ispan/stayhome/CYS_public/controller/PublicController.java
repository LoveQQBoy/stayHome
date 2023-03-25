package com.ispan.stayhome.CYS_public.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ispan.stayhome.CYS_public.model.CreatePublic;
import com.ispan.stayhome.CYS_public.model.PublicAppointment;
import com.ispan.stayhome.CYS_public.service.CreatePublicService;
import com.ispan.stayhome.CYS_public.service.PublicAppointmentService;
import com.ispan.stayhome.CYS_repair.model.HouseholdData;
import com.ispan.stayhome.CYS_repair.sevice.HouseholdDataService;

@Controller
@SessionAttributes({"pid", "publicDate", "date", "apState", "publicName", "pageNumberS", 
	"publicState"})
public class PublicController {

	private CreatePublicService createPublicService;
	private PublicAppointmentService publicAppointmentService;
	private HouseholdDataService householdDataService;
	private ServletContext servletContext;

//	@Autowired
	public PublicController(CreatePublicService createPublicService, PublicAppointmentService publicAppointmentService,
			HouseholdDataService householdDataService, ServletContext servletContext) {
		super();
		this.createPublicService = createPublicService;
		this.publicAppointmentService = publicAppointmentService;
		this.householdDataService = householdDataService;
		this.servletContext = servletContext;
	}

	// 後台建立公設
	@GetMapping("/public/createPublic")
	public String createPublic(Model model) {
		CreatePublic createPublic = new CreatePublic();

		model.addAttribute("createPublic", createPublic);

		return "public/createPublic";
	}

	// 送出後台建立的公設
	@PostMapping("/public/createPublic")
	public String postCreatePublic(@ModelAttribute CreatePublic cp,
//			@RequestParam(value="appointmentDateLimit") @DateTimeFormat(pattern = "yyyy/MM/dd") Date appointmentDateLimit,  
			RedirectAttributes redirectAttributes, Model model) {

		int interMinute = cp.getInterMinute();
		String openingHour = cp.getOpeningHour();
		String closingHour = cp.getClosingHour();
		Date o = convertString2Date("HH:mm", openingHour);
		Date c = convertString2Date("HH:mm", closingHour);

//		Date o = cp.getOpeningHour();		
//		Date c = cp.getClosingHour();

		if (!IsIntervalTimeOk(o, c, interMinute)) {
			model.addAttribute("intervalFail", "不能完整分配時間");
			return "public/createPublic";
		}

		String str = getIntervalTimeString(o, c, interMinute);
		cp.setInterTimes(str);
		// 存入照片
		MultipartFile mPhoto = cp.getM_publicPhoto();

		if (mPhoto != null && !mPhoto.isEmpty()) { // 不加!mPhoto.isEmpty()，資料庫會有0x寫入
			byte[] photoByte;

			try {
				photoByte = mPhoto.getBytes();
				cp.setPublicPhoto(photoByte);
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException("檔案上傳發生異常: " + e.getMessage());
			}
//			cp.setPublicPhoto(photoByte);
		}
		
		if(cp.getId()!=null) {
			cp.setDate((Date)model.getAttribute("publicDate")); 
			redirectAttributes.addFlashAttribute("insertSuccess", "修改成功");
		}else{
			redirectAttributes.addFlashAttribute("insertSuccess", "新增成功");
		}
		
		createPublicService.save(cp);			
		return "redirect:/public/publicList";
	}
	
		
	// 後台的公設列表+分頁
	@GetMapping("/public/publicList")
	public String publicList(Model model,
		@RequestParam(name="p",defaultValue = "1") Integer pageNumber,
		@RequestParam(defaultValue = "") String publicState,
		@RequestParam(defaultValue = "") String publicName) {
			
		Page<CreatePublic> page = 
				createPublicService.publicListByPage(publicState,publicName,pageNumber);

		model.addAttribute("page", page);
		String pageNumberS = String.valueOf(pageNumber);	
		model.addAttribute("pageNumberS", pageNumberS);
		model.addAttribute("publicState", publicState);
		model.addAttribute("publicName", publicName);

		return "public/publicList";
	}

	// 後台修改公設(包含刪除公設)
	@GetMapping("/public/modify/{publicId}")
	public String modifyPublic(Model model, @PathVariable Integer publicId) {

		CreatePublic createPublic = createPublicService.findById(publicId);
				
		model.addAttribute("createPublic", createPublic);
		model.addAttribute("publicDate", createPublic.getDate());

		return "public/createPublic";
	}
	
	
	//取公設之圖
	@GetMapping("/publicPicture/{id}")
	public ResponseEntity<byte[]> publicPicture(@PathVariable Integer id){
		
		CreatePublic cp = createPublicService.findById(id);
		 
		 byte[] publicPhoto = cp.getPublicPhoto();
		 
		 if(publicPhoto != null) {			 
			 return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(publicPhoto);
		 }else {			 
			 byte[] publicNoPhoto = toByteArray("/download/NoImage.jpg");		 
			 return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(publicNoPhoto);
		 }
		
	}


	// 送出後台刪除公設
	@GetMapping("/public/delete/{publicId}")
//	@DeleteMapping("/public/delete/${publicId}")	
	public String deletePublic(RedirectAttributes redirectAttributes, Model model,
			@PathVariable Integer publicId) {
		CreatePublic cp = createPublicService.findById(publicId);		
		cp.setState("2");		
		createPublicService.save(cp);

		redirectAttributes.addFlashAttribute("deleteSuccess", "刪除成功");
		
		String pageNumberS = (String)model.getAttribute("pageNumberS");
		String publicState = (String)model.getAttribute("publicState");
		String publicName = (String)model.getAttribute("publicName");
		
		String publicName_="";
		try {
			publicName_ = URLEncoder.encode(publicName, StandardCharsets.UTF_8.toString());
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "redirect:/public/publicList?p="+pageNumberS+"&publicState="+publicState
				+"&publicName="+publicName_;
	}

	
	// 後台的預約公設列表+分頁
	@GetMapping("/public/appointmentListBack")
	public String appointmentListBack(Model model,
		@RequestParam(name="p",defaultValue = "1") Integer pageNumber,
		@RequestParam(defaultValue = "") String date, 
		@RequestParam(defaultValue = "") String apState,
		@RequestParam(defaultValue = "") String publicName,
		@RequestParam(defaultValue = "") String name,
		@RequestParam(defaultValue = "") String phone
		) {
		publicAppointmentService.transStateAppos();
				
		Page<PublicAppointment> page = 
				publicAppointmentService.backListByPage(date, apState, publicName, 
						name, phone, pageNumber);
	
		model.addAttribute("page", page);
		String pageNumberS = String.valueOf(pageNumber);	
		model.addAttribute("pageNumberS", pageNumberS);
		model.addAttribute("date", date);
		model.addAttribute("apState", apState);
		model.addAttribute("publicName", publicName);
		model.addAttribute("name", name);
		model.addAttribute("phone", phone);

		return "public/appointmentListBack";
	}
	
	
	// 用戶預約公設
	@GetMapping("/public/appointmentPublic")
	public String appointmentPublic(Model model) {		
		PublicAppointment pa = new PublicAppointment();

		model.addAttribute("publicAppointment", pa);

//		預約公設名稱已改成@ModelAttribute(下方)

		return "public/appointmentPublic";
	}
	
	
	// 送出預約公設表單
	@PostMapping("/public/appointmentPublic")
	public String postAppointmentPublic(@RequestParam("appointmentTime") String at,
			@ModelAttribute("publicAppointment") PublicAppointment pa, Model model,
			RedirectAttributes redirectAttributes) {
		Integer pid = (Integer)model.getAttribute("pid");
		HouseholdData hd = householdDataService.findById(pid);
		
		pa.setResident(hd);
		pa.setAppointmentTime(at);
		pa.setAppointmentState("已預約");		
		publicAppointmentService.add(pa);
		
		if(pa.getId()!=null) {
			redirectAttributes.addFlashAttribute("insertSuccess","修改成功");
		}else {
			redirectAttributes.addFlashAttribute("insertSuccess","新增成功");			
		}
		return "redirect:/public/appointmentList";
	}
	
	
	// 用ajax找公設的預約日期限制、可預約時段
	@ResponseBody
	@GetMapping("/public/appointmentLimit")
	public Map<String, Object> appointmentDateLimit(@RequestParam Integer publicId, Model model) {

		CreatePublic cp = createPublicService.findById(publicId);
		Date adl = cp.getAppointmentDateLimit();		
		String daysOfWeek = cp.getDaysOfWeek();
		List<String> publicTimes = publicAppointmentService.publicTimes(publicId);
		
		Map<String, Object> map = new HashMap<>();		
		map.put("dateLimit",adl);
		map.put("daysOfWeek",daysOfWeek);
		map.put("times",publicTimes);
		
		model.addAttribute("times", publicTimes);
		
		return map;
	}
	
	
	// 用ajax找公設可預約人數
	@ResponseBody
	@GetMapping("/public/okAppointmentNumber")
	public Integer okAppointmentNumber(@RequestParam Integer publicId,
			@RequestParam("appointmentTime") String at) {

		Integer okNumber = 
				publicAppointmentService.okAppointmentNumber(publicId, at);
		
		return okNumber;
	}
	
	
	// 住戶的預約公設列表+分頁
	@GetMapping("/public/appointmentList")
	public String appointmentList(Model model, 
			@RequestParam(name="p",defaultValue = "1") Integer pageNumber,
			@RequestParam(defaultValue = "") String date, 
			@RequestParam(defaultValue = "") String apState,
			@RequestParam(defaultValue = "") String publicName) {
		publicAppointmentService.transStateAppos();
		
		Integer pid = (Integer)model.getAttribute("pid");
		
		Page<PublicAppointment> page = 
				publicAppointmentService.userListAndPublicState1ByPage(pid, date, apState,
						publicName, pageNumber);

		model.addAttribute("page", page);
		String pageNumberS = String.valueOf(pageNumber);	
		model.addAttribute("pageNumberS", pageNumberS);
		model.addAttribute("date", date);
		model.addAttribute("apState", apState);
		model.addAttribute("publicName", publicName);
		
		return "public/appointmentList";
	}
	
	
	
	//用戶修改預約公設之表格
	@GetMapping("/publicappointment/modify/{appoId}")
	public String modifyAppointment(Model model, @PathVariable Integer appoId) {

		PublicAppointment pa = publicAppointmentService.findById(appoId);
			
		model.addAttribute("publicAppointment", pa);
		
		return "public/appointmentPublic";
	}
	
	
	// 取消預約公設
	@GetMapping("/public/appointmentCancel/{appointmentId}")
	public String appointmentCancel(@PathVariable("appointmentId") Integer appoId,
			Model model) {
		PublicAppointment pa = publicAppointmentService.findById(appoId);
		
		pa.setAppointmentState("取消預約");
		
		publicAppointmentService.save(pa);
		
		String pageNumberS = (String)model.getAttribute("pageNumberS");
		String date = (String)model.getAttribute("date");
		String apState = (String)model.getAttribute("apState");
		String publicName = (String)model.getAttribute("publicName");
		
		
		String apState_="";
		String publicName_="";
		try {
			apState_ = URLEncoder.encode(apState, StandardCharsets.UTF_8.toString());
			publicName_ = URLEncoder.encode(publicName, StandardCharsets.UTF_8.toString());
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "redirect:/public/appointmentList?p="+pageNumberS+"&date="+date
				+ "&apState="+apState_+"&publicName="+publicName_;
	}
			
	
	//用戶預約公設的名稱選單(給@GetMapping("/public/appointmentPublic"使用)
	@ModelAttribute("publicsByState1")
	public Map<Integer, String> publicByState1() {
		Map<Integer, String> publicMap = new HashMap<>();
		List<CreatePublic> list = createPublicService.publicsByState("1");
		for (CreatePublic cp : list) {
			publicMap.put(cp.getId(), cp.getPublicName());
		}
		return publicMap;
	}
	
	
	@InitBinder
	public void initBinder(WebDataBinder binder, WebRequest request) {
		// java.util.Date
//		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		dateFormat.setLenient(false);
//		CustomDateEditor ce = new CustomDateEditor(dateFormat, true); 
//		binder.registerCustomEditor(Date.class, ce);

		// java.sql.Date
//		DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
//		dateFormat2.setLenient(false);
//		CustomDateEditor ce2 = new CustomDateEditor(dateFormat2, true); 
//		binder.registerCustomEditor(java.sql.Date.class, ce2);

		DateFormat dateFormat3 = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat3.setLenient(false);
		CustomDateEditor ce3 = new CustomDateEditor(dateFormat3, true);
		binder.registerCustomEditor(Date.class, ce3);

	}

	// 判斷時段間格是否ok，且同時間的會擋掉(false)
	public boolean IsIntervalTimeOk(Date start, Date end, int interval) {
		boolean flag = false;

		if (start.getTime() < end.getTime()) {
			long inter = end.getTime() - start.getTime();
			double inter_d = (double) Math.abs(inter); // 換成double，%才有餘數
			if (inter_d % (interval * 60 * 1000) == 0) {
				flag = true;
			}
		} else if (start.getTime() > end.getTime()) {
			Date m = convertString2Date("HH:mm", "24:0");
			Date o = convertString2Date("HH:mm", "0:0");
			long inter = (m.getTime() - start.getTime()) + (end.getTime() - o.getTime());
			double inter_d = (double) Math.abs(inter);
			if (inter_d % (interval * 60 * 1000) == 0) {
				flag = true;
			}
		}
		return flag;
	}

	// 將時間切出時段(限制輸入00~23)
	public String getIntervalTimeString(Date start, Date end, int interval) {
		String str = "";
		Calendar calendar = Calendar.getInstance();

		if (start.getTime() < end.getTime()) {
			while (true) {
				str = str + convertDate2String("HH:mm", start) + ","; // 加進字串
//					Calendar calendar = Calendar.getInstance();
				calendar.setTime(start);
				calendar.add(Calendar.MINUTE, interval); // 加間格時間

				if (calendar.getTime().getTime() == end.getTime()) {
					str = str + convertDate2String("HH:mm", end) + ",";
					break;
				}
				start = calendar.getTime();
			}
		} else if (start.getTime() > end.getTime()) {
			Date m = convertString2Date("HH:mm", "24:0");
			Date o = convertString2Date("HH:mm", "0:0");
			long inter = 0;

			// start到24
			while (true) {
				str = str + convertDate2String("HH:mm", start) + ",";
//					Calendar calendar = Calendar.getInstance();
				calendar.setTime(start);
				calendar.add(Calendar.MINUTE, interval);
				if (calendar.getTime().getTime() == m.getTime()) {
					break;
				} else if (calendar.getTime().getTime() > m.getTime()) {
					inter = calendar.getTime().getTime() - m.getTime();
					break;
				}
				start = calendar.getTime();
			}

			// 00到end
			if (end.getTime() != o.getTime()) {
				calendar.setTime(o);
				calendar.add(Calendar.MILLISECOND, (int) inter);
				start = calendar.getTime();

				while (start.getTime() <= end.getTime()) {
					str = str + convertDate2String("HH:mm", start) + ","; // 加進字串
					// calendar = Calendar.getInstance();
					calendar.setTime(start);
					calendar.add(Calendar.MINUTE, interval); // 加間格時間
					start = calendar.getTime();
				}
			} else {
				str = str + convertDate2String("HH:mm", o) + ",";
			}
		}
		String strSub = str.substring(0, str.length() - 1);
		
		str ="";
		String[] timesArray = strSub.split(",");		
		for(int i = 0;i<timesArray.length-1;i++) {
			str += timesArray[i] +"-"+ timesArray[i+1] + ",";
 		}
		strSub = str.substring(0, str.length() - 1);
		
		return strSub;
	}

	// string轉日期
	public Date convertString2Date(String format, String dateStr) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		try {
			Date date = simpleDateFormat.parse(dateStr);
			return date;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	// 日期轉string
	public String convertDate2String(String format, Date date) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		return simpleDateFormat.format(date);
	}
	
	//字串圖檔路徑轉byte[]
	private byte[] toByteArray(String filepath) {
	    byte[] b = null;
	    String realPath = servletContext.getRealPath(filepath);
	    System.out.println("realPath=" + realPath);
	    
	    try {
	        File file = new File(realPath);
	        long size = file.length();
	        b = new byte[(int) size];
	        InputStream fis = servletContext.getResourceAsStream(filepath);
	        fis.read(b);
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return b;
	}


	
//多個redirectAttributes	
//	Map<String, ?> flashAttributes = redirectAttributes.getFlashAttributes();
//	if (flashAttributes.containsKey("message")) {
//	        String message = (String) flashAttributes.get("message");
//	        
//	}
//	if (!flashAttributes.isEmpty())
	
}
