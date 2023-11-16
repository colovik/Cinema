package mk.ukim.finki.wp.lab.web;

import mk.ukim.finki.wp.lab.model.Movie;
import mk.ukim.finki.wp.lab.repository.MovieRepository;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ticketOrderServlet", urlPatterns = "/ticketOrder")
public class TicketOrderServlet extends HttpServlet {
    SpringTemplateEngine springTemplateEngine = new SpringTemplateEngine();

    public TicketOrderServlet(SpringTemplateEngine springTemplateEngine) {
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context = new WebContext(req, resp, req.getServletContext());

        context.setVariable("numTickets", req.getParameter("numTickets"));
        context.setVariable("ipAddress", req.getRemoteAddr());
        context.setVariable("selectedMovie", req.getParameter("selectedMovie"));
        context.setVariable("clientName", req.getHeader("User-Agent"));
        context.setVariable("movie", req.getParameter("movie"));
        context.setVariable("movieTickets", req.getParameter("movieTickets"));

        this.springTemplateEngine.process("orderConfirmation.html",context,resp.getWriter());

    }
}
