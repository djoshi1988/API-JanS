package com.bank.cedrus.model.request;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

import com.bank.cedrus.common.Constants;

public class Request {
	
	
	public static <T> String toFormString(T obj, String function,List<String> excludedFields) {
        StringJoiner joiner = new StringJoiner("|");
        joiner.add(Constants.PMYJAN).add(function);

        excludedFields = excludedFields == null ? Arrays.asList("token") : excludedFields;
        List<String> finalExcludedFields = excludedFields;
        
        
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                if (!finalExcludedFields.contains(field.getName())) { 
                	if (!Modifier.isStatic(field.getModifiers())) {
                        Object value = field.get(obj);
                        if (value != null) {
                            joiner.add(value.toString());
                        } else {
                             joiner.add(""); 
                        }
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return joiner.toString();
    }

}
