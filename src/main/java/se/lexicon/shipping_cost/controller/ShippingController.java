package se.lexicon.shipping_cost.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import se.lexicon.shipping_cost.entity.Box;
import se.lexicon.shipping_cost.repository.BoxRepository;

import java.util.ArrayList;
import java.util.List;



@Controller
@RequestMapping("admin/box")
public class ShippingController {


    BoxRepository boxRepository;

    @Autowired
    public void setBoxRepository(BoxRepository boxRepository) {
        this.boxRepository = boxRepository;
    }

    private List<Box> boxList = new ArrayList<>();

 @GetMapping("list")
 public String getAll(Model model){
     List<Box> boxListFromRep = findAll();
     model.addAttribute("boxListFromRep", boxListFromRep);

    return "boxList";
 }

 @GetMapping("addBox")
    public String registerForm(Model model){
     Box box = new Box();
     model.addAttribute("box", box);
     return "addBoxForm";
    }

    @PostMapping("add")
    public String add(@ModelAttribute("box") Box box){
     boxRepository.save(box);
     return "redirect:/admin/box/list";
    }

    List<Box> findAll() {
        Iterable<Box> iterable = boxRepository.findAll();
        List<Box> boxList = new ArrayList<>();
        iterable.iterator().forEachRemaining(boxList::add);

        return boxList;
    }
}
