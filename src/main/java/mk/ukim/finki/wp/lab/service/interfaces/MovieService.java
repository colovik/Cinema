package mk.ukim.finki.wp.lab.service.interfaces;

import mk.ukim.finki.wp.lab.model.Movie;

import java.util.List;

public interface MovieService {
    List<Movie> listAll();
    List<Movie> searchMovies(String text);
    Movie getMovie(String movieName);

    Movie mostOrdered(List<Movie> orderedMovies);

    List<Movie> order(String movie);
}