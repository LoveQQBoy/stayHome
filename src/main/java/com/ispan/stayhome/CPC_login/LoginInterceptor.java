package com.ispan.stayhome.CPC_login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class LoginInterceptor implements HandlerInterceptor {
	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        HttpSession session = request.getSession();
        Object pid = session.getAttribute("pid");

        if (pid == null) {
            // 如果使用者未登入，則導向登入頁面
            response.sendRedirect("/stayHome/household/loginpage"); //前面要加/stayHome，不然會有問題˙主要是路徑關係
            return false;
        }
        return true;
    }

}
