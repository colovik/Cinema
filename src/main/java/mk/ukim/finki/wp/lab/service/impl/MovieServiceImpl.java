package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Movie;
import mk.ukim.finki.wp.lab.model.Production;
import mk.ukim.finki.wp.lab.repository.MovieRepository;
import mk.ukim.finki.wp.lab.service.interfaces.MovieService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;

    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public List<Movie> listAll() {
        return this.movieRepository.findAll();
    }

    @Override
    public List<Movie> searchMoviesByName(String searchName) {
        List<Movie> movies = movieRepository.findAll();

        if (searchName != null && !searchName.isEmpty()) {
            movies = movieRepository.searchMovies(searchName);
        }
        return movies;
    }

    @Override
    public List<Movie> searchMoviesByRating(String searchRating){
        List<Movie> movies = movieRepository.findAll();

        if (searchRating != null && !searchRating.isEmpty()) {
            movies = movieRepository.getMoviesByRating(searchRating);
        }

        return movies;
    }

    @Override
    public Movie mostOrdered(List<String> orderedMoviesNames) {
        int max = 0;
        Movie mo = new Movie();
        for (String movieName : orderedMoviesNames) {
            Movie m = movieRepository.getMovie(movieName);
            if (m.getNum_orders() >= max) {
                max = m.getNum_orders();
                mo = m;
            }
        }
        return mo;
    }

    @Override
    public Movie getMovie(String movieTitle) {
        return movieRepository.getMovie(movieTitle);
    }

    @Override
    public Movie save(String title, String summary, String rating, Production production) {
        return movieRepository.save(title, summary, rating, production);
    }

    @Override
    public Movie edit(Long movieId, String title, String summary, String rating, Production production) {
        return movieRepository.edit(movieId, title, summary, rating, production);
    }

    @Override
    public void delete(Long id) {
        movieRepository.delete(id);
    }

    @Override
    public Movie findById(Long id) {
        return movieRepository.findById(id);
    }
}
