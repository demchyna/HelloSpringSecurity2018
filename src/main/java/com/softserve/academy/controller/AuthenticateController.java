package com.softserve.academy.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AuthenticateController {

    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public String login() {
        return "login-form";
    }

    @RequestMapping(path = "/logout", method = RequestMethod.GET)
    public String logout() {
        SecurityContextHolder.clearContext();
        return "redirect:/login";
    }

}
