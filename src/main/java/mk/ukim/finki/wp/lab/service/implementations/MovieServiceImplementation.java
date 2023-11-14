package mk.ukim.finki.wp.lab.service.implementations;

import mk.ukim.finki.wp.lab.model.Movie;
import mk.ukim.finki.wp.lab.repository.MovieRepository;
import mk.ukim.finki.wp.lab.service.interfaces.MovieService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class MovieServiceImplementation implements MovieService {
    private final MovieRepository movieRepository;

    public MovieServiceImplementation(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public List<Movie> listAll() {
        return this.movieRepository.findAll();
    }

    @Override
    public List<Movie> searchMovies(String text) {
        return this.movieRepository.searchMovies(text);
    }

    @Override
    public Movie getMovie(String movieName) {
        for (Movie m : this.movieRepository.findAll()){
            if (Objects.equals(m.getTitle(), movieName))
                return m;
        }
        return null;
    }

    @Override
    public Movie mostOrdered(List<Movie> orderedMovies) {
        int max = 0;
        Movie mo = new Movie();
        for (Movie m : orderedMovies){
            if (m.getNum_orders() >= max){
                max=m.getNum_orders();
                mo = m;
            }
            return mo;
        }

        return null;
    }

    List<Movie> orders = new ArrayList<Movie>();

    @Override
    public List<Movie> order(String movie) {
        getMovie(movie).setNum_orders(getMovie(movie).getNum_orders()+1);
        orders.add(getMovie(movie));
        return orders;
    }
}
