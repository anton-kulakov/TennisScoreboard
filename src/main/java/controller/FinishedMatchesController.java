package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Page;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/matches")
public class FinishedMatchesController extends AbstractMainController {
    private final static int DEFAULT_PAGE_NUMBER = 1;
    private final static int DEFAULT_MATCHES_PER_PAGE_NUMBER = 5;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int currentPageNumber = DEFAULT_PAGE_NUMBER;
        int matchesPerPageNumber = DEFAULT_MATCHES_PER_PAGE_NUMBER;

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
