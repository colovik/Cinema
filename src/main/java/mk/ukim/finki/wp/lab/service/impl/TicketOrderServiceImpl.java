package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.TicketOrder;
import mk.ukim.finki.wp.lab.repository.MovieRepository;
import mk.ukim.finki.wp.lab.repository.TicketRepository;
import mk.ukim.finki.wp.lab.service.interfaces.TicketOrderService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public TicketOrder placeOrder(String movieTitle, String clientName, String address, int numberOfTickets) {
        TicketOrder order = new TicketOrder(movieTitle, clientName, address, numberOfTickets);
        DataHolder.ticketOrders.add(order);
        List<TicketOrder> tickets = ticketRepository.findAll();
        movieRepository.getMovie(movieTitle).setNum_orders
                (movieRepository.getMovie(movieTitle).getNum_orders() + numberOfTickets);
        // ja zgolemuva promenlivata sho broi kolku karti se kupeni za sekoj film
        return order;
    }

    List<String> uniqueMovieNamesOrdered = new ArrayList<>();

    @Override
    public List<String> getOrderedMovies(List<TicketOrder> orders) {

        for (TicketOrder t : orders) {
            if (uniqueMovieNamesOrdered.contains(t.getMovieTitle())) {
                continue;
            }
            uniqueMovieNamesOrdered.add(t.getMovieTitle());
        }
        //posle ova treba da imam lista od iminjata na site filmovi za koi bile kupeni karti
        return uniqueMovieNamesOrdered;
    }

    @Override
    public List<TicketOrder> findAll() {
        return ticketRepository.findAll();
    }

    @Override
    public List<String> findAllClients() {
        return ticketRepository.findAllClients();
    }


}
