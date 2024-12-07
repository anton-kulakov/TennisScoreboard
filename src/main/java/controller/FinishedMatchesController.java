package controller;

import dao.MatchDAO;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Page;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/matches")
public class FinishedMatchesController extends AbstractMainController {
    MatchDAO matchDAO;

    public void init(ServletConfig config) {
        matchDAO = (MatchDAO) config.getServletContext().getAttribute("matchDAO");
    }
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int currentPageNumber = 1;
        int matchesPerPageNumber = 5;

        Optional<String> stringCurrentPageNumber = Optional.ofNullable(req.getParameter("page"));
        String playerName = req.getParameter("filter_by_player_name");

        if (stringCurrentPageNumber.isPresent() && !stringCurrentPageNumber.get().isBlank()) {
            currentPageNumber = Integer.parseInt(stringCurrentPageNumber.get());
        }

        Page page;

        if (playerName.isBlank()) {
            page = matchDAO.getAll(currentPageNumber, matchesPerPageNumber);
        } else {
            page = matchDAO.getByName(currentPageNumber, matchesPerPageNumber, playerName);
        }

        req.setAttribute("page", page);
        req.getRequestDispatcher("finished-matches.jsp").forward(req, resp);
    }
}
