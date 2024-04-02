package com.bank.cedrus.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class Response<T> {
    private String message;
    private boolean success;
    private String status;
    private String token;
    private String timeStamp;
    private Map<String, Optional<T>> optionalValues;

    public Response(String responseCode, String token, String message) {
    	if ("Ok".equalsIgnoreCase(responseCode)) {
            this.message = "success";
            this.success = true;
            this.status = "200";
        } else {
            this.message = HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();
            this.success = false;
            this.status = "400";
        }
        this.timeStamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.optionalValues = new HashMap<>();
    }

    public void setOptionalValue(String key, T value) {
        this.optionalValues.put(key, Optional.ofNullable(value));
    }

    public Map<String, Object> toMap() {
        Map<String, Object> response = new HashMap<>();
        response.put("message", this.message);
        response.put("success", this.success);
        response.put("status", this.status);
        response.put("token", this.token);
        response.put("timeStamp", this.timeStamp);
        optionalValues.forEach((key, value) -> value.ifPresent(val -> response.put(key, val)));
        return response;
    }
}

