//package com.ispan.stayhome.TLK_activity.FrontTest;
//
//import java.util.List;
//
//import javax.servlet.http.HttpSession;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.servlet.ModelAndView;
//
//import com.ispan.stayhome.CPC_login.dao.HouseholdDao;
//import com.ispan.stayhome.CPC_login.model.HouseholdData;
//import com.ispan.stayhome.CPC_login.service.HouseholdService;
//
///*
// * 首先了解一下IOC操作Bean管理，bean管理是指（1）spring创建对象 （2）spring注入属性。
// * 当我们在将一个类上标注@Service或者@Controller或@Component或@Repository注解之后，
// * spring的组件扫描就会自动发现它，并且会将其初始化为spring应用上下文中的bean。 
// * 当需要使用这个bean的时候，例如加上@Autowired注解的时候，这个bean就会被创建。
// * 而且初始化是根据无参构造函数。
// */
//
//@Controller  
//public class HouseholdController_Test {
//	@Autowired
//	private HouseholdService hService;
//	private HouseholdDao householdDao;
//	
//	//navbar 註冊 跳轉頁面  先設定一個空的householdData 然後model Attribute送一個物件到addHousehold.JSP頁面
//	@GetMapping("/household/add")
//	public String addHouseholdPage(Model model) {
//	
//		HouseholdData householdData= new HouseholdData();
//		model.addAttribute("householdData",householdData);
//		
//		return "House/addHousehold";
//	}
//	
//	
//	@PostMapping("/household/post")
//	public ModelAndView addHouseholdPost(@ModelAttribute("householdData") HouseholdData householdData,ModelAndView modelAndView) {
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
//	
//	//////////////檢查 EMAIL  PHONE 是否重複
//	
//	@GetMapping("/checkEmail")
//    public String checkEmail(@RequestParam String email) {
//		HouseholdData householdData = householdDao.findByEmail(email);
//        if (householdData != null) {
//            return "E-mail已重複，請重新註冊";
//        } else {
//            return "OK";
//        }
//    }
// 
//    @GetMapping("/checkPhone")
//    public String checkPhone(@RequestParam String phone) {
//    	HouseholdData householdData = householdDao.findByPhone(phone);
//        if (householdData != null) {
//            return "Phone已重複，請重新註冊";
//        } else {
//            return "OK";
//        }
//    }
//	
//	
//	
//	
//	
//	@GetMapping("/household/readpage")
//	public String showHouseholdPage(Model model) {
//		List<HouseholdData> householdData= hService.findAll();
//		
//		model.addAttribute("householdData",householdData);
//		
//		return "House/showHouse";
//	}
//		
//	
//	/*跳轉修改頁面 ShowHouse.JSP 會送一個 input name="id" 過來
//	所以用 RequestParam Integer id 去接 ，接下來用 hService.findById 找到(id) 
//	最後 model Attribute 過去給household/editpost*/
//	
//	//RequestParam是Spring框架中的一個註解，用於從HTTP請求中獲取請求參數的值，並將其映射到控制器方法的參數上。
//	//但如果input 超過3 建議改用ModelAttribute
//	
//	@GetMapping("/household/edit")
//	public String editHouseholdPage(@RequestParam Integer id,Model model) {
//		HouseholdData householdData=hService.findById(id);
//		model.addAttribute("householdData",householdData);
//		
//		return "House/editHouse";
//		
//	}
//	
//	
//	@PutMapping("/household/editpost")
//	public String postEdit(@ModelAttribute("householdData") HouseholdData householdData) {
//		hService.updateHouseholdData(householdData);//沒有ID會新增  有ID 會更新
//		return "redirect:/household/readpage";
//	}
//	
//	
//	//刪除
//	@DeleteMapping("/household/delete")
//	public String deleteHousehold(@RequestParam Integer id) {
//		hService.deleteById(id);
//		return "redirect:/household/readpage";
//	}
//	
//	
//	@GetMapping("/household/loginpage")
//	public String loginHouseholdPage(Model model) {
//	
//		HouseholdData householdData= new HouseholdData();
//		model.addAttribute("householdData",householdData);
//		
//		return "House/loginHouse";
//	}
//	
//	
//	/*
//	  	&&	与	将两个表达式连接成一个。两个表达式必须都为 true，整个表达式才为 true
//		||	或	将两个表达式连接成一个。必须有一个或两个表达式为 true，才能使整个表达式为 true。只要其中有一个为 true，那么另外一个就变得无关紧要
//		！	非	反转一个表达式的“真相”。它使一个表达式从 true 变成了 false，或者从 false 变成了 true*/
//	
//	@PostMapping("/household/login")
//    public String loginHousehold(@ModelAttribute HouseholdData householdData, HttpSession session) {
//		HouseholdData existingHouse = hService.findByEmailAndPassword(householdData.getEmail(),householdData.getPassword());
//        if (existingHouse != null && existingHouse.getManagerPermission()==1 && existingHouse.getPassword().equals(householdData.getPassword())) {
//        	int pid=existingHouse.getId();
//        	String name=existingHouse.getName();
//        	int managerPermission = existingHouse.getManagerPermission();
////        	session.setAttribute("existingHouse", existingHouse);
//        	session.setAttribute("pid", pid);
//        	session.setAttribute("name", name);
//        	session.setAttribute("managerPermission", managerPermission);
//        	
//        	
//        	// 之後要導到後台去
//            return "redirect:/";
//        } else if(existingHouse.getManagerPermission()==0) {
//        	int pid=existingHouse.getId();
//        	String name=existingHouse.getName();
//        	int managerPermission = existingHouse.getManagerPermission();
//        	session.setAttribute("pid", pid);
//        	session.setAttribute("name", name);
//        	session.setAttribute("managerPermission", managerPermission);
//            return "redirect:/";
//        }else {
//        	 return "/";
//        }
//    }
//	
//	@GetMapping("/household/logout")
//    public String logoutHousehold(HttpSession session) {
//       // session.removeAttribute("name");
//        //session.removeAttribute("pid");
//        session.invalidate();
//        return "redirect:/";
//    }
//	
//
//}