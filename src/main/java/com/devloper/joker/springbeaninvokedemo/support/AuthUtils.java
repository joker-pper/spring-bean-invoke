package com.devloper.joker.springbeaninvokedemo.support;

import javax.servlet.http.HttpServletRequest;

public class AuthUtils {

    public static boolean isPass(HttpServletRequest request) {
        String method = request.getMethod();
        String token = request.getHeader("token");
        if ("admin".equals(token) || "OPTIONS".equals(method)) {
            //自定义filter和拦截器存在验证请求权限时由于优先级大于CORS的验证所以需要放行ajax的预检请求
            return true;
        }
        return false;
    }

}
