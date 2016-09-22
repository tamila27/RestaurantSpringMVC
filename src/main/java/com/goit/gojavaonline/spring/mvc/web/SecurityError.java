package com.goit.gojavaonline.spring.mvc.web;

import java.io.Serializable;

public class SecurityError implements Serializable {

    private String message;
    private Boolean authFailed = Boolean.TRUE;

    public SecurityError( String message ) {
        this.message = message;
    }

    public SecurityError() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage( String message ) {
        this.message = message;
    }

    public Boolean getAuthFailed() {
        return authFailed;
    }
}