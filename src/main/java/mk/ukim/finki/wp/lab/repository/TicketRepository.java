package mk.ukim.finki.wp.lab.repository;

import lombok.Data;
import mk.ukim.finki.wp.lab.model.TicketOrder;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static mk.ukim.finki.wp.lab.bootstrap.DataHolder.ticketOrders;

@Repository
public class TicketRepository {

    public List<TicketOrder> findAll() {
        return ticketOrders;
    }

    public List<String> findAllClients() {
        List<String> result = new ArrayList<>();
        for (TicketOrder t : ticketOrders) {
            if (!result.contains(t.getClientName())) {
                result.add(t.getClientName());
            }
        }
        return result;
    }
}
