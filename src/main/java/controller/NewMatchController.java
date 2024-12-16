package controller;

import entity.Match;
import exception.AppException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static jakarta.servlet.http.HttpServletResponse.SC_BAD_REQUEST;

@WebServlet("/new-match")
public class NewMatchController extends AbstractMainController {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("new-match.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String firstPlayerName = req.getParameter("player1");
        String secondPlayerName = req.getParameter("player2");

        if(!playerNameValidator.isNameConsistsOfLetters(firstPlayerName)) {
            throw new AppException(SC_BAD_REQUEST,
                    "You have entered a name: %s. The name should consist of letters.".formatted(firstPlayerName));
        }

        if(!playerNameValidator.isNameConsistsOfLetters(secondPlayerName)) {
            throw new AppException(SC_BAD_REQUEST,
                    "You have entered a name: %s. The name should consist of letters.".formatted(secondPlayerName));
        }

        if (playerNameValidator.isEqualsNames(firstPlayerName, secondPlayerName)) {
            throw new AppException(SC_BAD_REQUEST, "The names of the players should be different");
        }

        if (playerNameValidator.isNameLongerThanMaxLength(firstPlayerName)) {
            throw new AppException(SC_BAD_REQUEST,
                    "The name of the first player should be less than %d characters.".formatted(playerNameValidator.getMaxNameLength()));
        }

        if (playerNameValidator.isNameLongerThanMaxLength(secondPlayerName)) {
            throw new AppException(SC_BAD_REQUEST,
                    "The name of the second player should be less than %d characters.".formatted(playerNameValidator.getMaxNameLength()));
        }

        Match newMatch = newMatchService.createNewMatch(firstPlayerName, secondPlayerName);
        String uuid = ongoingMatchesService.add(newMatch);

        resp.sendRedirect("match-score?uuid=" + uuid);
    }
}
