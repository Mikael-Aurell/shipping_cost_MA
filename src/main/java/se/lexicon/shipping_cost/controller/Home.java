package se.lexicon.shipping_cost.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Home {
    @RequestMapping("/")
    public String goToIndexPage(){
        return "index";
    }
}
