package com.devloper.joker.springbeaninvokedemo.adapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class ConfigAdapter extends WebMvcConfigurationSupport {
    //WebMvcConfigurerAdapter

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${auth.enable}")
    private Boolean enableAuth;

    @Value("${auth.is-filter}")
    private Boolean isFilter;

    @Autowired
    private AuthInterceptorAdapter authInterceptorAdapter;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        if (enableAuth && !isFilter) {
            logger.info("auth interceptor 启用.....");
            registry.addInterceptor(authInterceptorAdapter).addPathPatterns("/**");
        }
    }
}