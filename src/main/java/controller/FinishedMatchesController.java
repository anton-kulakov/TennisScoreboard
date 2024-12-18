package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Page;

import java.io.IOException;

@WebServlet("/matches")
public class FinishedMatchesController extends AbstractMainController {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String stringPage = req.getParameter("page");
        String playerName = req.getParameter("filter_by_player_name");

        Page page = paginationService.getPage(stringPage, playerName, matchDAO);

        req.setAttribute("page", page);
        req.getRequestDispatcher("finished-matches.jsp").forward(req, resp);
    }
}
