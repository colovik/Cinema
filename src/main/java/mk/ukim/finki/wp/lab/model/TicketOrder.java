package mk.ukim.finki.wp.lab.model;

import lombok.Data;

@Data
public class TicketOrder {

    String movieTitle;
    String clientName;
    String clientAddress;
    int numberOfTickets;
    Production production;

    public TicketOrder(String movieTitle, String clientName, String clientAddress, int numberOfTickets, Production production) {
        this.movieTitle = movieTitle;
        this.clientName = clientName;
        this.clientAddress = clientAddress;
        this.numberOfTickets = numberOfTickets;
        this.production = production;
    }
}
