package mk.ukim.finki.wp.lab.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class TicketOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    Movie movie; //za movie title

    int numberOfTickets;

    @ManyToOne
    User user; //za clientName (username)

    @ManyToOne
    ShoppingCart shoppingCart;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dateCreated;

    //    String clientName;
    //    String clientAddress;

    //    @ManyToOne
    //    Production production;


    public TicketOrder(Movie movie, int numberOfTickets, User user, LocalDateTime dateCreated) {
        this.movie = movie;
        this.numberOfTickets = numberOfTickets;
        this.user = user;
        this.dateCreated = dateCreated;
    }

    public TicketOrder() {

    }
}
