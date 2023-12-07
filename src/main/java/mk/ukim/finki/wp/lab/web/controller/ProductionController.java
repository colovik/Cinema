package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Production;
import mk.ukim.finki.wp.lab.service.interfaces.ProductionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/production")
public class ProductionController {
    ProductionService productionService;

    public ProductionController(ProductionService productionService){
        this.productionService = productionService;
    }

    @GetMapping
    public String getProductionsPage(Model model){
        model.addAttribute("productions", productionService.findAll());
        return "listProductions";
    }

    @PostMapping("/add")
    public String addProduction(@RequestParam String name,
                                @RequestParam String country,
                                @RequestParam String address){
        productionService.save(new Production(name,country,address));
        return "redirect:/production";
    }
}
