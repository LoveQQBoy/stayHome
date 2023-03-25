package com.ispan.stayhome.CPC_login.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ispan.stayhome.CPC_login.model.HouseType;
import com.ispan.stayhome.CPC_login.model.HouseholdData;
import com.ispan.stayhome.CPC_login.service.HouseTypeService;
import com.ispan.stayhome.CPC_login.service.HouseholdService;

@Controller
public class HouseTypeController {
	
	@Autowired
	private HouseTypeService houseTypeService;
	
	@Autowired
	private HouseholdService householdService;
	
	//跳轉戶型編輯頁面
	@GetMapping("HouseType/addpage")
	public String HouseTypeaddpage(@RequestParam Integer id,Model model) {
		
		HouseType findById = houseTypeService.findById(id);
		
		HouseType houseType=new HouseType();
		model.addAttribute("houseType", houseType);
		//添加house id
		model.addAttribute("householdId", id);
		return "House/addHouseType";
		
	}
	
	
	//編輯戶型送出
	@PostMapping("HouseType/add")
	public String HouseTypeadd(@ModelAttribute("houseType") HouseType houseType,HttpSession session,Model model,@RequestParam Integer householdId) {
		
		
		HouseholdData checkHouseholdData=householdService.checkHouseType(householdId);
		//條件判斷,先從sql去檢查是否有新增過了.若是沒新增過則進行新增,新增過就修改
		if(checkHouseholdData == null) {
			HouseholdData householdData=householdService.findById(householdId);
			HouseType houseTypeNew=houseTypeService.add(houseType);
			householdData.setHouseType(houseTypeNew);
			householdService.updateHouseholdData(householdData);
		}else {
			//如果已經有了則進行修改
			HouseholdData householdData=householdService.findById(householdId);
			Integer houseTypeId=householdData.getHouseType().getTypeId();
			houseType.setTypeId(houseTypeId);
			houseTypeService.add(houseType);
		}
		
		return "redirect:/household/readpage";
//		return "redirect:/HouseType/addpage?id=" + householdId;
	}

}
