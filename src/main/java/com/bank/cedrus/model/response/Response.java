package com.bank.cedrus.model.response;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
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
    
    public void addOptionalValues(T input) {
        if (input != null) {
            Class<?> clazz = input.getClass();
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                try {
                    field.setAccessible(true);
                    optionalValues.put(field.getName(), Optional.ofNullable((T) field.get(input)));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    
    public static <T> T setFields(Class<T> clazz, List<String> values) {
        if (values.size() != clazz.getDeclaredFields().length) {
            throw new IllegalArgumentException("Invalid number of values provided");
        }

        try {
            T instance = clazz.getDeclaredConstructor().newInstance();
            int index = 0;
            for (java.lang.reflect.Field field : clazz.getDeclaredFields()) {
                field.setAccessible(true);
                field.set(instance, values.get(index++));
            }
            return instance;
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
            return null;
        }
    }
 
}

