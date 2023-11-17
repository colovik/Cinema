package mk.ukim.finki.wp.lab.service.interfaces;

import mk.ukim.finki.wp.lab.model.TicketOrder;

import java.util.List;

public interface TicketOrderService{
    TicketOrder placeOrder(String movieTitle, String clientName, String address, int numberOfTickets);

    List<String> getOrderedMovies(List<TicketOrder> orders);

    List<TicketOrder> findAll();
    List<String> findAllClients();
}