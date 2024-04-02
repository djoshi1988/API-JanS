package com.bank.cedrus.common;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@ConfigurationProperties(prefix = "server.connect24")
@Data
public class ISOServiceConfiguration {
    private String address;
    private int port;
    private int connectionTimeout;
}
