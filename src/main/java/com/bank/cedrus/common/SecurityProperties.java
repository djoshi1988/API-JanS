package com.bank.cedrus.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SecurityProperties {

    @Value("${security.user-name}")
    private String userName;

    @Value("${security.api-key}")
    private String apiKey;

    public String getUserName() {
        return userName;
    }

    public String getApiKey() {
        return apiKey;
    }
}
