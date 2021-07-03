package com.gmail.aba.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @GetMapping("/admin")
    public String getAdmin() {
        return "home_admin";
    }

    @GetMapping("/admin/rools")
    public String adminRools(){
        return "admin_rools";
    }

    @GetMapping("/admin/contact")
    public String adminContact(){
        return "admin_contact";
    }

    @GetMapping("/admin/database")
    public String admin() {
        return "database";
    }

    @GetMapping("/one/admin")
    public String returnOneAdmin() {
        return "admin_training-program-one";
    }
    @GetMapping("/two/admin")
    public String returnTwoAdmin() {
        return "admin_training-program-two";
    }
    @GetMapping("/three/admin")
    public String returnThreeAdmin() {
        return "admin_training-program-three";
    }
}
