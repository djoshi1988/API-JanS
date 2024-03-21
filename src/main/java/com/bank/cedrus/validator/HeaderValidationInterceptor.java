package com.bank.cedrus.validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.bank.cedrus.common.SecurityProperties;

@Component
public class HeaderValidationInterceptor implements HandlerInterceptor {

    private final SecurityProperties securityProperties;

    @Autowired
    public HeaderValidationInterceptor(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        if (requestURI != null && requestURI.contains("/api/bank/v3")) {
           
        	String userName = request.getHeader("user-name");
            String apiKey = request.getHeader("api-key");

             if (userName == null || userName.isEmpty() || apiKey == null || apiKey.isEmpty() ||
                    !securityProperties.getUserName().equals(userName) || !securityProperties.getApiKey().equals(apiKey)) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return false;
            }
        }
        return true;
    }
}
