package com.goit.gojavaonline.spring.mvc.utils;

import com.mysql.cj.core.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class AuthTokenRepository {

    private static final String AUTH_TOKEN = "authToken";
    private static Set<String> tokens = new HashSet<>();

    public static void signResponse(HttpServletResponse response) {
        String newAuthToken = registerAuthToken();
        response.addCookie(new Cookie( AUTH_TOKEN, newAuthToken));
    }

    private static boolean isActiveToken(String token) {
        return !StringUtils.isEmptyOrWhitespaceOnly(token) && tokens.contains( token );
    }

    private static String registerAuthToken() {
        String newAuthToken = UUID.randomUUID().toString();
        tokens.add( newAuthToken );
        return newAuthToken;
    }

    public static void initializeAuthAccess(HttpServletRequest request) {
        Cookie authCookie = getRequestCookie( request, AUTH_TOKEN );
        if (authCookie != null) {
            String clientAuthToken = authCookie.getValue();
            if (isActiveToken( clientAuthToken )) {
                AppSecurity.authenticationConfirmed();
            }
        }
    }

    public static void releaseCurrentToken() {
        AppSecurity.releaseCurrentToken();
    }

    private static Cookie getRequestCookie(HttpServletRequest request, String cookieName) {
        Cookie resultCookie = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equalsIgnoreCase(cookieName)) {
                    resultCookie = cookie;
                    break;
                }
            }
        }
        return resultCookie;
    }

}
