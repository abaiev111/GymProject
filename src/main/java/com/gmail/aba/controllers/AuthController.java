package com.gmail.aba.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    @GetMapping("/rools")
    public String userRools(){
        return "user_rools";
    }


    @GetMapping("/login")
    public String getLoginPage(){
        return "login";
    }

    @GetMapping
    public String getSuccessPage(){
        return "success";
    }








}
