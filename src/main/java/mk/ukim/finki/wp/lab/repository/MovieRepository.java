package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.model.Movie;
import mk.ukim.finki.wp.lab.model.Production;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static mk.ukim.finki.wp.lab.bootstrap.DataHolder.movieList;

@Repository
public class MovieRepository {

    public List<Movie> findAll() {
        return movieList;
    }

    public List<Movie> searchMovies(String text) {
        List<Movie> result = new ArrayList<>();

        for (Movie m : movieList) {
            if (m.getTitle().toLowerCase().contains(text.toLowerCase())) {
                result.add(m);
            }
        }

        return result;
    }

    public Movie getMovie(String movieName) {
        for (Movie m : movieList) {
            if (Objects.equals(m.getTitle(), movieName))
                return m;
        }
        return null;
    }

    public List<Movie> getMoviesByRating(String rating) {
        List<Movie> result = new ArrayList<>();
        double minRating = Double.parseDouble(rating);
        for (Movie m : movieList) {
            if (m.getRating() >= minRating) {
                result.add(m);
            }
        }
        return result;
    }

    public Movie findById(Long id) {
        for (Movie m : movieList) {
            if (m.getId().equals(id)) {
                return m;
            }
        }
        return null;
    }

    public Movie save(String title, String summary, String rating, Production production) {
        double ratingNum = Double.parseDouble(rating);
        Movie m = new Movie(title, summary, ratingNum, production);
        movieList.add(m);
        return m;
    }

    public Movie edit(Long movieId, String title, String summary, String rating, Production production) {
        Movie m = findById(movieId);
        m.setTitle(title);
        m.setSummary(summary);
        double ratingNum = Double.parseDouble(rating);
        m.setRating(ratingNum);
        m.setProduction(production);
        return m;
    }

    public void delete(Long id) {
        for (Movie m : movieList) {
            if (m.getId().equals(id)) {
                movieList.remove(getMovie(m.getTitle()));
                break;
            }
        }
    }

    public void updateRating(String movie, double rating){
        double newRating;
        for(Movie m : movieList){
            if(m.getTitle().equals(movie)){
                newRating = (m.getRating()+rating)/2;
                m.setRating(newRating);
            }
        }
    }
}
