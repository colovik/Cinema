package mk.ukim.finki.wp.lab.service.interfaces;

import mk.ukim.finki.wp.lab.model.Movie;
import mk.ukim.finki.wp.lab.model.TicketOrder;

import java.util.List;

public interface MovieService {
    List<Movie> listAll();
    List<Movie> searchMovies(String text, String searchRating);
    //Movie getOrderedMovies(List<TicketOrder> orders);

    Movie mostOrdered(List<String> orderedMoviesNames);

    //List<Movie> order(String movie);
}