package controller;

import entity.Match;
import exception.AppException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.NewMatchService;
import service.OngoingMatchesService;

import java.io.IOException;

import static jakarta.servlet.http.HttpServletResponse.SC_BAD_REQUEST;

@WebServlet("/new-match")
public class NewMatchController extends AbstractMainController {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("new-match.jsp").forward(req, resp);
    }

    @Override
    protected void handlePost(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String firstPlayerName = req.getParameter("player1");
        String secondPlayerName = req.getParameter("player2");

        if (!isPlayersNamesValid(firstPlayerName, secondPlayerName)) {
            throw new AppException(SC_BAD_REQUEST, "The names of the players should be different and should consist of letters");
        }

        NewMatchService newMatchService = new NewMatchService();
        OngoingMatchesService ongoingMatchesService = new OngoingMatchesService();

        Match newMatch = newMatchService.createNewMatch(firstPlayerName, secondPlayerName);
        String uuid = ongoingMatchesService.addMatch(newMatch);

        resp.sendRedirect("match-score?uuid=" + uuid);
    }


    private boolean isPlayersNamesValid(String firstPlayerName, String secondPlayerName) {
        return !firstPlayerName.isBlank() &&
               !secondPlayerName.isBlank() &&
               !firstPlayerName.equals(secondPlayerName) &&
               firstPlayerName.replaceAll("\\s+", "").matches("^[a-zA-Zа-яА-ЯёЁ]+$") &&
               secondPlayerName.replaceAll("\\s+", "").matches("^[a-zA-Zа-яА-ЯёЁ]+$");
    }
}
