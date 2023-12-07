package mk.ukim.finki.wp.lab.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    String title;

    String summary;

    double rating;

    int numOrders;

    @ManyToOne
    private Production production;

//    @OneToMany(mappedBy = "movie")
//    private List<TicketOrder> ticketOrders;

    public Movie(String title, String summary, double rating, Production production) {
        this.title = title;
        this.summary = summary;
        this.rating = rating;
        this.numOrders = 0;
        this.production = production;
    }

    public Movie() {

    }


}