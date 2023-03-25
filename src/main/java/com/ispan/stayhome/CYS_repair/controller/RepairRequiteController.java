package com.ispan.stayhome.CYS_repair.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.regex.Pattern;

import javax.servlet.ServletContext;

import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ispan.stayhome.CYS_repair.model.HouseholdData;
import com.ispan.stayhome.CYS_repair.model.RepairRequite;
import com.ispan.stayhome.CYS_repair.sevice.HouseholdDataService;
import com.ispan.stayhome.CYS_repair.sevice.RepairRequiteService;

@Controller
@SessionAttributes({"pid","pageNumberS","date","replyState","repairName","phone"})
//@Validated
public class RepairRequiteController {
		
	private RepairRequiteService repairRequiteService;		
	private HouseholdDataService householdDataService;	
	private ServletContext servletContext;
	
//	@Autowired
	public RepairRequiteController(RepairRequiteService repairRequiteService, HouseholdDataService householdDataService,
			ServletContext servletContext) {		
		this.repairRequiteService = repairRequiteService;
		this.householdDataService = householdDataService;
		this.servletContext = servletContext;
	}
	
	
	//用戶的維修列表+分頁	
	@GetMapping("/repair/residentList")	
	public String repairResidentList(
			@RequestParam(name="p",defaultValue = "1") Integer pageNumber,
			@RequestParam(defaultValue = "") String date,
			@RequestParam(defaultValue = "") String replyState, Model model) {
		
		Integer pid = (Integer)model.getAttribute("pid");
				
		Page<RepairRequite> page = repairRequiteService.residentListByPage(pid, date, 
				replyState, pageNumber);
				
		model.addAttribute("page", page);
		model.addAttribute("date", date);
		model.addAttribute("replyState", replyState);		
					
		return "repair/residentList";
	}
	
	
	
	//用戶詳細資料
	@GetMapping("/repair/{id}")
	public String repairDataForResident(@PathVariable("id") Integer id, Model model) {
				
		RepairRequite repair = repairRequiteService.findById(id);
		
		model.addAttribute("repair", repair);
		
		return "repair/repairData";
	}
	
	
	//取維修之圖
	@GetMapping("/repairPicture/{id}")
	public ResponseEntity<byte[]> repairPicture(@PathVariable Integer id){
		
		 RepairRequite repair = repairRequiteService.findById(id);
		 
		 byte[] repairPhoto = repair.getRepairPhoto();
		 
		 if(repairPhoto != null) {			 
			 return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(repairPhoto);
		 }else {			 
			 byte[] repairNoPhoto = toByteArray("/download/NoImage.jpg");		 
			 return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(repairNoPhoto);
		 }
		 
//天恩的範例
//		 if(repairPhoto != null) {
//			 HousePhoto photo = optional.get();
//			 byte[] photoFile = photo.getPhotoFile();
//			 return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(photoFile);
//		 }		 
//		 return null;
		
	}
	
	
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
	
		
	//用戶通報維修
	@GetMapping("/repair/report")
	public String residentReport(Model model) {
				
		RepairRequite repair = new RepairRequite();
		
		model.addAttribute("repairForReport" , repair);
					
		return "repair/report";
	}
		
	
	
	//送出用戶通報維修+存圖
	@PostMapping("/repair/report")		
	public String postResidentReport(@ModelAttribute("repairForReport") RepairRequite repair, 
			Model model, RedirectAttributes redirectAttributes) {  //@Validated、BindingResult result
		
//		if (result.hasErrors()) {
//			String errorMessage = result.getFieldError("description").getDefaultMessage();
//			redirectAttributes.addFlashAttribute("errorMessage", errorMessage);
//			return "repair/NewFile";
//			throw new BindException(result);
//		}
		
		String description = repair.getDescription();
		
		if(Pattern.matches("\\s+", description)) {
						
			redirectAttributes.addFlashAttribute("notSpace", "不可空格");
						
			return "redirect:/repair/report";
		}
				
		repair.setReplyState("0");
		
		HouseholdData householdData = 
				householdDataService.findById((Integer)model.getAttribute("pid"));
		
		repair.setResident(householdData);
				
		MultipartFile mPhoto = repair.getM_repairPhoto();	
		
		if(mPhoto != null && !mPhoto.isEmpty() ) {    //不加!mPhoto.isEmpty()，資料庫會有0x寫入
			byte[] photoByte;
			
			try {
				photoByte = mPhoto.getBytes();
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException("檔案上傳發生異常: " + e.getMessage());
			}	
			
			repair.setRepairPhoto(photoByte);
		}
								
		repairRequiteService.save(repair);
		redirectAttributes.addFlashAttribute("insertSuccess", "新增成功");
    
	
		return "redirect:/repair/residentList";
	}
			
	
//	@ModelAttribute("repairForResident")
//	public RepairRequite setRepairForResidentModel(Model model) {
//		
//		RepairRequite repair = new RepairRequite();
//		repair.setReplyState("0");
//		
//		HouseholdData householdData = 
//				householdDataService.findById((Integer)model.getAttribute("pid"));
//		
//		repair.setResident(householdData);
//									
//		return repair;
//	}
	
			
	
	
	
	
	
	//後台的維修列表+分頁	
	@GetMapping("/repair/backList")	
	public String repairBackList(
			@RequestParam(name="p",defaultValue = "1") Integer pageNumber,
			@RequestParam(defaultValue = "") String date,
			@RequestParam(defaultValue = "") String replyState, 
			@RequestParam(defaultValue = "") String repairName, 
			@RequestParam(defaultValue = "") String phone, Model model) {				
				
		Page<RepairRequite> page = repairRequiteService.backListByPage(date, 
				replyState, repairName, phone, pageNumber);
		
		String pageNumberS = String.valueOf(pageNumber);
		model.addAttribute("page", page);		
		model.addAttribute("pageNumberS", pageNumberS);
		model.addAttribute("date", date);
		model.addAttribute("replyState", replyState);		
		model.addAttribute("repairName", repairName);		
		model.addAttribute("phone", phone);		
					
		return "repair/backList";
	}
	
	
	//後台維修回報
	@GetMapping("/repair/reply/{id}")
	public String repairReply(@PathVariable Integer id, Model model) {
						
		RepairRequite repair = repairRequiteService.findById(id);
		
		String reply = repair.getReply();
		
		if(reply != null) {									
			return "redirect:/repair/backList";
		}
		
		model.addAttribute("repair", repair);
				
		return "repair/reply";
	}
	
	
	//送出後台維修回報
	@PostMapping("/repair/reply/{id}")
	public String postRepairReply(@RequestParam("reply") String reply, 
			@PathVariable Integer id, RedirectAttributes redirectAttributes, Model model) throws UnsupportedEncodingException {					

		if(Pattern.matches("\\s+", reply)) {
						
			redirectAttributes.addFlashAttribute("notSpace", "不可空格");
						
			return "redirect:/repair/reply/" + id;
		}				

		RepairRequite repair = repairRequiteService.findById(id);
		
		repair.setReplyTime(new Date());
		
		repair.setReply(reply);
		
		repair.setReplyState("1");
		
//		if(reply != null && !reply.isEmpty() && !Pattern.matches("\\s+", reply)) {
//			repair.setReplyState("1");
//		}else {
//			repair.setReplyState("0");
//		}
		
		repairRequiteService.save(repair);
		redirectAttributes.addFlashAttribute("replySuccess", "回覆成功");
		
		String pageNumberS = (String)model.getAttribute("pageNumberS");
		String date = (String)model.getAttribute("date");
		String replyState = (String)model.getAttribute("replyState");
		String repairName = (String)model.getAttribute("repairName");
		String phone = (String)model.getAttribute("phone");
		
//		String replyState_ = URLEncoder.encode(replyState, StandardCharsets.UTF_8.toString());
		
		return "redirect:/repair/backList?p=" + pageNumberS + "&date=" + date + "&replyState=" + replyState + 
				"&repairName=" + repairName + "&phone=" + phone;
	}
	
	
	
}
