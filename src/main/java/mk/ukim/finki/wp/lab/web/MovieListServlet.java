package mk.ukim.finki.wp.lab.web;

import mk.ukim.finki.wp.lab.model.Movie;
import mk.ukim.finki.wp.lab.model.TicketOrder;
import mk.ukim.finki.wp.lab.service.interfaces.MovieService;
import mk.ukim.finki.wp.lab.service.interfaces.TicketOrderService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "movieListServlet", urlPatterns = "/Movies")
public class MovieListServlet extends HttpServlet {
    private final MovieService movieService;
    private final SpringTemplateEngine springTemplateEngine;
    private final TicketOrderService ticketOrder;

    public MovieListServlet(SpringTemplateEngine ste, MovieService ms, SpringTemplateEngine springTemplateEngine, TicketOrderService ticketOrder) {
        this.movieService = ms;
        this.springTemplateEngine = springTemplateEngine;
        this.ticketOrder = ticketOrder;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context = new WebContext(req, resp, req.getServletContext());

        String searchName = req.getParameter("searchName");
        String searchRating = req.getParameter("searchRating");

        List<Movie> movies = movieService.searchMovies(searchName, searchRating);

        context.setVariable("movies", movies);
        this.springTemplateEngine.process("listMovies.html", context, resp.getWriter());
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context = new WebContext(req, resp, req.getServletContext());

        int numTickets = Integer.parseInt(req.getParameter("numTickets"));
        String selectedMovie = req.getParameter("selectedMovie");
        String clientName = req.getHeader("User-Agent");
        String clientAddress = req.getRemoteAddr();

        List<TicketOrder> allTicketOrderesPlaced = ticketOrder.placeOrder(selectedMovie,clientName,clientAddress,numTickets);
        List<String> allMovies = ticketOrder.getOrderedMovies(allTicketOrderesPlaced);
        Movie m = movieService.mostOrdered(allMovies);

        String movie = m.getTitle();
        int movieTickets = m.getNum_orders();

        resp.sendRedirect("/ticketOrder?clientName=" + clientName + "&selectedMovie=" + selectedMovie +
                "&numTickets=" + numTickets + "&clientAddress=" + clientAddress + "&movie=" + movie
                + "&movieTickets=" + movieTickets);
    }
}
