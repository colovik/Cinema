package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Movie;
import mk.ukim.finki.wp.lab.model.TicketOrder;
import mk.ukim.finki.wp.lab.service.interfaces.MovieService;
import mk.ukim.finki.wp.lab.service.interfaces.TicketOrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/ticketOrder")
public class TicketOrderController {

    private final TicketOrderService ticketOrderService;
    private final MovieService movieService;

    public TicketOrderController(TicketOrderService ticketOrderService, MovieService movieService) {
        this.ticketOrderService = ticketOrderService;
        this.movieService = movieService;
    }

    @GetMapping
    public String getTicketPage(Model model) {
        model.addAttribute("allOrders", ticketOrderService.findAll());
        Movie movie = movieService.mostOrdered(movieService.listAll());
        model.addAttribute("movieTitle", movie.getTitle());
        model.addAttribute("numTickets", movie.getNum_orders());
        model.addAttribute("clients", ticketOrderService.getAllClients());
        return "orderConfirmation";
    }

    @GetMapping("/search")
    public String clientMoviesPage(@RequestParam String clientNameFilter, Model model) {
        List<TicketOrder> filteredTicketsByUser = ticketOrderService.findTicketsByUser(clientNameFilter);
        model.addAttribute("allOrders", filteredTicketsByUser);
        Movie movie = movieService.mostOrdered(movieService.listAll());
        model.addAttribute("movieTitle", movie.getTitle());
        model.addAttribute("numTickets", movie.getNum_orders());
        model.addAttribute("clients", ticketOrderService.getAllClients());
        return "orderConfirmation";
    }
}
