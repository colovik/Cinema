package mk.ukim.finki.wp.lab.service.implementations;

import mk.ukim.finki.wp.lab.model.Movie;
import mk.ukim.finki.wp.lab.model.TicketOrder;
import mk.ukim.finki.wp.lab.repository.MovieRepository;
import mk.ukim.finki.wp.lab.repository.TicketRepository;
import mk.ukim.finki.wp.lab.service.interfaces.MovieService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieServiceImplementation implements MovieService {
    private final MovieRepository movieRepository;
    private final TicketRepository ticketRepository;

    public MovieServiceImplementation(MovieRepository movieRepository, TicketRepository ticketRepository) {
        this.movieRepository = movieRepository;
        this.ticketRepository = ticketRepository;
    }

    @Override
    public List<Movie> listAll() {
        return this.movieRepository.findAll();
    }

    @Override
    public List<Movie> searchMovies(String searchName, String searchRating) {
        List<Movie> movies = movieRepository.findAll();

        if (searchName != null && !searchName.isEmpty()) {
            movies = movieRepository.searchMovies(searchName);
        }

        if (searchRating != null && !searchRating.isEmpty()) {
            float minRating = Float.parseFloat(searchRating);
            movies = movies.stream()
                    .filter(movie -> movie.getRating() >= minRating)
                    .collect(Collectors.toList()); //ovaa logika treba da bide vo service delot
        }

        return movies;
    }

//    @Override
//    public Movie getOrderedMovies(List<TicketOrder> orders) {
//        orders.
//    }


    @Override
    public Movie mostOrdered(List<String> orderedMoviesNames) {
        int max = 0;
        Movie mo = new Movie();
        for (String movieName : orderedMoviesNames){
            Movie m = movieRepository.getMovie(movieName);
            if (m.getNum_orders() >= max){
                max=m.getNum_orders();
                mo = m;
            }
        }
        return mo;
    }

//    List<Movie> orders = new ArrayList<Movie>();
//
//    @Override
//    public List<Movie> order(String movie) {
//        getMovie(movie).setNum_orders(getMovie(movie).getNum_orders()+1);
//        orders.add(getMovie(movie));
//        return orders;
//    }
}
