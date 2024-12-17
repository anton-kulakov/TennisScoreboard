package controller;

import dao.MatchDAO;
import dao.PlayerDAO;
import entity.Match;
import entity.Player;
import exception.AppException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.HibernateException;
import service.MatchResultService;
import service.NewMatchService;
import service.OngoingMatchesService;
import util.PlayerNameValidator;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

import static jakarta.servlet.http.HttpServletResponse.SC_INTERNAL_SERVER_ERROR;

public abstract class AbstractMainController extends HttpServlet {
    protected static final PlayerNameValidator playerNameValidator;
    protected static final MatchDAO matchDAO;
    protected static final NewMatchService newMatchService;
    protected static final OngoingMatchesService ongoingMatchesService;
    protected static final MatchResultService matchResultService;

    static {
        playerNameValidator = new PlayerNameValidator();
        matchDAO = new MatchDAO();
        newMatchService = new NewMatchService(new PlayerDAO());
        ongoingMatchesService = new OngoingMatchesService(new ConcurrentHashMap<>());
        matchResultService = new MatchResultService();

        insertTestData();
    }

    protected void service(HttpServletRequest req, HttpServletResponse resp) {
        try {
            super.service(req, resp);
        } catch (AppException e) {
            sendError(e.code, e.message, req, resp);
        } catch (HibernateException e) {
            sendError(SC_INTERNAL_SERVER_ERROR, "Something happened with the database. Please try again later!", req, resp);
        } catch (Exception e) {
            sendError(SC_INTERNAL_SERVER_ERROR, "Fatal error", req, resp);
        }
    }

    private void sendError(int code, String message, HttpServletRequest req, HttpServletResponse resp) {
        try {
            req.setAttribute("errorMessage", message);
            req.getRequestDispatcher("/error.jsp").forward(req, resp);
            resp.setStatus(code);
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void insertTestData() {
        Player player1 = new Player("Rafael Nadal");
        Player player2 = new Player("Maria Sharapova");
        Player player3 = new Player("Novak Djokovic");
        Player player4 = new Player("Serena Williams");
        Player player5 = new Player("Stan Wawrinka");
        Player player6 = new Player("Andrea Carrera");
        Player player7 = new Player("Olga Belova");
        Player player8 = new Player("Max Simonov");
        Player player9 = new Player("John Isner");
        Player player10 = new Player("Li Jian");

        Player player11 = new Player("Alexander Zverev");
        Player player12 = new Player("Simona Halep");
        Player player13 = new Player("Venus Williams");
        Player player14 = new Player("Bianca Andreescu");
        Player player15 = new Player("Garbine Muguruza");
        Player player16 = new Player("Madison Keys");
        Player player17 = new Player("Petra Kvitova");
        Player player18 = new Player("Felix Auger-Aliassime");
        Player player19 = new Player("Ashleigh Barty");
        Player player20 = new Player("Karolina Pliskova");

        matchDAO.merge(new Match(player1, player2, player1));  // Rafael Nadal vs Maria Sharapova, victory for Rafael Nadal
        matchDAO.merge(new Match(player3, player4, player3));  // Novak Djokovic vs Serena Williams, victory for Novak Djokovic
        matchDAO.merge(new Match(player5, player6, player6));  // Stan Wawrinka vs Andrea Carrera, victory for Stan Wawrinka
        matchDAO.merge(new Match(player7, player8, player7));  // Olga Belova vs Max Simonov, victory for Olga Belova
        matchDAO.merge(new Match(player9, player10, player10)); // John Isner vs Li Jian, victory for John Isner

        matchDAO.merge(new Match(player11, player12, player12));  // Alexander Zverev vs Simona Halep, victory for Alexander Zverev
        matchDAO.merge(new Match(player13, player14, player14));  // Venus Williams vs Bianca Andreescu, victory for Venus Williams
        matchDAO.merge(new Match(player15, player16, player15));  // Garbine Muguruza vs Madison Keys, victory for Garbine Muguruza
        matchDAO.merge(new Match(player17, player18, player18));  // Petra Kvitova vs Felix Auger-Aliassime, victory for Petra Kvitova
        matchDAO.merge(new Match(player19, player20, player19));  // Ashleigh Barty vs Karolina Pliskova, victory for Ashleigh Barty
    }
}
