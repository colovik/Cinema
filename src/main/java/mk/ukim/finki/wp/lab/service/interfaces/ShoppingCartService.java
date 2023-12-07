package mk.ukim.finki.wp.lab.service.interfaces;

import mk.ukim.finki.wp.lab.model.ShoppingCart;
import mk.ukim.finki.wp.lab.model.TicketOrder;
import mk.ukim.finki.wp.lab.model.User;

import java.time.LocalDateTime;
import java.util.List;

public interface ShoppingCartService {

    List<ShoppingCart> findAll();

    ShoppingCart saveOrUpdateShoppingCart(User user, LocalDateTime localDateTime, TicketOrder order);


}
