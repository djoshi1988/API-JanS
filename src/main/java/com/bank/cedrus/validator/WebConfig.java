package com.bank.cedrus.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.bank.cedrus.common.SecurityProperties;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	
	private final SecurityProperties securityProperties;

    @Autowired
    public WebConfig(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new HeaderValidationInterceptor(securityProperties)).addPathPatterns("/**");
    }
}
