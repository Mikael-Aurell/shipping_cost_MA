package se.lexicon.shipping_cost.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import se.lexicon.shipping_cost.entity.Box;
import se.lexicon.shipping_cost.repository.BoxRepository;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


@Controller
@RequestMapping("shipping")
public class ShippingController {


    BoxRepository boxRepository;

    @Autowired
    public void setBoxRepository(BoxRepository boxRepository) {
        this.boxRepository = boxRepository;
    }

    private List<Box> boxList = new ArrayList<>();

    @GetMapping("/list")
    public String getAll(Model model) {

        model.addAttribute("boxList", findAll());

        return "showBoxList";
    }

    @GetMapping("/addBox")
    public String registerForm(Model model) {
        Box box = new Box();
        model.addAttribute("box", box);
        return "addBoxForm";
    }

    @PostMapping("/add")
    public String add(/*@Valid*/ @ModelAttribute("box") Box box /*BindingResult bindingResult*/) {

        /*if(bindingResult.hasErrors()){
            return "addBoxForm";
        }*/

        box.setCost(calculateCost(box));
        boxRepository.save(box);
        System.out.println("box = " + box);
        return "redirect:/shipping/list";
    }

    List<Box> findAll() {
        Iterable<Box> iterable = boxRepository.findAll();
        List<Box> boxList = new ArrayList<>();
        iterable.iterator().forEachRemaining(boxList::add);

        return boxList;
    }

    Double calculateCost(Box box){
        double setCost=0.0;
        if (box != null) {
            if (box.getCountry().equals("SW")) {
                if (box.getWeightType().equals("g")) {
                    setCost = ((box.getWeight() * 2.0) * 2.5);
                } else {
                    setCost = ((box.getWeight() * 1000.0) * 2.5);
                }
            }
            if (box.getCountry().equals("AU")) {
                if (box.getWeightType().equals("g")) {
                    setCost = ((box.getWeight() * 2.0) * 7.0);
                } else {
                    setCost = ((box.getWeight() * 1000.0) * 7.0);
                }
            }
        }
        return setCost;
    }
}
