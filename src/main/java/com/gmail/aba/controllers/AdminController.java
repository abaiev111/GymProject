package com.gmail.aba.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping()
    public String getAdmin() {
        return "success";
    }

    @GetMapping("/rools")
    public String adminRools(){
        return "admin_rools";
    }

    @GetMapping("/contact")
    public String adminContact(){
        return "admin_contact";
    }

    @GetMapping("/database")
    public String admin() {
        return "database";
    }

    @GetMapping("/one")
    public String returnOneAdmin() {
        return "admin_training-program-one";
    }
    @GetMapping("/two")
    public String returnTwoAdmin() {
        return "admin_training-program-two";
    }
    @GetMapping("/three")
    public String returnThreeAdmin() {
        return "admin_training-program-three";
    }
}
