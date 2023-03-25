package com.ispan.stayhome.CYS_repair;

import org.springframework.ui.Model;
import org.springframework.validation.BindException;

//@ControllerAdvice(assignableTypes = {RepairRequiteController.class})  //是给Controller控制器添加统一的操作或处理
public class GlobalExceptionHandler {

//	要加依賴
//	<dependency>
//	<groupId>org.springframework.boot</groupId>
//	<artifactId>spring-boot-starter-validation</artifactId>
//	</dependency>
	
	
//	@ExceptionHandler(BindException.class)   //是用于BindException异常的处理
    public String handleBindException(BindException ex, Model model) {
		System.out.println("--------------123456----------- " + ex.getMessage());
        model.addAttribute("errorMessage", ex.getMessage()); // 將錯誤訊息傳遞到錯誤頁面
        System.out.println("--------------1------------ ");
        return "repair/NewFile";
		
//		ModelAndView modelAndView = new ModelAndView();
//		System.out.println("--------------1------------ ");
//        modelAndView.setViewName("repair/NewFile"); // 自訂的錯誤頁面
//        System.out.println("--------------2-------------- " );
//        modelAndView.addObject("errorMessage", ex.getMessage()); // 將錯誤訊息傳遞到錯誤頁面
//        System.out.println("--------------3-------------- " );
//        return modelAndView;
        
    }
	
}
