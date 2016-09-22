package com.goit.gojavaonline.spring.mvc.utils;

public class UserIsNotAuthenticatedException extends RuntimeException {

    public UserIsNotAuthenticatedException() {
    }

    public UserIsNotAuthenticatedException( String message ) {
        super( message );
    }
}