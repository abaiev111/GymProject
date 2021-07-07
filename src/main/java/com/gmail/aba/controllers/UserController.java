package com.gmail.aba.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    @GetMapping()
    public String getUser() {
        return "success";
    }


    @GetMapping("/rools")
    public String userRools(){
        return "user_rools";
    }

    @GetMapping("/contact")
    public String userContact()   {
        return "user_contact";
    }

    @GetMapping("/one")
    public String returnOne() {
        return "training-program-one";
    }
    @GetMapping("/two")
    public String returnTwo() {
        return "training-program-two";
    }
    @GetMapping("/three")
    public String returnThree() {
        return "training-program-three";
    }
}
