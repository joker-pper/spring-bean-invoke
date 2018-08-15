package com.devloper.joker.springbeaninvokedemo.filter;

import com.devloper.joker.springbeaninvokedemo.support.AuthUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class AuthFilter implements Filter {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${auth.enable}")
    private Boolean enableAuth;

    @Value("${auth.is-filter}")
    private Boolean isFilter;

    private Boolean isWork;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        isWork = enableAuth && isFilter;
        if (isWork) {
            logger.info("auth filter 启用.....");
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (isWork) {
            HttpServletRequest request = (HttpServletRequest) servletRequest;
            if (AuthUtils.isPass(request)) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                HttpServletResponse response = (HttpServletResponse) servletResponse;
                PrintWriter printWriter = response.getWriter();
                printWriter.write("has no authority");
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            }
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }



    }

    @Override
    public void destroy() {

    }
}
