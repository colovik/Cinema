package mk.ukim.finki.wp.lab.service.interfaces;

import mk.ukim.finki.wp.lab.model.TicketOrder;

import java.util.List;

public interface TicketOrderService{
    void placeOrder(String movieTitle, String clientName, String address, int numberOfTickets);

    List<TicketOrder> findAll();

    List<String> getAllClients();

    List<TicketOrder> findTicketsByUser(String name);
}