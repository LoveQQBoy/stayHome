package com.ispan.stayhome.CPC_login.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ispan.stayhome.CPC_login.model.HouseholdData;
import com.ispan.stayhome.CPC_login.service.HouseholdService;

/*
 * 首先了解一下IOC操作Bean管理，bean管理是指（1）spring创建对象 （2）spring注入属性。
 * 当我们在将一个类上标注@Service或者@Controller或@Component或@Repository注解之后，
 * spring的组件扫描就会自动发现它，并且会将其初始化为spring应用上下文中的bean。 
 * 当需要使用这个bean的时候，例如加上@Autowired注解的时候，这个bean就会被创建。
 * 而且初始化是根据无参构造函数。
 */

@Controller  
public class HouseholdController {
	@Autowired
	private HouseholdService hService;
	
	
	//navbar 註冊 跳轉頁面  先設定一個空的householdData 然後model Attribute送一個物件到addHousehold.JSP頁面
	@GetMapping("/household/add")
	public String addHouseholdPage(Model model) {
//		 也可以這樣寫 model.addAttribute("householdData", new HouseholdData());
		
		/* Controller 的某個方法中，它的主要功能是用來初始化表單上的一些資料，並將它們儲存在 Spring 的 Model 中，以便在表單頁面中使用。
			首先，透過 model.containsAttribute() 方法來判斷 Model 是否已經存在一個名為 householdData 的屬性。
			如果 Model 中沒有 householdData 這個屬性，就表示這是一個新的表單請求，需要初始化表單資料。
			接下來，透過建立一個新的 HouseholdData 物件，並使用 model.addAttribute() 方法將這個物件添加到 Model 中。
			如果 Model 中已經存在 householdData 這個屬性，則不會進行任何動作。
			簡單來說，這段程式碼的作用就是確保每一次進入表單頁面時，表單上的資料都是最新的。如果之前已經有輸入的資料，則保留這些資料；
			如果是新的表單請求，則初始化表單資料。*/
		if (!model.containsAttribute("householdData")) {
	     	HouseholdData householdData= new HouseholdData();
		model.addAttribute("householdData",householdData);
		
	    }
	
		return "House/addHousehold";
	}
	
//	//註冊
//	@PostMapping("/household/post")
//	public ModelAndView addHouseholdPost(@ModelAttribute("householdData")@Valid HouseholdData householdData,BindingResult bindingResult,ModelAndView modelAndView) {
//		
//		
//		
//		try {
//			hService.add(householdData);
//		}catch(RuntimeException e){
//			modelAndView.addObject("error", e.getMessage());
//            modelAndView.setViewName("House/addHousehold");
//		}
//		
//		if(modelAndView.isEmpty()) {
//			modelAndView.setViewName("House/loginHouse");
//			return modelAndView;
//		}
//		return modelAndView;
//		
//	}
	
	//@ModelAttribute("householdData")@Valid  HouseholdData householdData,BindingResult bindingResultt 要連一起 不然會報錯
	//註冊 spring boot 表單驗證
		@PostMapping("/household/post")
		public String addHouseholdPost(@ModelAttribute("householdData")@Valid HouseholdData householdData,BindingResult bindingResult,Model model,RedirectAttributes redirectAttributes) {
			
			if (bindingResult.hasErrors()) {
				redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.householdData", bindingResult);
		        redirectAttributes.addFlashAttribute("householdData", householdData);
	            return "redirect:/household/add";
	        }
			
//			if (bindingResult.hasErrors()) {
//				
//	            return "House/addHousehold";
//	        }
			
			try {
				hService.add(householdData);
			}catch(RuntimeException e){
				model.addAttribute("error", e.getMessage());
				return "House/addHousehold";
			}
	       
			
		
			redirectAttributes.addFlashAttribute("success", "註冊成功！");
			return "redirect:/household/loginpage";
			
		}
	
	
	//跳轉全住戶頁面
	@GetMapping("/household/readpage")
	public String showHouseholdPage(Model model) {
		List<HouseholdData> householdData= hService.findAll();
		
		model.addAttribute("householdData",householdData);
		
		return "House/showHouse";
	}
		
	
	/*跳轉修改頁面 ShowHouse.JSP 會送一個 input name="id" 過來
	所以用 RequestParam Integer id 去接 ，接下來用 hService.findById 找到(id) 
	最後 model Attribute 過去給household/editpost*/
	
	//RequestParam是Spring框架中的一個註解，用於從HTTP請求中獲取請求參數的值，並將其映射到控制器方法的參數上。
	//但如果input 超過3 建議改用ModelAttribute
	
	//跳轉修改頁面 前台
	@GetMapping("/household/editFront")
	public String editHouseholdFrontPage(@RequestParam Integer id,Model model) {
	
		HouseholdData householdData=hService.findById(id);
		model.addAttribute("householdData",householdData);
		
		return "House/editHouseFront";
		
	}
	
	//跳轉修改頁面 後台
	@GetMapping("/household/editBack")
	public String editHouseholdPage(@RequestParam Integer id,Model model) {
		HouseholdData householdData=hService.findById(id);
		model.addAttribute("householdData",householdData);
		
		return "House/editHouseBack";
		
	}
	
	
	//住戶資訊修改
	@PostMapping("/household/editpost")
	public String postEdit(@ModelAttribute("householdData")@Valid HouseholdData householdData,BindingResult bindingResult,
			HttpSession session,HttpServletRequest request,Model model,RedirectAttributes redirectAttributes) {
	
	

		
		int path = 0;//0是導向後台頁面'  頁面導向判斷器
		if(householdData.getManagerPermission()==null) { //因為住戶前台修改資訊的頁面沒有權限，所以更新會變NULL，就會觸發以下方法
			path=1;//1是導向前台頁面'
			
			// hService.findById(householdData.getId()) = 先用findById方法 從前端送過來的ID 找到自己
			//.getManagerPermission(); 再從資料庫找到當時的權限
			Integer mp =  hService.findById(householdData.getId()).getManagerPermission();
			householdData.setManagerPermission(mp);//最後把權限設定回去後更新
		}
		
		if (bindingResult.hasErrors()) {
			if(path == 1) {
				return "House/editHouseFront";
			}
			return "House/editHouseBack";
		}
		
		hService.updateHouseholdData(householdData);//沒有ID會新增  有ID 會更新
		Integer mp2 = householdData.getManagerPermission();

		session.setAttribute("managerPermission", mp2);
		
		if(path == 0 && mp2==1) {
			return "redirect:/household/readpage";//redirect: 吃的是controller的URL 而不是頁面路徑
		}else {
			return "redirect:/";//redirect: 吃的是controller的URL 而不是頁面路徑
		}
		
		
		
	}
	
	
	////////////測試////////////////////////////////////////
	@DeleteMapping("/household/delete")
	public String deleteHousehold(@RequestParam Integer id, Model model) {
	    try {
	        hService.deleteById(id);
	        model.addAttribute("deleteSuccess", true);
	    } catch (Exception e) {
	        model.addAttribute("deleteSuccess", false);
	    }
	    return "redirect:/household/readpage";
	}
	//////////////////////////////////////////
	
	
	
//	//刪除
//	@DeleteMapping("/household/delete")
//	public String deleteHousehold(@RequestParam Integer id) {
//		hService.deleteById(id);
//		return "redirect:/household/readpage";
//	}
	
	//登入頁面
	@GetMapping("/household/loginpage")
	public String loginHouseholdPage(Model model) {
	
		HouseholdData householdData= new HouseholdData();
		model.addAttribute("householdData",householdData);
		
		return "House/loginHouse";
	}
	
	
	/*
	  	&&	与	将两个表达式连接成一个。两个表达式必须都为 true，整个表达式才为 true
		||	或	将两个表达式连接成一个。必须有一个或两个表达式为 true，才能使整个表达式为 true。只要其中有一个为 true，那么另外一个就变得无关紧要
		！	非	反转一个表达式的“真相”。它使一个表达式从 true 变成了 false，或者从 false 变成了 true*/
	//登入
	@PostMapping("/household/login")
    public String loginHousehold(@ModelAttribute HouseholdData householdData, HttpSession session,RedirectAttributes redirectAttributes) {
		HouseholdData existingHouse = hService.findByEmailAndPassword(householdData.getEmail(),householdData.getPassword());
        if (existingHouse != null && existingHouse.getManagerPermission()==1 && existingHouse.getPassword().equals(householdData.getPassword())) {
        	int pid=existingHouse.getId();
        	String name=existingHouse.getName();
        	int managerPermission = existingHouse.getManagerPermission();
//        	session.setAttribute("existingHouse", existingHouse);
        	session.setAttribute("pid", pid);
        	session.setAttribute("name", name);
        	session.setAttribute("managerPermission", managerPermission);
        	
        	
        	// 之後要導到後台去
            return "redirect:/";
        } else if(existingHouse != null && existingHouse.getManagerPermission()==0) {
        	int pid=existingHouse.getId();
        	String name=existingHouse.getName();
        	int managerPermission = existingHouse.getManagerPermission();
        	session.setAttribute("pid", pid);
        	session.setAttribute("name", name);
        	session.setAttribute("managerPermission", managerPermission);
            return "redirect:/";
        }else {
        	redirectAttributes.addFlashAttribute("error", "帳號or密碼錯誤，請重新輸入");
            return "redirect:/household/loginpage";
        }
    }
	
	//登出
	@GetMapping("/household/logout")
    public String logoutHousehold(HttpSession session) {
       // session.removeAttribute("name");
        //session.removeAttribute("pid");
        session.invalidate();
        return "redirect:/";
    }
	

}