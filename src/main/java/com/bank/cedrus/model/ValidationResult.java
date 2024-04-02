package com.bank.cedrus.model;

import lombok.Data;

@Data
public class ValidationResult<T> {
    private String errorMessage;
    private T object;

    public ValidationResult(String errorMessage, T object) {
        this.errorMessage = errorMessage;
        this.object = object;
    }
}
