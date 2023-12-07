package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Movie;
import mk.ukim.finki.wp.lab.model.TicketOrder;
import mk.ukim.finki.wp.lab.service.interfaces.MovieService;
import mk.ukim.finki.wp.lab.service.interfaces.TicketOrderService;
import mk.ukim.finki.wp.lab.service.interfaces.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/ticketOrder")
public class TicketOrderController {

    private final TicketOrderService ticketOrderService;
    private final MovieService movieService;
    private final UserService userService;

    public TicketOrderController(TicketOrderService ticketOrderService,
                                 MovieService movieService,
                                 UserService userService) {
        this.ticketOrderService = ticketOrderService;
        this.movieService = movieService;
        this.userService = userService;
    }

    @GetMapping
    public String getTicketPage(@RequestParam(required = false) String clientName, Model model) {
        List<TicketOrder> result = new ArrayList<>();
        if (clientName != null) {
            result = ticketOrderService.findTicketsByUser(clientName);
        } else {
            result = ticketOrderService.findAll();
        }
        model.addAttribute("allOrders", result);

        //dopolnitelno baranje labs2 - filtriranje orders po klient
        model.addAttribute("clients", ticketOrderService.getAllClientsWithOrders());

        //dopolnitelno baranje od labs1
        Movie movie = movieService.getMostOrderedMovie(movieService.findAll());
        model.addAttribute("movieTitle", movie.getTitle());
        model.addAttribute("numTickets", movie.getNumOrders());

        return "orderConfirmation";
    }

    @GetMapping("/filteredByDate")
    public String getOrdersInTimeInterval(@RequestParam("from") String from,
                                          @RequestParam("to") String to,
                                          Model model) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

        LocalDateTime fromDate = LocalDateTime.parse(from, formatter);
        LocalDateTime toDate = LocalDateTime.parse(to, formatter);

        List<TicketOrder> orders = ticketOrderService.getOrdersInTimeInterval(fromDate, toDate);
        model.addAttribute("allOrders", orders);

        //dopolnitelno baranje od labs1
        Movie movie = movieService.getMostOrderedMovie(movieService.findAll());
        model.addAttribute("movieTitle", movie.getTitle());
        model.addAttribute("numTickets", movie.getNumOrders());

        return "orderConfirmation";
    }

//    @GetMapping("/search")
//    public String clientMoviesPage(@RequestParam String clientName, Model model) {
//        List<TicketOrder> filteredTicketsByUser = ticketOrderService.findTicketsByUser(clientName);
//        model.addAttribute("allOrders", filteredTicketsByUser);
//        model.addAttribute("clients", ticketOrderService.getAllClientsWithOrders());
//
//        //dopolnitelno baranje od labs1
//        Movie movie = movieService.getMostOrderedMovie(movieService.findAll());
//        model.addAttribute("movieTitle", movie.getTitle());
//        model.addAttribute("numTickets", movie.getNumOrders());
//
//        return "orderConfirmation";
//    }
}
