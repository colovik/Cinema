package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Production;
import mk.ukim.finki.wp.lab.repository.jpaProductionRepository;
import mk.ukim.finki.wp.lab.service.interfaces.ProductionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductionServiceImpl implements ProductionService {
    private final jpaProductionRepository productionRepository;

    public ProductionServiceImpl(jpaProductionRepository productionRepository) {
        this.productionRepository = productionRepository;
    }

    @Override
    public List<Production> findAll() {
        return productionRepository.findAll();
    }

    @Override
    public void save(Production production) {
        productionRepository.save(production);
    }

    @Override
    public Production findByName(String name) {
        return productionRepository.findByName(name);
    }

}
