package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.model.ShoppingCart;
import mk.ukim.finki.wp.lab.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface jpaShoppingCartRepository extends JpaRepository<ShoppingCart, Integer> {
    ShoppingCart findByUser(User user);
}
