package com.document.system.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class ResourceExistException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ResourceExistException(String message){
        super(message);
    }
}
