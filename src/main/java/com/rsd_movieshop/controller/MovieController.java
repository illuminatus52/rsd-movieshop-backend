package com.rsd_movieshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@Controller
public class MovieController {
   @GetMapping ("/")
    public String getIndex() {
       return "";
   }
    @GetMapping   ("/register")
    public String getRegister() {
        return "";
    }
}
