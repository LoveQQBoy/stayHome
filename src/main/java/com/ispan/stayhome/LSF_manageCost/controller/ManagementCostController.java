package com.ispan.stayhome.LSF_manageCost.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ispan.stayhome.LSF_manageCost.model.ManageCostList;
import com.ispan.stayhome.LSF_manageCost.model.ManagementCost;
import com.ispan.stayhome.LSF_manageCost.service.ManagementCostService;

@Controller
public class ManagementCostController {

	@Autowired
	private ManagementCostService managementCostService;
	
	@ResponseBody
	@GetMapping("/manageCost/vist")
	public List<ManagementCost> findAllManageCost(){
		return managementCostService.findAllData();
	}
	
	@ResponseBody
	@DeleteMapping("/manageCost/delete")
	public String deleteManagementCost(Integer id) {
		managementCostService.deleteById(id);
		return "刪除完成";
	}
	
	//進入管理費後台頁面
	@GetMapping("/manageCost/manageCostPageBack")
	public String visitManageCostListPageBack(@RequestParam(name="page",defaultValue = "1")Integer pageNumber,
			@RequestParam(name="error",required = false)String error,Model model) {
		Page<ManageCostList> mclPage = managementCostService.findAllManageCostListPage(pageNumber);
		
		model.addAttribute("mclPage",mclPage);
		model.addAttribute("error", error);
		
		return "manageCost/showManageCostBack";
	}
	
	//進入新增管理費期別頁面
	@GetMapping("/manageCost/addManageCostPage")
	public String visitAddManageCostPage() {
		return "manageCost/addManageCostList";
	}
	
	//新增一期管理費
	@PostMapping("/manageCost/addManageCostList")
	public String addManageCostList(@RequestParam(name="manageTitle")String title,
			@RequestParam(name="dateYear")String mcYear, @RequestParam(name="dateMonth")String mcMonth,
			@RequestParam(name="squareF")Integer squareFeet, @RequestParam(name="parkF")Integer parkingFee) {
		
		ManageCostList manageCostList = new ManageCostList();
		
		manageCostList.setMcTitle(title);
		manageCostList.setMcYear(mcYear);
		manageCostList.setMcMonth(mcMonth);
		manageCostList.setMcState(0);
		manageCostList.setSquareFeet(squareFeet);
		manageCostList.setParkingFee(parkingFee);
		
		Date date = new Date();
		manageCostList.setCreateDate(date);
		
		managementCostService.insertOrUpdateManagementCostList(manageCostList);
		managementCostService.CreateManagementCostForAllHousehold();
		
		return "redirect:/manageCost/manageCostPageBack";
	}
	
	//進入每期管理費詳細細頁面
	@GetMapping("/manageCost/manageCostDetailPage/{mcId}")
	public String visitManageCostDetailPage(@PathVariable(name = "mcId")Integer id, Model model) {
		
		List<ManagementCost> managementCosts = managementCostService.findAllManageCostById(id);
		
		model.addAttribute("managementCosts",managementCosts);
		
		return "manageCost/showManageCostDetail";
	}
	
	//進入前台管理費頁面
	@GetMapping("/manageCost/manageCostPageFront")
	public String visitManageCostPageFront(@RequestParam(name="page",defaultValue = "1")Integer pageNumber,
			HttpSession session,Model model) {
		
		Integer userId = (Integer)session.getAttribute("pid");
		
		Page<ManagementCost> mcPage = managementCostService.findUserManageCost(pageNumber, userId);
		
		model.addAttribute("mcPage",mcPage);
		
		return "manageCost/showManageCostFront";
	}
	
	//開啟或關閉管理費列表
	@PutMapping("/manageCost/changeState/{state}")
	public String changeManageCostListState(@PathVariable(name = "state")String state,
			@RequestParam Integer id) {
		
		ManageCostList manageCostList = managementCostService.findManageCostListById(id);
		
		if(state.equals("on")) {
			manageCostList.setMcState(1);
		}else {
			manageCostList.setMcState(0);
		}
		
		managementCostService.insertOrUpdateManagementCostList(manageCostList);
		
		return "redirect:/manageCost/manageCostPageBack";
	}
	
	//刪除住戶的管理費
	@DeleteMapping("/manageCost/deleteManageCost")
	public String deleteManageCostById(@RequestParam Integer id) {
		
		Integer mclId = managementCostService.findById(id).getManageCostList().getId();
		managementCostService.deleteById(id);
		
		return "redirect:/manageCost/manageCostDetailPage/"+mclId;
	}
	
	//刪除一整期的管理費
	@DeleteMapping("/manageCost/deleteManageCostList")
	public String deleteManageCostListById(@RequestParam Integer id, RedirectAttributes attrs) {
		boolean deleteSuccess;
		
		deleteSuccess = managementCostService.deleteListById(id);

		attrs.addAttribute("error",deleteSuccess);
		
		return "redirect:/manageCost/manageCostPageBack";
	}
}
