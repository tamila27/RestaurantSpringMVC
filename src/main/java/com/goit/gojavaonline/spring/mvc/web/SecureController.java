package com.goit.gojavaonline.spring.mvc.web;

import com.goit.gojavaonline.spring.mvc.utils.UserIsNotAuthenticatedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

public class SecureController {

    @ExceptionHandler(UserIsNotAuthenticatedException.class)
    public ResponseEntity<SecurityError> rulesForCustomerNotFound( HttpServletRequest req, Exception e ) {
        SecurityError error = new SecurityError( e.getMessage() );
        return new ResponseEntity<>( error, HttpStatus.FORBIDDEN );
    }

}
