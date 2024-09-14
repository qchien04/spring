package com.example.pro.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
//@RequestMapping("/")
public class auth {

    @GetMapping("/login")
    public String loginform(){
        return "login";
    }


//    @PostMapping("/authencate")
//    public String login(){
//        return "redirect:/api/users";
//    }

}
