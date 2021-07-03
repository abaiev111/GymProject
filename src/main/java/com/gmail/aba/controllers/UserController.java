package com.gmail.aba.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {


    @GetMapping("/user/rools")
    public String userRools(){
        return "user_rools";
    }

    @GetMapping("/user/contact")
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
