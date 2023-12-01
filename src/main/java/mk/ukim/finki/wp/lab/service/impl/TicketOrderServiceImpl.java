package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.TicketOrder;
import mk.ukim.finki.wp.lab.repository.MovieRepository;
import mk.ukim.finki.wp.lab.repository.TicketRepository;
import mk.ukim.finki.wp.lab.service.interfaces.TicketOrderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketOrderServiceImpl implements TicketOrderService {

    private final TicketRepository ticketRepository;
    private final MovieRepository movieRepository;

    public TicketOrderServiceImpl(TicketRepository ticketRepository, MovieRepository movieRepository) {
        this.ticketRepository = ticketRepository;
        this.movieRepository = movieRepository;
    }

    @Override
    public void placeOrder(String movieTitle, String clientName, String address, int numberOfTickets) {
        TicketOrder order = new TicketOrder(movieTitle, clientName, address, numberOfTickets, movieRepository.getMovie(movieTitle).getProduction());
        DataHolder.ticketOrders.add(order);
        movieRepository.getMovie(movieTitle).setNum_orders
                (movieRepository.getMovie(movieTitle).getNum_orders() + numberOfTickets);
        // ja zgolemuva promenlivata sho broi kolku karti se kupeni za sekoj film
    }

    @Override
    public List<TicketOrder> findAll() {
        return ticketRepository.findAll();
    }

    @Override
    public List<String> getAllClients() {
        return ticketRepository.findAllClients();
    }

    @Override
    public List<TicketOrder> findTicketsByUser(String name) {
       return ticketRepository.findTicketsByUser(name);
    }


}
