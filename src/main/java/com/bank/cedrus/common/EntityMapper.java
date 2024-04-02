package com.bank.cedrus.common;

import java.lang.reflect.Field;
import org.springframework.beans.BeanUtils;

public class EntityMapper {

    public static <T> T mapModelToEntity(Object model, Class<T> entityClass) {
        try {
            T entity = entityClass.newInstance();
            BeanUtils.copyProperties(model, entity);
            return entity;
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException("Error mapping model to entity", e);
        }
    }
}
