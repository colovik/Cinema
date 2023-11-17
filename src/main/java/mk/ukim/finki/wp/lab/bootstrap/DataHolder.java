package mk.ukim.finki.wp.lab.bootstrap;

import mk.ukim.finki.wp.lab.model.Movie;
import mk.ukim.finki.wp.lab.model.Production;
import mk.ukim.finki.wp.lab.model.TicketOrder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Movie> movieList = new ArrayList<>();
    public static List<TicketOrder> ticketOrders = new ArrayList<>();
    public static List<Production> productions = new ArrayList<>();

    @PostConstruct
    public void init(){
        productions = new ArrayList<>();
        productions.add(new Production("Warner Bros.","USA","4000 Warner Blvd., Burbank, California, US"));
        productions.add(new Production("Paramount Pictures","USA","5555 Melrose Avenue, Hollywood, California, U.S."));
        productions.add(new Production("Universal City Studios","USA","10 Universal City Plaza, Universal City, California, U.S."));
        productions.add(new Production("Marvel Studios","USA","500 South Buena Vista Street, Burbank, California, U.S."));
        productions.add(new Production("DreamWorks Pictures","USA","100 Universal City Plaza, Universal City, California, U.S."));

        movieList.add(new Movie("The Shawshank Redemption", "Biography, Drama, Romance", 7,productions.get(0)));
        movieList.add(new Movie("The Godfather", "Crime, Drama", 9,productions.get(0)));
        movieList.add(new Movie("Pulp Fiction", "Crime, Drama", 8,productions.get(1)));
        movieList.add(new Movie("The Dark Knight", "Action, Crime, Drama", 8,productions.get(1)));
        movieList.add(new Movie("Schindler's List", "Biography, Drama, History", 9,productions.get(2)));
        movieList.add(new Movie("Forrest Gump", "Drama, Romance", 7,productions.get(2)));
        movieList.add(new Movie("Inception", "Action, Adventure, Sci-Fi", 8,productions.get(3)));
        movieList.add(new Movie("The Matrix", "Action, Sci-Fi", 7,productions.get(3)));
        movieList.add(new Movie("The Silence of the Lambs", "Crime, Drama, Thriller", 8,productions.get(4)));
        movieList.add(new Movie("Gladiator", "Action, Adventure, Drama", 8,productions.get(4)));
    }

}
