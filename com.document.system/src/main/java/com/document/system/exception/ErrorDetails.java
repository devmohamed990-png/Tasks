package com.document.system.exception;

import lombok.Data;

@Data
public class ErrorDetails {

    private String message;

    public ErrorDetails(String message) {
        this.message = message;
    }
}
