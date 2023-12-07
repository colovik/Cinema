package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.model.Production;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface jpaProductionRepository extends JpaRepository<Production, Integer> {

    Production findByName(String name);
}