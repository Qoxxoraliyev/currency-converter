package com.currency_converter.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;


@Controller
public class CurrencyPageController {

    @GetMapping("/")
    public String index(@ModelAttribute String string){
        return "index";
    }


}
