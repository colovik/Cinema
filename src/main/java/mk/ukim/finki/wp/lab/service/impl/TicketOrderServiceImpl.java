package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.TicketOrder;
import mk.ukim.finki.wp.lab.model.User;
import mk.ukim.finki.wp.lab.repository.jpaTicketOrderRepository;
import mk.ukim.finki.wp.lab.service.interfaces.TicketOrderService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TicketOrderServiceImpl implements TicketOrderService {

    private final jpaTicketOrderRepository ticketOrderRepository;

    public TicketOrderServiceImpl(jpaTicketOrderRepository ticketRepository) {
        this.ticketOrderRepository = ticketRepository;
    }

    @Override
    public List<TicketOrder> findAll() {
        return ticketOrderRepository.findAll();
    }

    @Override
    public List<String> getAllClientsWithOrders() {
        List<String> result = new ArrayList<String>();
        for (TicketOrder t : ticketOrderRepository.findAll()){
            if (!result.contains(t.getUser().getUsername())){
                result.add(t.getUser().getUsername());
            }
        }
        return result;
    }

    @Override
    public List<TicketOrder> findTicketsByUser(String name) {
        return ticketOrderRepository.findAllByUserUsername(name);
    }

    @Override
    public TicketOrder save(TicketOrder ticketOrder) {
        return ticketOrderRepository.save(ticketOrder);
    }

    @Override
    public List<TicketOrder> getOrdersInTimeInterval(LocalDateTime from, LocalDateTime to) {
        if (from == null || to == null) {
            return ticketOrderRepository.findAll();
        }
        return ticketOrderRepository.findOrdersInTimeInterval(from, to);
    }
}
