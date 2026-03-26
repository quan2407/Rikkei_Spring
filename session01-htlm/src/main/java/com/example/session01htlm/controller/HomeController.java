package com.example.session01htlm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
    @GetMapping("/home")
    public String home(){
        return "home";
    }

    @GetMapping("/calculate")
    public String calculate(@RequestParam int num1,@RequestParam int num2, Model model) {
        // nhan tham so gui tren duong dan
       int s = num1 + num2;
       model.addAttribute("s",s);
        return "result";
    }
}
