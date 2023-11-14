package mk.ukim.finki.wp.lab.web;

import mk.ukim.finki.wp.lab.model.Movie;
import mk.ukim.finki.wp.lab.service.interfaces.MovieService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "movieListServlet", urlPatterns = "/Movies")
public class MovieListServlet extends HttpServlet {
    private final MovieService movieService;
    private final SpringTemplateEngine springTemplateEngine;

    public MovieListServlet(SpringTemplateEngine ste, MovieService ms, SpringTemplateEngine springTemplateEngine) {
        this.movieService = ms;
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context = new WebContext(req, resp, req.getServletContext());

        String searchName = req.getParameter("searchName");
        String searchRating = req.getParameter("searchRating");

        List<Movie> movies = movieService.listAll();

        if (searchName != null && !searchName.isEmpty()) {
            movies = movieService.searchMovies(searchName);
        }

        if (searchRating != null && !searchRating.isEmpty()) {
            float minRating = Float.parseFloat(searchRating);
            movies = movies.stream()
                    .filter(movie -> movie.getRating() >= minRating)
                    .collect(Collectors.toList()); //ovaa logika treba da bide vo service delot
        }

        context.setVariable("movies", movies);
        this.springTemplateEngine.process("listMovies.html", context, resp.getWriter());
    }



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context = new WebContext(req, resp, req.getServletContext());

        String number_tickets = req.getParameter("numTickets");
        String numTickets = req.getParameter("numTickets");
        String selectedMovie = req.getParameter("selectedMovie");
        String clientName = req.getHeader("User-Agent");

        // movieService.getMovie(selectedMovie).setNum_orders(movieService.getMovie(selectedMovie).getNum_orders()+1);
        // orders.add(movieService.getMovie(selectedMovie));


        Movie m = movieService.mostOrdered( movieService.order(selectedMovie));

        String movie = m.getTitle();


        resp.sendRedirect("/ticketOrder?clientName=" + clientName + "&selectedMovie=" + selectedMovie + "&numTickets=" + numTickets + "&movie=" + movie);
    }
}
