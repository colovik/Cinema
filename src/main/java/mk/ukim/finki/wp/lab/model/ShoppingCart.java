package mk.ukim.finki.wp.lab.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dateCreated;

    @OneToMany
    private List<TicketOrder> ticketOrders;


    public ShoppingCart(User user, LocalDateTime dateCreated, List <TicketOrder> ticketOrders) {
        this.user = user;
        this.dateCreated = dateCreated;
        this.ticketOrders = ticketOrders;
    }

    public ShoppingCart() {

    }
}
