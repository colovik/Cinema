package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Production;
import mk.ukim.finki.wp.lab.model.TicketOrder;
import mk.ukim.finki.wp.lab.service.interfaces.ProductionService;
import mk.ukim.finki.wp.lab.service.interfaces.TicketOrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/ticketOrder")
public class TicketOrderController {

    private final TicketOrderService ticketOrderService;
    private final ProductionService productionService;

    public TicketOrderController(TicketOrderService ticketOrderService, ProductionService productionService) {
        this.ticketOrderService = ticketOrderService;
        this.productionService = productionService;
    }

    @GetMapping
    public String getTicketPage(@ModelAttribute("order") TicketOrder order,
                                @ModelAttribute("production") String production,
                                Model model) {
        model.addAttribute("order", order);
        model.addAttribute("production", production);
        return "orderConfirmation";
    }


}
