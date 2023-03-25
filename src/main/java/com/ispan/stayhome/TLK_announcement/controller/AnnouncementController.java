package com.ispan.stayhome.TLK_announcement.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ispan.stayhome.CPC_login.model.HouseholdData;
import com.ispan.stayhome.CPC_login.service.HouseholdService;
import com.ispan.stayhome.TLK_announcement.model.Announcement;
import com.ispan.stayhome.TLK_announcement.model.AnnouncementPicture;
import com.ispan.stayhome.TLK_announcement.model.Message;
import com.ispan.stayhome.TLK_announcement.service.AnnouncementPictureService;
import com.ispan.stayhome.TLK_announcement.service.AnnouncementService;
import com.ispan.stayhome.TLK_announcement.service.MessageService;

@Controller
public class AnnouncementController {

	@Autowired
	private AnnouncementService announcementService;

	@Autowired
	private HouseholdService householdService;

	@Autowired
	private MessageService messageService;

	@Autowired
	private AnnouncementPictureService announcementPictureService;

	@GetMapping("announcement/announcementAdd")
	public String announcementAddPage() {
		return "announcement/announcementAdd";
	}

	@PostMapping("announcement/announcement")
	public String announcementAdd(@RequestParam String title, @RequestParam String text,
			@RequestParam String announcementDate, @RequestParam MultipartFile picture, HttpSession session,
			@RequestParam MultipartFile[] pictureMany, Model model) throws ParseException {
		///////////// 取會員/////////////
		Integer memberId = (Integer) session.getAttribute("pid");
		HouseholdData householdData = householdService.findById(memberId);
		/////////// 轉換時間//////////////
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date announcementDateFormat = format.parse(announcementDate);

		///////// input卡控///////////
		Map<String, String> errorMessage = new HashMap<>();
		Map<String, String> rememberMessage = new HashMap<>();
		rememberMessage.put("text", text);
		rememberMessage.put("title", title);
		rememberMessage.put("announcementDate", announcementDate);
		if (title.trim().length() == 0) {
			errorMessage.put("title", "請勿空白");
		} else if (title.length() > 20) {
			errorMessage.put("title", "您輸入字元大於20");
			rememberMessage.put("title", "");
		}

		if (text.trim().length() == 0) {
			errorMessage.put("text", "請勿空白");
		}

		if (!errorMessage.isEmpty()) {
			model.addAttribute("errorMessage", errorMessage);
			model.addAttribute("rememberMessage", rememberMessage);
			return "announcement/announcementAdd";
		}

		///////// 先儲存一次，取得儲存後的公告ID 加在所有照片後面,這樣就可以確保這張照片是唯一,照片儲存在save一次公告進行修改)
		Announcement announcement = new Announcement(householdData, title, text, announcementDateFormat);
		Announcement announcementNew = announcementService.announcementSave(announcement);
		String announcementId = announcementNew.getP_Id().toString();
		/////////// 照片單張上傳////////////////////
		String pictureName = null;
		if (!picture.isEmpty()) {
			// MultipartFile 介面中的一個方法，用於獲取上傳檔案的原始檔案名稱。
			pictureName = picture.getOriginalFilename();
			// 判斷照片名是否重複
			if (announcementService.checkPictureNameExist(announcementId + "-" + pictureName) != null) {
				errorMessage.put("pictureName", "您的照片名稱重複:\"" + announcementId + "-" + pictureName + "\"  請進行修改");
				model.addAttribute("errorMessage", errorMessage);
				model.addAttribute("rememberMessage", rememberMessage);
				return "announcement/announcementAdd";
			}
			String filderPath = "./src/main/webapp/download";
			File downloadFilder = new File(filderPath);
			String filePath = "./src/main/webapp/download/" + announcementId + "-" + pictureName;

			File downloadFile = new File(filePath);

			if (!downloadFilder.exists()) {
				downloadFilder.mkdirs();
			}
			try {
				byte[] fileContent = picture.getBytes();
				FileOutputStream fos = new FileOutputStream(downloadFile);
				fos.write(fileContent);
				fos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		// 照片有唯一識別後再修改公告新增照片名稱
		pictureName = announcementId + "-" + pictureName;
		announcementNew.setPictureName(pictureName);
		announcementService.announcementSave(announcementNew);
		///////// 多張圖片上傳處處理////////
		if (!pictureMany[0].isEmpty()) {
			for (MultipartFile relatedPictures : pictureMany) {
				String relatedPicturesName = relatedPictures.getOriginalFilename();
				///////// 建立資料夾///////////
				String relatedPicturesFilderPath = "./src/main/webapp/relatedDownload";
				File relatedPicturesDownloadFilder = new File(relatedPicturesFilderPath);
				if (!relatedPicturesDownloadFilder.exists()) {
					relatedPicturesDownloadFilder.mkdirs();
				}
				///////// 建立資料夾///////////
				String relatedPicturesNameFilePath = "./src/main/webapp/relatedDownload/" + announcementId + "-"
						+ relatedPicturesName;
				File relatedPicturesDownloadFile = new File(relatedPicturesNameFilePath);
				try {
					byte[] relatedPicturesFileContent = relatedPictures.getBytes();
					FileOutputStream fos = new FileOutputStream(relatedPicturesDownloadFile);
					fos.write(relatedPicturesFileContent);
					fos.close();
					relatedPicturesName = announcementId + "-" + relatedPicturesName;
					AnnouncementPicture announcementPicture = new AnnouncementPicture(relatedPicturesName,
							announcementNew);
					announcementPictureService.save(announcementPicture);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}
		return "redirect:/announcement/announcement";
	}

	@GetMapping("announcement/announcement")
	public String announcementPage(@RequestParam(defaultValue = "1") Integer pageNumber, Model model) {
		Page<Announcement> page = announcementService.announcementPage(pageNumber);
		model.addAttribute("page", page);
		return "announcement/announcementPage";
	}

	@GetMapping("announcement/announcement/{announcementId}")
	public String announcementInformation(@PathVariable Integer announcementId, Model model) {
		Announcement announcement = announcementService.announcementInformation(announcementId);
		model.addAttribute("announcement", announcement);
		return "announcement/announcementInformation";
	}

	@GetMapping("announcement/announcemetUpdate")
	public String announcementUpdatePage(@RequestParam Integer announcementId, Model model,
			RedirectAttributes redirectAttributes) {
		Announcement announcemet = announcementService.announcementInformation(announcementId);
		System.out.println(model.getAttribute("errorMessage"));
		if (model.getAttribute("errorMessage") != null) {
			System.out.println("ccccccccccccccccc");
			return "announcement/announcementUpdate";
		}
		model.addAttribute("announcement", announcemet);
		return "announcement/announcementUpdate";
	}

	@PutMapping("announcement/announcement/{announcementId}")
	public String announcementUpdate(@RequestParam String title, @RequestParam String text,
			@RequestParam String announcementDate, @RequestParam MultipartFile picture,
			@PathVariable Integer announcementId, @RequestParam String pictureName, HttpSession session,
			RedirectAttributes redirectAttributes, @RequestParam MultipartFile[] pictureMany, Model model)
			throws IOException, ParseException {

		String announcementIdString = announcementId.toString();
		///////////////// 找會員/////////////////
		Integer memberId = (Integer) session.getAttribute("pid");
		HouseholdData householdData = householdService.findById(memberId);
		//////////////// 找時間////////////////
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date announcementDateFormat = format.parse(announcementDate);
		/////////////// 卡控//////////
		Map<String, String> errorMessage = new HashMap<>();
		Map<String, String> rememberMessage = new HashMap<>();
		rememberMessage.put("text", text);
		rememberMessage.put("title", title);
		rememberMessage.put("announcementDate", announcementDate);
		rememberMessage.put("announcementId", announcementIdString);

		if (title.trim().length() == 0) {
			errorMessage.put("title", "請勿空白");
		} else if (title.length() > 20) {
			errorMessage.put("title", "您輸入字元大於20");
			rememberMessage.put("title", "");
		}

		if (text.trim().length() == 0) {
			errorMessage.put("text", "請勿空白");
		}

		if (!errorMessage.isEmpty()) {
			redirectAttributes.addFlashAttribute("errorMessage", errorMessage);
			redirectAttributes.addFlashAttribute("rememberMessage", rememberMessage);
			return "redirect:/announcement/announcemetUpdate?announcementId=" + announcementIdString;
		}
		//////////////// 圖檔//////////////////
		if (!picture.isEmpty()) {
			pictureName = picture.getOriginalFilename();
			// 刪除照片//
			String searchPictureName = announcementService.searchPictureName(announcementId);
			if (searchPictureName != null) {
				String filderPath = "./src/main/webapp/download/" + searchPictureName;
				File file = new File(filderPath);
				file.delete();
			}

			pictureName = announcementId + "-" + pictureName;
			String filderPath = "./src/main/webapp/download/" + pictureName;
			File file = new File(filderPath);
			byte[] fileByte = picture.getBytes();
			FileOutputStream fos = new FileOutputStream(file);
			fos.write(fileByte);
			fos.close();
		}
		Announcement announcement = new Announcement(announcementId, householdData, title, text, announcementDateFormat,
				pictureName);
		Announcement announcementNew = announcementService.announcementSave(announcement);
		//////// 多張圖片上傳處處理////////
		if (!pictureMany[0].isEmpty()) {
			/////////// 先將舊的照片全部刪除資料夾照片/////////////
			List<String> announcementReleatePicture = announcementPictureService.searchReleatePicture(announcementId);
			if (announcementReleatePicture != null) {
				for (String releatePicture : announcementReleatePicture) {
					String relatedPicturesNameFilePath = "./src/main/webapp/relatedDownload/" + releatePicture;
					File relatedPicturesDownloadFile = new File(relatedPicturesNameFilePath);
					relatedPicturesDownloadFile.delete();
				}
			}
			/////////// 先將舊的照片全部刪除(資料庫)/////////////
			announcementPictureService.deleteAll(announcementId);
			for (MultipartFile relatedPictures : pictureMany) {
				String relatedPicturesName = relatedPictures.getOriginalFilename();
				///////// 建立資料夾///////////
				String relatedPicturesFilderPath = "./src/main/webapp/relatedDownload";
				File relatedPicturesDownloadFilder = new File(relatedPicturesFilderPath);
				if (!relatedPicturesDownloadFilder.exists()) {
					relatedPicturesDownloadFilder.mkdirs();
				}
				///////// 建立資料夾///////////
				String relatedPicturesNameFilePath = "./src/main/webapp/relatedDownload/" + announcementIdString + "-"
						+ relatedPicturesName;
				File relatedPicturesDownloadFile = new File(relatedPicturesNameFilePath);
				try {
					byte[] relatedPicturesFileContent = relatedPictures.getBytes();
					FileOutputStream fos = new FileOutputStream(relatedPicturesDownloadFile);
					fos.write(relatedPicturesFileContent);
					fos.close();
					relatedPicturesName = announcementId + "-" + relatedPicturesName;
					AnnouncementPicture announcementPicture = new AnnouncementPicture(relatedPicturesName,
							announcementNew);
					announcementPictureService.save(announcementPicture);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}

		redirectAttributes.addFlashAttribute("message", "修改完畢");
		return "redirect:/announcement/announcement";
	}

	@DeleteMapping("/announcement/announcement/{announcementId}")
	public String announcementDelete(@PathVariable("announcementId") Integer announcementId,
			RedirectAttributes redirectAttributes) {
		if (messageService.messageNumberAll(announcementId) > 0) {
			messageService.messageDeleteAnnouncement(announcementId);
			// 先刪除照片再將資料刪除//
			// 標題照片
			String searchPictureName = announcementService.searchPictureName(announcementId);
			String filderPath = "./src/main/webapp/download/" + searchPictureName;
			File file = new File(filderPath);
			file.delete();
			// 標題照片
			// 相關照片
			List<String> announcementReleatePicture = announcementPictureService.searchReleatePicture(announcementId);
			if (announcementReleatePicture != null) {
				for (String releatePicture : announcementReleatePicture) {
					String relatedPicturesNameFilePath = "./src/main/webapp/relatedDownload/" + releatePicture;
					File relatedPicturesDownloadFile = new File(relatedPicturesNameFilePath);
					relatedPicturesDownloadFile.delete();
				}
			}
			// 相關照片
			// 先刪除照片再將資料刪除//
			announcementPictureService.deleteAll(announcementId);
			announcementService.announcementDelete(announcementId);
		} else {
			// 先刪除照片再將資料刪除//
			// 標題照片
			String searchPictureName = announcementService.searchPictureName(announcementId);
			String filderPath = "./src/main/webapp/download/" + searchPictureName;
			File file = new File(filderPath);
			file.delete();
			// 標題照片
			// 相關照片
			List<String> announcementReleatePicture = announcementPictureService.searchReleatePicture(announcementId);
			if (announcementReleatePicture != null) {
				for (String releatePicture : announcementReleatePicture) {
					String relatedPicturesNameFilePath = "./src/main/webapp/relatedDownload/" + releatePicture;
					File relatedPicturesDownloadFile = new File(relatedPicturesNameFilePath);
					relatedPicturesDownloadFile.delete();
				}
			}
			// 相關照片
			// 先刪除照片再將資料刪除//
			announcementPictureService.deleteAll(announcementId);
			announcementService.announcementDelete(announcementId);
		}
		redirectAttributes.addFlashAttribute("message", "刪除成功");
		return "redirect:/announcement/announcement";
	}

	@GetMapping("announcement/announcementRead")
	public String announcementRead(@RequestParam(defaultValue = "1") Integer pageNumber, Model model) {
		Page<Announcement> page = announcementService.announcementPage(pageNumber);
		model.addAttribute("page", page);
		return "announcement/announcementHouseholdPage";
	}

//	@GetMapping("announcement/announcementCondition")
//	public String announcementCondition(@RequestParam(defaultValue = "1")Integer pageNumber,@RequestParam String keyWord,@RequestParam String startTime,@RequestParam String endTime ,@RequestParam Integer lower) {
//		
//		System.out.println(lower.toString());
//		
//		if(lower == 4) {
//			System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaa");
//			Page<Announcement> page=announcementService.searchMessageCountNoMessage(pageNumber, startTime, endTime, keyWord);
//			System.out.println(page);
//			return "redirect:/announcement/announcementRead";
//		}
//		
//		Page<Announcement> page=announcementService.searchCondition(pageNumber, startTime, endTime, keyWord, lower);
//		System.out.println(page);
//		return "redirect:/announcement/announcementRead";
//	}

	@GetMapping("announcement/announcementRead/{announcementId}")
	public String announcementReadInformation(@PathVariable Integer announcementId,
			@RequestParam(defaultValue = "1") Integer pageNumber, Model model, HttpSession session) {
		Announcement announcement = announcementService.announcementInformation(announcementId);
		model.addAttribute("announcement", announcement);
		////////////////////// 訊息頁面///////////////////////
		Page<Message> page = messageService.messagePageByOne(pageNumber, announcementId);
		model.addAttribute("page", page);
		return "announcement/announcementHouseholdInformation";
	}

	@GetMapping("announcement/sendEmail")
	public String sendEmail(@RequestParam Integer announcementId) {
		announcementService.sendEmail(announcementId);
		return "redirect:/announcement/announcement";
	}
}
