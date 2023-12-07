package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Movie;
import mk.ukim.finki.wp.lab.model.Production;
import mk.ukim.finki.wp.lab.repository.jpaMovieRepository;
import mk.ukim.finki.wp.lab.service.interfaces.MovieService;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.List;


@Service
public class MovieServiceImpl implements MovieService {

    private final jpaMovieRepository movieRepository;

    public MovieServiceImpl(jpaMovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public List<Movie> findAll() {
        return this.movieRepository.findAll();
    }

    @Override
    public List<Movie> searchMoviesByTitle(String searchName) {
        return this.movieRepository.findByTitleIgnoreCaseContaining(searchName);
    }

    @Override
    public List<Movie> searchMoviesByRating(String searchRating) {
        return this.movieRepository.findByRatingGreaterThanEqual(Double.parseDouble(searchRating));
    }

    @Override
    public void updateRating(String title, double rating) {
        Movie movie = movieRepository.findByTitle(title);
        movie.setRating((movie.getRating() + rating) / 2);
        this.movieRepository.save(movie);
    }

    @Override
    public Movie findByTitle(String title) {
        return movieRepository.findByTitle(title);
    }


    @Override
    public Movie getMostOrderedMovie(List<Movie> orderedMovies) {
        return this.movieRepository.findFirstByOrderByNumOrdersDesc();
    }


    @Override
    @Transactional
    public void edit(Long movieId, String title, String summary, String rating, Production production) {
        Movie movieToUpdate = findById(movieId);

        movieToUpdate.setTitle(title);
        movieToUpdate.setSummary(summary);
        movieToUpdate.setRating(Double.parseDouble(rating));
        movieToUpdate.setProduction(production);

        save(movieToUpdate);
    }


    @Override
    public void delete(Long id) {
        this.movieRepository.delete(findById(id));

    }

    @Override
    public Movie findById(Long id) {
        return movieRepository.findById(id);
    }


    @Override
    public void save(Movie movie) {
        movieRepository.save(movie);
    }

    // dopolnitelno baranje labs1
    @Override
    @Transactional
    public void updateNumOrders(String title, Integer numTickets) {
        movieRepository.findByTitle(title).setNumOrders
                (movieRepository.findByTitle(title).getNumOrders() + numTickets);
        // ja zgolemuva promenlivata sho broi kolku karti se kupeni za sekoj film
    }
}
