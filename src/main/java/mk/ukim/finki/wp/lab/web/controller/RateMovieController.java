package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Movie;
import mk.ukim.finki.wp.lab.service.interfaces.MovieService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
//dopolnitelno baranje labs2
//da se dodade strana kade shto ke se ovozmozi dodavanje rejting
// za film koj se selektira od lista na ponudeni
@Controller
@RequestMapping("/rate")
public class RateMovieController {

    private final MovieService movieService;

    public RateMovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public String getRateMoviePage(Model model){
        List<Movie> movies = movieService.listAll();
        model.addAttribute("movies",movies);
        return "addMovieRating";
    }

    @PostMapping
    public String rateMovie(@RequestParam String movie, @RequestParam int rating){
        movieService.updateRating(movie,rating);
        return "redirect:/movies";
    }
}
