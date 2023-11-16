package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.model.Movie;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class MovieRepository {
    List<Movie> movieList = new ArrayList<Movie>(10);

    public MovieRepository() {
        movieList = new ArrayList<>();
        movieList.add(new Movie("The Shawshank Redemption", "Biography, Drama, Romance", 7));
        movieList.add(new Movie("The Godfather", "Crime, Drama", 9));
        movieList.add(new Movie("Pulp Fiction", "Crime, Drama", 8));
        movieList.add(new Movie("The Dark Knight", "Action, Crime, Drama", 8));
        movieList.add(new Movie("Schindler's List", "Biography, Drama, History", 9));
        movieList.add(new Movie("Forrest Gump", "Drama, Romance", 7));
        movieList.add(new Movie("Inception", "Action, Adventure, Sci-Fi", 8));
        movieList.add(new Movie("The Matrix", "Action, Sci-Fi", 7));
        movieList.add(new Movie("The Silence of the Lambs", "Crime, Drama, Thriller", 8));
        movieList.add(new Movie("Gladiator", "Action, Adventure, Drama", 8));
    }

    public List<Movie> findAll(){
        return movieList;
    }

    public List<Movie> searchMovies(String text){
        List<Movie> result = new ArrayList<Movie>();

        for (Movie m: movieList){
            if(m.getTitle().contains(text) || m.getSummary().contains(text)){
                result.add(m);
            }
        }

        return result;
    }

    public Movie getMovie(String movieName) {
        for (Movie m : movieList){
            if (Objects.equals(m.getTitle(), movieName))
                return m;
        }
        return null;
    }
}
