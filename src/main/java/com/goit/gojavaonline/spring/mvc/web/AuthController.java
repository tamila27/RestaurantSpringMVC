package com.goit.gojavaonline.spring.mvc.web;

import com.goit.gojavaonline.spring.mvc.utils.AuthTokenRepository;
import com.goit.gojavaonline.spring.mvc.utils.PasswordHash;
import com.goit.gojavaonline.spring.mvc.utils.PasswordLoader;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.util.Date;
import java.util.Objects;

@Controller
public class AuthController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView( "login" );
        modelAndView.addObject( "currentTime", DateFormat.getDateTimeInstance().format( new Date() ) );
        modelAndView.addObject("active", "login");
        return modelAndView;
    }

    @RequestMapping(value = "/sign-in", method = RequestMethod.POST)
    public ModelAndView signIn(
            @RequestParam(name = "username") String username, @RequestParam(name = "password") String password,
            HttpServletResponse response) {
        String hash = loadAdminPasswordHash( username );
        if ( StringUtils.isEmpty( hash ) ) {
            return authFailed(username);
        }
        String hashToCheck = PasswordHash.hash( password );
        if ( Objects.equals(hashToCheck, hash) ) {
            return authSucceed(response);
        }
        return authFailed(username);
    }

    private String loadAdminPasswordHash(String username) {
        return PasswordLoader.loadPasswordHash(username);
    }

    private ModelAndView authFailed(String username) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject( "message", "Failed to authenticate user [" + username + "]" );
        return modelAndView;
    }

    private ModelAndView authSucceed(HttpServletResponse response) {
        AuthTokenRepository.signResponse(response);

        ModelAndView modelAndView = new ModelAndView("adminMainPage");
        modelAndView.addObject( "message", "Welcome to the Administration Page." );
        return modelAndView;
    }

}