package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.ShoppingCart;
import mk.ukim.finki.wp.lab.model.TicketOrder;
import mk.ukim.finki.wp.lab.model.User;
import mk.ukim.finki.wp.lab.repository.jpaShoppingCartRepository;
import mk.ukim.finki.wp.lab.service.interfaces.ShoppingCartService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final jpaShoppingCartRepository shoppingCartRepository;

    public ShoppingCartServiceImpl(jpaShoppingCartRepository shoppingCartRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
    }

    @Override
    public ShoppingCart saveOrUpdateShoppingCart(User user, LocalDateTime localDateTime, TicketOrder order) {
        ShoppingCart existingShoppingCart = shoppingCartRepository.findByUser(user);
        if (existingShoppingCart != null) {
            existingShoppingCart.getTicketOrders().add(order);
            return existingShoppingCart;
        } else {
            ShoppingCart newShoppingCart = shoppingCartRepository.save(new ShoppingCart(user, localDateTime, new ArrayList<>()));
            newShoppingCart.getTicketOrders().add(order);
            return newShoppingCart;
        }
    }

    @Override
    public List<ShoppingCart> findAll() {
        return shoppingCartRepository.findAll();
    }
}
