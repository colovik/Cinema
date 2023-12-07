package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface jpaUserRepository extends JpaRepository<User, Long> {

    User findByUsername(String clientName);

}
