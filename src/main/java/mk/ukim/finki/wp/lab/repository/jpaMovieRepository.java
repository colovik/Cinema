package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface jpaMovieRepository extends JpaRepository<Movie, Integer> {

    List<Movie> findByTitleIgnoreCaseContaining(String text);

    List<Movie> findByRatingGreaterThanEqual(double rating);

    Movie findByTitle(String movieTitle);

    Movie findFirstByOrderByNumOrdersDesc();

    Movie findById(Long id);

}
