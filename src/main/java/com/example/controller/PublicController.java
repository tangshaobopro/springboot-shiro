package com.example.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/public")
public class PublicController {

    @RequestMapping(value = "/unAuthc")
    public String unAuthc(){
        return "unAuthc";
    }
}

