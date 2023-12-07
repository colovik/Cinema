package mk.ukim.finki.wp.lab.service.interfaces;

import mk.ukim.finki.wp.lab.model.Movie;
import mk.ukim.finki.wp.lab.model.Production;

import java.util.List;

public interface MovieService {
    List<Movie> findAll();

    Movie findByTitle(String title);

    Movie findById(Long id);

    List<Movie> searchMoviesByTitle(String searchName);

    List<Movie> searchMoviesByRating(String searchRating);

    Movie getMostOrderedMovie(List<Movie> orderedMoviesNames);

    void edit(Long movieId,String title, String summary, String rating, Production production);

    void delete(Long id);

    void save(Movie movie);

    void updateRating(String title,double rating);

    void updateNumOrders(String title, Integer numTickets);
}