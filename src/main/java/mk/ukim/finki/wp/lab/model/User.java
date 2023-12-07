package mk.ukim.finki.wp.lab.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Table(name = "users") //ne smee tabela da se vika User (isto kako klasata)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    private String name;

    private String surname;

    @Convert(converter = UserFullnameConverter.class)
    UserFullname userFullname;

    private String password;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate dateOfBirth;

    @OneToMany(mappedBy = "user")
    private List<ShoppingCart> carts;

    @OneToMany(mappedBy = "user")
    List<TicketOrder> ticketOrders;

    public User(String username, String name, String surname, String password,
                LocalDate dateOfBirth, List<ShoppingCart> carts, List<TicketOrder> ticketOrders) {
        this.id = (long) (Math.random() * 1000);
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.carts = carts;
        this.ticketOrders = ticketOrders;
    }

    public User() {

    }

    public User(String username) {
        this.username = username;
    }
}
