package mk.ukim.finki.wp.lab.service.interfaces;

import mk.ukim.finki.wp.lab.model.ShoppingCart;
import mk.ukim.finki.wp.lab.model.TicketOrder;
import mk.ukim.finki.wp.lab.model.User;

import java.time.LocalDateTime;
import java.util.List;

public interface TicketOrderService{
    List<TicketOrder> findAll();

    List<String> getAllClientsWithOrders();

    List<TicketOrder> findTicketsByUser(String name);

    TicketOrder save(TicketOrder ticketOrder);

    List<TicketOrder> getOrdersInTimeInterval(LocalDateTime from, LocalDateTime to);
}