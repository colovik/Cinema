package mk.ukim.finki.wp.lab.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Production {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String country;

    private String address;

//    @OneToMany(mappedBy = "production")
//    private List<Movie> movies;
//
//    @OneToMany(mappedBy = "production")
//    List<TicketOrder> ticketOrders;

    public Production(String name, String country, String address) {
//        this.id= (long)(Math.random()*1000);
        this.name = name;
        this.country = country;
        this.address = address;
    }

    public Production() {

    }
}
