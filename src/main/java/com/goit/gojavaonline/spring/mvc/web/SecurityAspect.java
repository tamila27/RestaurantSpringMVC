package com.goit.gojavaonline.spring.mvc.web;

import com.goit.gojavaonline.spring.mvc.utils.AppSecurity;
import com.goit.gojavaonline.spring.mvc.utils.UserIsNotAuthenticatedException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SecurityAspect {

    @Around(value = "@annotation(authenticationRequired)", argNames = "pjp,authenticationRequired")
    public Object checkSecurity( ProceedingJoinPoint pjp, AuthenticationRequired authenticationRequired) throws Throwable {
        if ( AppSecurity.isUserAuthenticated() ) {
            return pjp.proceed();
        } else {
            throw new UserIsNotAuthenticatedException( "Guest Users are not allowed to create new employee." );
        }
    }
}