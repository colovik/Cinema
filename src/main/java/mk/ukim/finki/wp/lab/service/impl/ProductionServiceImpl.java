package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Movie;
import mk.ukim.finki.wp.lab.model.Production;
import mk.ukim.finki.wp.lab.repository.MovieRepository;
import mk.ukim.finki.wp.lab.repository.ProductionRepository;
import mk.ukim.finki.wp.lab.service.interfaces.ProductionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductionServiceImpl implements ProductionService {
    private final ProductionRepository productionRepository;
    private final MovieRepository movieRepository;

    public ProductionServiceImpl(ProductionRepository productionRepository, MovieRepository movieRepository) {
        this.productionRepository = productionRepository;
        this.movieRepository = movieRepository;
    }

    @Override
    public List<Production> findAll() {
        return productionRepository.findAll();
    }

    @Override
    public Production findById(String id) {
        return productionRepository.findById(id);
    }

    @Override
    public Production findByMovieTitle(String title) {
        for(Movie m : movieRepository.findAll()){
            if(m.getTitle().equals(title)){
                return m.getProduction();
            }
        }
        return null;
    }
}
