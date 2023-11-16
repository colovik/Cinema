package mk.ukim.finki.wp.lab.model;

import lombok.Data;

@Data
public class Movie {
    String title;
    String summary;
    double rating;
    int num_orders;

    public Movie() {
    }

    public Movie(String title, String summary, double rating) {
        this.title = title;
        this.summary = summary;
        this.rating = rating;
        this.num_orders = 0;
    }
}