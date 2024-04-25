package com.bank.cedrus.model.response;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

import lombok.Data;

@Data
public class Response<T> {
    private String message;
    private boolean success;
    private String status;
    private String token;
    private String timeStamp;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @JsonUnwrapped
    T optionalValue;

    public Response(String responseCode, String token, String message) {
    	if ("Ok".equalsIgnoreCase(responseCode)) {
            this.message = "success";
            this.success = true;
            this.status = "200";
        } else {
            this.message = message;
            this.success = false;
            this.status = "400";
        }
    	this.token = token;
        this.timeStamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
     
    }

    public void setOptionalValue(T optionalValue) {
        this.optionalValue = optionalValue;
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
                String value = values.get(index);
                if (value == null || value.trim().isEmpty()) {
                    field.set(instance, null); 
                } else {
	                try {
	                    if (field.getName().equals("dob")) {
	                        DateTimeFormatter incomingFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	                        LocalDate date = LocalDate.parse(values.get(index), incomingFormatter);
	                        DateTimeFormatter outgoingFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	                        field.set(instance, date.format(outgoingFormatter));
	                    }
	                    else if(field.getName().equalsIgnoreCase("policyInceptionDate"))
	                    {
	                    	DateTimeFormatter incomingFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	                    	LocalDate date = LocalDate.parse(values.get(index), incomingFormatter);
	                    	LocalDateTime dateTime = date.atStartOfDay(); // Sets time to midnight
	                    	DateTimeFormatter outgoingFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	                    	field.set(instance, dateTime.format(outgoingFormatter));
	                    }                    
	                    else {
	                         field.set(instance, values.get(index));
	                    }
	                } catch (Exception parseException) {
	                     field.set(instance, values.get(index));
	                }
                }
                index++;
            }
            return instance;
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
            return null;
        }
    }
 
}

