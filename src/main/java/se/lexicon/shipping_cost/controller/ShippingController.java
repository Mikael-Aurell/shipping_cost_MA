package se.lexicon.shipping_cost.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import se.lexicon.shipping_cost.entity.Box;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("admin/box")
public class ShippingController {
 private List<Box> boxList = new ArrayList<>();

 @GetMapping("addBox")
    public String registerForm(Model model){
     Box box = new Box();
     model.addAttribute("box", box);
     return "AddBoxForm";
    }
}
