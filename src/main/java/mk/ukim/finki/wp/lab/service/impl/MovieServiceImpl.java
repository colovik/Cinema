package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Movie;
import mk.ukim.finki.wp.lab.model.Production;
import mk.ukim.finki.wp.lab.repository.MovieRepository;
import mk.ukim.finki.wp.lab.service.interfaces.MovieService;
import org.springframework.stereotype.Service;


import java.util.List;

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
    public Movie mostOrdered(List<Movie> orderedMovies) {
        int max = 0;
        Movie mo = new Movie();
        for (Movie m : orderedMovies) {
            if (m.getNum_orders() >= max) {
                max = m.getNum_orders();
                mo = m;
            }
        }
        return mo;
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

    public void updateRating(String movie,int rating){
        movieRepository.updateRating(movie,rating);
    }
}
