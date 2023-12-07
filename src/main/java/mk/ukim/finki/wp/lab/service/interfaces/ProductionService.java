package mk.ukim.finki.wp.lab.service.interfaces;

import mk.ukim.finki.wp.lab.model.Production;

import java.util.List;

public interface ProductionService {

    List<Production> findAll();

    Production findByName(String name);

    void save(Production production);

}
