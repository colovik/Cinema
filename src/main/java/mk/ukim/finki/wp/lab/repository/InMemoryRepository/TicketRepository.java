package mk.ukim.finki.wp.lab.repository.InMemoryRepository;

import mk.ukim.finki.wp.lab.model.TicketOrder;
import org.springframework.stereotype.Repository;

import java.util.List;

import static mk.ukim.finki.wp.lab.bootstrap.DataHolder.ticketOrders;

@Repository
public class TicketRepository {

    public List<TicketOrder> findAll() {
        return ticketOrders;
    }
}