package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Movie;
import mk.ukim.finki.wp.lab.model.Production;
import mk.ukim.finki.wp.lab.model.ShoppingCart;
import mk.ukim.finki.wp.lab.model.TicketOrder;
import mk.ukim.finki.wp.lab.service.interfaces.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;
    private final TicketOrderService ticketOrderService;
    private final ProductionService productionService;
    private final UserService userService;
    private final ShoppingCartService shoppingCartService;

    public MovieController(MovieService movieService, TicketOrderService ticketOrderService, ProductionService productionService, UserService userService, ShoppingCartService shoppingCartService) {
        this.movieService = movieService;
        this.ticketOrderService = ticketOrderService;
        this.productionService = productionService;
        this.userService = userService;
        this.shoppingCartService = shoppingCartService;
    }

    @GetMapping
    public String getMoviesPage(Model model) {

        List<Movie> movies = movieService.findAll();
        model.addAttribute("movies", movies);

        return "listMovies";
    }

    @GetMapping("/search")
    public String searchMovies(@RequestParam(required = false) String searchName, @RequestParam(required = false) String searchRating, Model model) {
        List<Movie> movies = new ArrayList<>();
        if ((searchName == null || searchName.isEmpty()) && (searchRating == null || searchRating.isEmpty())) {
            movies = movieService.findAll();
        }
        if (searchName != null && !searchName.isEmpty()) {
            movies = movieService.searchMoviesByTitle(searchName);
        }
        if (searchRating != null && !searchRating.isEmpty()) {
            movies = movieService.searchMoviesByRating(searchRating);
        }
        model.addAttribute("movies", movies);
        return "listMovies";

    }

    @PostMapping("/order")
    public String orderTicket(@RequestParam String title,
                              @RequestParam Integer numTickets,
                              @RequestParam String username,
                              @RequestParam(value = "localDateTime", required = false)
                              @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                              LocalDateTime localDateTime) {

        if (localDateTime == null) {
            localDateTime = LocalDateTime.now();
        }

        TicketOrder order = new TicketOrder(movieService.findByTitle(title),
                numTickets, userService.saveOrUpdateUser(username), localDateTime);

        ShoppingCart shoppingCart = shoppingCartService.saveOrUpdateShoppingCart(userService.findByUsername(username),
                LocalDateTime.now(), order);

        order.setShoppingCart(shoppingCart);

        ticketOrderService.save(order);


        // dopolnitelno baranje labs2
        movieService.updateNumOrders(title, numTickets);

        return "redirect:/ticketOrder";
    }

    @GetMapping("/add-form")
    public String getAddMoviePage(Model model) {
        List<Production> productions = productionService.findAll();
        model.addAttribute("productions", productions);
        return "add-movie";
    }

    @GetMapping("/add-production")
    public String getAddProductionPage() {
        return "add-production";
    }

    @GetMapping("/edit-form/{id}")
    public String getEditMovieForm(@PathVariable Long id, Model model) {
        if (movieService.findById(id) != null) {
            Movie movie = movieService.findById(id);
            List<Production> productions = productionService.findAll();
            model.addAttribute("productions", productions);
            model.addAttribute("movie", movie);
            return "add-movie";
        }
        return "redirect:/movies?error=Movie%20Not%20Found";
    }

    @PostMapping("/add")
    public String saveOrEditMovie(@RequestParam String title,
                                  @RequestParam String summary,
                                  @RequestParam String rating,
                                  @RequestParam String production,
                                  @RequestParam String movieId) {
        Production p = productionService.findByName(production);
        if (movieId != null && !movieId.isEmpty()) {
            movieService.edit(Long.parseLong(movieId), title, summary, rating, p);
        } else {
            movieService.save(new Movie(title, summary, Double.parseDouble(rating), p));
        }
        return "redirect:/movies";
    }

    @GetMapping("/delete/{id}")
    public String deleteMovie(@PathVariable Long id) {
        movieService.delete(id);
        return "redirect:/movies";
    }


}
