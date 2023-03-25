package com.ispan.stayhome.TLK_activity.controller;

import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class errorPageController {

	@ExceptionHandler(NullPointerException.class)
	public String errorPageNullPointException(Exception ex) {
		//登入錯誤
		if (ex.toString().contains("existingHouse")) {
			System.out.println("bbbbbbbbbbbbbbbbbbb");
		return "redirect:/household/loginpage";
		}
		return "errorPage/errorPage";
	}
	
	@ExceptionHandler(InvalidDataAccessApiUsageException.class)
	public String errorPageInvalidDataAccessApiUsageException(Exception ex) {
		//剛好登入過期
		if(ex.toString().contains("The given id must not be null!")) {
			System.out.println(ex);
			System.out.println("cccccccccccccccc");
			return "redirect:/household/loginpage";
		}
		return "errorPage/errorPage";
	}
	
	@ExceptionHandler(Exception.class)
	public String errorPage(Exception ex) {
		System.out.println(ex);
		return "errorPage/errorPage";
	}
	
	
	

}
