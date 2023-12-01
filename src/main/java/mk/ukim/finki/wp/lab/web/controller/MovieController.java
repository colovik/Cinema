package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Movie;
import mk.ukim.finki.wp.lab.model.Production;
import mk.ukim.finki.wp.lab.service.interfaces.MovieService;
import mk.ukim.finki.wp.lab.service.interfaces.ProductionService;
import mk.ukim.finki.wp.lab.service.interfaces.TicketOrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;
    private final TicketOrderService ticketOrderService;
    private final ProductionService productionService;

    public MovieController(MovieService movieService, TicketOrderService ticketOrderService, ProductionService productionService) {
        this.movieService = movieService;
        this.ticketOrderService = ticketOrderService;
        this.productionService = productionService;
    }

    @GetMapping
    public String getMoviesPage(Model model) {

        List<Movie> movies = movieService.listAll();
        model.addAttribute("movies", movies);

        return "listMovies";
    }

    @GetMapping("/search")
    public String searchMovies(@RequestParam(required = false) String searchName, @RequestParam(required = false) String searchRating, Model model) {
        List<Movie> movies = new ArrayList<>();
        if ((searchName == null || searchName.isEmpty()) && (searchRating == null || searchRating.isEmpty())) {
            movies = movieService.listAll();
        }
        if (searchName != null && !searchName.isEmpty()) {
            movies = movieService.searchMoviesByName(searchName);
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
                              @RequestParam String name,
                              HttpServletRequest request) {
        String clientAddress = request.getRemoteAddr();
        ticketOrderService.placeOrder(title, name, clientAddress, numTickets);
        return "redirect:/ticketOrder";
    }

    @GetMapping("/add-form")
    public String getAddMoviePage(Model model) {
        List<Production> productions = productionService.findAll();
        model.addAttribute("productions", productions);
        return "add-movie";
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
    public String saveMovie(@RequestParam String title, @RequestParam String summary, @RequestParam String rating, @RequestParam String production, @RequestParam String movieId) {
        Production p = productionService.findById(production);
        if (movieId != null && !movieId.isEmpty()) {
            movieService.edit((long) Integer.parseInt(movieId), title, summary, rating, p);
        } else {
            movieService.save(title, summary, rating, p);
        }
        return "redirect:/movies";
    }

    @GetMapping("/delete/{id}")
    public String deleteMovie(@PathVariable Long id) {
        movieService.delete(id);
        return "redirect:/movies";
    }


}
