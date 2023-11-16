package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.model.TicketOrder;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TicketRepository {

    ArrayList<TicketOrder> ticketOrders = new ArrayList<>();

    public List<TicketOrder> findAll(){
        return ticketOrders;
    }

    public List<TicketOrder> searchTicketOrders(String movieName){
        List<TicketOrder> result = new ArrayList<>();

        for(TicketOrder t : ticketOrders){
            if (t.getMovieTitle().equals(movieName)) {
                result.add(t);
            }
        }

        return result;
    }

    public void add(TicketOrder ticket){
        ticketOrders.add(ticket);
    }
}
