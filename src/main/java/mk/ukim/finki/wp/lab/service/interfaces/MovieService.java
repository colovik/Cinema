package mk.ukim.finki.wp.lab.service.interfaces;

import mk.ukim.finki.wp.lab.model.Movie;
import mk.ukim.finki.wp.lab.model.Production;

import java.util.List;

public interface MovieService {
    List<Movie> listAll();

    List<Movie> searchMoviesByName(String searchName);

    Movie mostOrdered(List<Movie> orderedMoviesNames);

    Movie save(String title, String summary, String rating, Production production);
    Movie edit(Long movieId,String title, String summary, String rating, Production production);

    void delete(Long id);

    Movie findById(Long id);
    List<Movie> searchMoviesByRating(String searchRating);
    void updateRating(String movie,int rating);

}