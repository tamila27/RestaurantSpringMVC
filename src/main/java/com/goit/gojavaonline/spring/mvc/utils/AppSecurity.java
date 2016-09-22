package com.goit.gojavaonline.spring.mvc.utils;

public class AppSecurity {

    private static ThreadLocal<Boolean> currentUserAuthenticated = new InheritableThreadLocal<>();

    public static boolean isUserAuthenticated() {
        Boolean authenticated = currentUserAuthenticated.get();
        return authenticated != null && authenticated;
    }

    static void authenticationConfirmed() {
        currentUserAuthenticated.set( Boolean.TRUE );
    }

    static void releaseCurrentToken() {
        currentUserAuthenticated.remove();
    }
}
