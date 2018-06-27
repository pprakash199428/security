package com.spring.security.controller;

import com.spring.security.common.SecurityContext;
import com.spring.security.dto.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class HomeController {
    private final SecurityContext securityContext;

    @Autowired
    public HomeController(SecurityContext securityContext) {
        this.securityContext = securityContext;
    }


    @RequestMapping(value = "/testServer", method = RequestMethod.GET)
    String firstTest(HttpServletRequest request) {
        System.out.println(securityContext.getAuthentication());
        return "OK :";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    UserDetails login() {
        return securityContext.getUserDetails();
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public void logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
    }

    @Secured("ROLE_USER")
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    String testUser(HttpServletRequest request) {
        System.out.println(securityContext.getAuthentication());
        return "User Authority Only :";
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    String testAdmin(HttpServletRequest request) {
        System.out.println(securityContext.getAuthentication());
        return "Admin Authority Only :";
    }

}
