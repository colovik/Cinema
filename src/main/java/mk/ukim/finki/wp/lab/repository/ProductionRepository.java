package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.model.Production;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static mk.ukim.finki.wp.lab.bootstrap.DataHolder.productions;

@Repository
public class ProductionRepository {

    public List<Production> findAll() {
        return productions;
    }

    public Production findById(String id) {
        for (Production p : productions) {
            if (p.getId().toString().equals(id)) {
                return p;
            }
        }
        return null;
    }
}