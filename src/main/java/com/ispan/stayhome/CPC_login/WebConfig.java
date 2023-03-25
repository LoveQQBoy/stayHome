//package com.ispan.stayhome.CPC_login;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class WebConfig implements WebMvcConfigurer {
//	
//	@Autowired
//	private LoginInterceptor loginInterceptor;
//	
//	@Override
//    public void addInterceptors(InterceptorRegistry registry) {
//		System.out.println("我是攔截器");
//        registry.addInterceptor(loginInterceptor) 
//                .addPathPatterns("/**") //  所有路徑都攔截
//                .excludePathPatterns("/household/loginpage") // 登入頁面除外
//                .excludePathPatterns("/household/login")// 登入處理除外
//                .excludePathPatterns("/household/add")
//                .excludePathPatterns("/household/post")
//                .excludePathPatterns("/assets/**"); 
//        
//        
//    }
//
//}
