package controller;

import entity.Match;
import exception.AppException;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.EnumPlayer;
import service.FinishedMatchesPersistenceService;
import service.MatchScoreCalculationService;
import service.OngoingMatchesService;
import java.io.IOException;

import static jakarta.servlet.http.HttpServletResponse.SC_BAD_REQUEST;
import static jakarta.servlet.http.HttpServletResponse.SC_NOT_FOUND;

@WebServlet("/match-score")
public class MatchScoreController extends HttpServlet {
    OngoingMatchesService ongoingMatchesService;
    MatchScoreCalculationService matchScoreCalculationService;
    FinishedMatchesPersistenceService finishedMatchesPersistenceService;
    public void init(ServletConfig config) {
        ongoingMatchesService = (OngoingMatchesService) config.getServletContext().getAttribute("ongoingMatchesService");
        matchScoreCalculationService = (MatchScoreCalculationService) config.getServletContext().getAttribute("matchScoreCalculationService");
        finishedMatchesPersistenceService = (FinishedMatchesPersistenceService) config.getServletContext().getAttribute("finishedMatchesPersistenceService");
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uuid = req.getParameter("uuid");

        if (uuid.isBlank()) {
            throw new AppException(SC_BAD_REQUEST, "Match UUID is empty");
        }

        Match ongoingMatch = ongoingMatchesService.get(uuid)
                .orElseThrow(() -> new AppException(SC_NOT_FOUND, "The match with UUID %s was not found".formatted(uuid)));

        req.setAttribute("match", ongoingMatch);
        req.setAttribute("uuid", uuid);
        req.getRequestDispatcher("match-score.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String uuid = req.getParameter("uuid");
        String stringPlayerID = req.getParameter("playerID");

        if (uuid.isBlank()) {
            throw new AppException(SC_BAD_REQUEST, "Match UUID is empty");
        }

        if (stringPlayerID.isBlank()) {
            throw new AppException(SC_BAD_REQUEST, "Player's ID is empty");
        }

        Match ongoingMatch = ongoingMatchesService.get(uuid)
                .orElseThrow(() -> new AppException(SC_NOT_FOUND, "The match with UUID %s was not found".formatted(uuid)));

        int playerID = Integer.parseInt(stringPlayerID);
        EnumPlayer pointWinner;

        if (playerID == ongoingMatch.getPlayer1().getId()) {
            pointWinner = EnumPlayer.FIRST_PLAYER;
        } else {
            pointWinner = EnumPlayer.SECOND_PLAYER;
        }

        matchScoreCalculationService.updateMatchScore(ongoingMatch, pointWinner);

        if (matchScoreCalculationService.isMatchFinished(ongoingMatch)) {
            Match match = matchScoreCalculationService.getFinishedMatch(ongoingMatch, pointWinner);
            ongoingMatchesService.remove(uuid);
            finishedMatchesPersistenceService.save(match);
        } else {
            resp.sendRedirect("match-score?uuid=" + uuid);
        }
    }
}
