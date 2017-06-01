package com.previsto.dinero.exception;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;


public class ApiException extends DineroException {

    @JsonCreator
    public ApiException(@JsonProperty(value = "code") int code, @JsonProperty(value = "message") String message) {
        super(message, code);
    }
    
    private static final long serialVersionUID = 1L;

}