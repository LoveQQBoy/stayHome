package com.ispan.stayhome.HYL_collectParcel.controller;



import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ispan.stayhome.HYL_collectParcel.model.CollectParcel;
import com.ispan.stayhome.HYL_collectParcel.model.PackHouseholdData;
import com.ispan.stayhome.HYL_collectParcel.service.CollectParcelService;




@Controller
@RequestMapping("/collectParcel")
public class CollectParcelController {

	@Autowired
	private CollectParcelService collectParcelService;
	

//	// 後台頁面
//
//	@GetMapping("/backendDemo")
//	public String Backenddemo(@RequestParam(name="p",defaultValue = "1") Integer pageNumber,@RequestParam(name="pickup",required = false)Integer pickup, Model model) {
//		Page<CollectParcel> page = collectParcelService.getPage(pageNumber, pickup);
//		model.addAttribute("page", page);
//		return "/collectParcel/backendDemo";
//	}

//	// 後台頁面條件查詢表單提交
//	@PostMapping("/parcel-form")
//	public String submitBackendForm(@RequestParam("parcelname") String parcelname,
//			@RequestParam("recipientname") String recipientname,Model model) {
//		if (parcelname == "" && recipientname == "") {
//			List<CollectParcel> ctps = collectParcelService.getAllCollectParcel();
//			model.addAttribute("ctps", ctps);
//		} else if (parcelname != "" && recipientname == "") {
//			List<CollectParcel> bpn = collectParcelService.getByParcelname(parcelname);
//			model.addAttribute("ctps", bpn);
//		} else if (parcelname == "" && recipientname != "") {
//
//			List<CollectParcel> brn = collectParcelService.getByRecipientname(recipientname);
//			model.addAttribute("ctps", brn);
//		} else {
//			List<CollectParcel> bpnarn = collectParcelService.getByParcelnameAndRecipientname(parcelname,
//					recipientname);
//			model.addAttribute("ctps", bpnarn);
//		}
//		return "/collectParcel/backendDemo";
//	}
	
	// 後台頁面條件查詢表單提交
		@GetMapping("/backendDemo")
		public String submitBackendForm(@RequestParam(name="parcelname",defaultValue = "") String parcelname,
										@RequestParam(name="p",defaultValue = "1") Integer pageNumber,
										@RequestParam(name="state",defaultValue = "未選擇") String state,
										Model model) {
			if (parcelname == "") {
				Page<CollectParcel> page = collectParcelService.getPage(pageNumber, state);
				model.addAttribute("page", page);
				model.addAttribute("state", state);
			}else if (parcelname != ""){
				Page<CollectParcel> gpbpn = collectParcelService.getPageByParcelname(pageNumber, state, parcelname);
				model.addAttribute("state", state);
				model.addAttribute("parcelname", parcelname);
				model.addAttribute("page", gpbpn); 
			
			}
			return "collectParcel/backendDemo";
		}

	// 後台刪除包裹
	@DeleteMapping("/deleteParcel")
	public String deleteParcel(@RequestParam Integer id) {
		collectParcelService.deleteParcel(id);

		return "redirect:/collectParcel/backendDemo";
	}

	// 後台進入修改包裹頁面的前置作業
	@GetMapping("/updatebeforepage")
	public String updateBeforePage(@RequestParam Integer id, Model model) {
		CollectParcel collectparcel = collectParcelService.findById(id);
		
		model.addAttribute("collectParcel", collectparcel);
		
		return "/collectParcel/updateParcel";
	}

	 //後台修改包裹
	@PutMapping("/updateParcel")
	public String updateParcel(@RequestParam Integer id,@ModelAttribute CollectParcel ctp,@RequestParam(name="p",defaultValue = "1") Integer pageNumber,@RequestParam(name="state", defaultValue = "未選擇")String state, Model model) {
		collectParcelService.updateCollectParcel(id, ctp);
		Page<CollectParcel> page = collectParcelService.getPage(pageNumber,state);
		model.addAttribute("page", page);
		return "redirect:/collectParcel/backendDemo";
	}

	// 後台新增包裹頁面

	@GetMapping("/createParcel")
	public String createParcel() {
		return "/collectParcel/createParcel";
	}

	// 後台新增包裹頁面from表單提交

	@PostMapping("/add-form")
	public String addCollectParcel(@ModelAttribute CollectParcel ctp, 
			@RequestParam(name="recipientname") String userName,
			@RequestParam(name="page",defaultValue = "1") Integer pageNumber,
			@RequestParam(name="state", defaultValue = "未選擇")String state,
			Model model) {
		
		PackHouseholdData phUser = collectParcelService.findHouseholdByName(userName);
		
		if(phUser == null) 
			return "redirect:/collectParcel/createParcel";
		
		ctp.setPackHouseholdData(phUser);
		collectParcelService.addCollectParcel(ctp);
		
		Page<CollectParcel> page = collectParcelService.getPage(pageNumber,state);
		model.addAttribute("page", page);
		return "redirect:/collectParcel/backendDemo";
	}

	// 住戶頁面

//	@GetMapping("/userDemo")
//	public String userdemo(@RequestParam(name="p",defaultValue = "1") Integer pageNumber,@RequestParam(name="state", required = false)Integer state,HttpSession hs, Model model) {
//		Integer userId = (Integer) hs.getAttribute("pid");
//		Page<CollectParcel> page = collectParcelService.getPageByUser(pageNumber, userId,state);
//		model.addAttribute("page", page);
//		return "/collectParcel/userDemo";
//	}
	
	// 住戶頁面表單提交
		@GetMapping("/userDemo")
		public String submitUserForm(@RequestParam(name="p",defaultValue = "1") Integer pageNumber,
									@RequestParam(name="state",defaultValue = "未選擇")String state,
									HttpSession hs,
									Model model) {
			Integer userId = (Integer) hs.getAttribute("pid");
			Page<CollectParcel> page = collectParcelService.getPageByUser(pageNumber,userId,state);
			model.addAttribute("page", page);
			model.addAttribute("state", state);			
			return "collectParcel/userDemo";
		}
		
//		//住戶拒收
//		@GetMapping("/refuseToAccept")
//		public String refuseToAccept(@RequestParam Integer id,@RequestParam(name="p",defaultValue = "1") Integer pageNumber,
//				@RequestParam(name="state",defaultValue = "未選擇")String state,@ModelAttribute CollectParcel ctp,
//				HttpSession hs,
//				Model model) {
//			
//			Integer userId = (Integer) hs.getAttribute("pid");
//			Page<CollectParcel> page = collectParcelService.getPageByUser(pageNumber,userId,state);
//			model.addAttribute("page", page);
//			model.addAttribute("state", state);	
//			collectParcelService.changetorefuse(id ,ctp);
//			return "collectParcel/userDemo";
//		}

		
//		@GetMapping("/collectParcel/getById/{id}")
//		public CollectParcel getCollectParcelById(@PathVariable int id) {
//			return collectParcelService.getCollectParcelById(id);
//		}

//	//後台分頁顯示資料
//		
//		@PostMapping("/packPage")
//		public String showParceleByPage(@RequestParam(name="p",defaultValue = "1") Integer pageNumber,
//										@RequestParam(name="state", required = false)Integer state,
//										Model model) {
//			Page<CollectParcel> page = collectParcelService.getPage(pageNumber,state);
//			model.addAttribute("page", page);
//			return "/collectParcel/backendDemo";
//		}
		
		
//	// 查詢全部包裹 postman測試ok
//	@ResponseBody
//	@GetMapping("/collectParcel/getAll")
//	public List<CollectParcel> getAllCollectParcel(Model model) {
//		List<CollectParcel> ctps = collectParcelService.getAllCollectParcel();
//
//		model.addAttribute("ctps", ctps);
//		return ctps;
//	}
//
//	// 用id查詢包裹 postman測試ok
//	@ResponseBody
//	@GetMapping("/collectParcel/getById/{id}")
//	public CollectParcel getCollectParcelById(@PathVariable int id) {
//		return collectParcelService.getCollectParcelById(id);
//	}

	// 依照id刪除包裹 postman測試ok
//    @ResponseBody
//    @DeleteMapping("/collectParcel/deleteById/{id}") 
//    public String deleteCollectParcel(@PathVariable int id) {
//        
//        try {
//        	collectParcelService.deleteCollectParcel(id);
//			return "刪除"+id+"已成功";
//		}catch(EmptyResultDataAccessException e){
//			return "沒有這筆資料";
//		}
//    }

//	// 新增包裹 postman測試ok
//
//	@ResponseBody
//	@PostMapping("/collectParcel/add")
//	public CollectParcel createCollectParcel(@RequestBody CollectParcel ctps) {
//		return collectParcelService.addCollectParcel(ctps);
//	}

//	// 修改包裹(測試未ok)
//    @ResponseBody
//    @PutMapping("/update/{id}")
//    public CollectParcel updateCollectParcel(@PathVariable int id, @RequestBody CollectParcel ctp) {
//    	return collectParcelService.updateCollectParcel(id, ctp);
//    }
}