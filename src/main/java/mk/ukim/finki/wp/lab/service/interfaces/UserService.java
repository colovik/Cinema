package mk.ukim.finki.wp.lab.service.interfaces;

import mk.ukim.finki.wp.lab.model.TicketOrder;
import mk.ukim.finki.wp.lab.model.User;

import java.time.LocalDateTime;
import java.util.List;


public interface UserService {
    List<User> findAll();

    User findByUsername(String clientName);

    User saveOrUpdateUser(String username);


}
